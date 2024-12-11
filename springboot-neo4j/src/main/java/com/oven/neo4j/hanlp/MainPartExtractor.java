package com.oven.neo4j.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.oven.neo4j.util.GraphUtil;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeGraphNode;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseTreebankLanguagePack;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public class MainPartExtractor {

    private static final LexicalizedParser lp;
    private static final GrammaticalStructureFactory gsf;

    static {
        // 模型
        String models = "models/chineseFactored.ser";
        log.info("载入文法模型：{}", models);
        lp = LexicalizedParser.loadModel(models);
        // 汉语
        TreebankLanguagePack tlp = new ChineseTreebankLanguagePack();
        gsf = tlp.grammaticalStructureFactory();
    }

    /**
     * 获取句子的主谓宾
     *
     * @param sentence 问题
     * @return 问题结构
     */
    public static MainPart getMainPart(String sentence) {
        // 去掉不可见字符
        sentence = sentence.replace("\\s+", "");
        // 分词，用空格隔开
        List<Word> wordList = seg(sentence);
        return getMainPart(wordList);
    }

    /**
     * 获取句子的主谓宾
     *
     * @param words HashWord列表
     * @return 问题结构
     */
    public static MainPart getMainPart(List<Word> words) {
        MainPart mainPart = new MainPart();
        if (words == null || words.isEmpty()) {
            return mainPart;
        }
        Tree tree = lp.apply(words);
        log.info("句法树:{}", tree.pennString());
        // 根据整个句子的语法类型来采用不同的策略提取主干
        if (tree.firstChild().label().toString().equals("NP")) {// 名词短语，认为只有主语，将所有短NP拼起来作为主语即可
            mainPart = getNPPhraseMainPart(tree);
        } else {
            GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
            Collection<TypedDependency> tdls = gs.typedDependenciesCCprocessed(true);
            log.info("依存关系：{}", tdls);
            TreeGraphNode rootNode = getRootNode(tdls);
            if (rootNode == null) {
                return getNPPhraseMainPart(tree);
            }
            log.info("中心词语：{}", rootNode);
            mainPart = new MainPart(rootNode);
            for (TypedDependency td : tdls) {
                // 依存关系的出发节点，依存关系，以及结束节点
                TreeGraphNode gov = td.gov();
                GrammaticalRelation reln = td.reln();
                String shortName = reln.getShortName();
                TreeGraphNode dep = td.dep();
                if (gov == rootNode) {
                    switch (shortName) {
                        case "nsubjpass":
                        case "dobj":
                        case "attr":
                            mainPart.object = dep;
                            break;
                        case "nsubj":
                        case "top":
                            mainPart.subject = dep;
                            break;
                    }
                }
                if (mainPart.object != null && mainPart.subject != null) {
                    break;
                }
            }
            // 尝试合并主语和谓语中的名词性短语
            combineNN(tdls, mainPart.subject);
            combineNN(tdls, mainPart.object);
            if (!mainPart.isDone()) {
                mainPart.done();
            }
        }
        return mainPart;
    }

    private static MainPart getNPPhraseMainPart(Tree tree) {
        MainPart mainPart = new MainPart();
        StringBuilder sbResult = new StringBuilder();
        List<String> phraseList = getPhraseList("NP", tree);
        for (String phrase : phraseList) {
            sbResult.append(phrase);
        }
        mainPart.result = sbResult.toString();
        return mainPart;
    }

    /**
     * 从句子中提取最小粒度的短语
     */
    @SuppressWarnings("unused")
    public static List<String> getPhraseList(String type, String sentence) {
        return getPhraseList(type, lp.apply(seg(sentence)));
    }

    private static List<String> getPhraseList(String type, Tree tree) {
        List<String> phraseList = new LinkedList<>();
        for (Tree subtree : tree) {
            if (subtree.isPrePreTerminal() && subtree.label().value().equals(type)) {
                StringBuilder sbResult = new StringBuilder();
                for (Tree leaf : subtree.getLeaves()) {
                    sbResult.append(leaf.value());
                }
                phraseList.add(sbResult.toString());
            }
        }
        return phraseList;
    }

    /**
     * 合并名词性短语为一个节点
     *
     * @param tdls   依存关系集合
     * @param target 目标节点
     */
    private static void combineNN(Collection<TypedDependency> tdls, TreeGraphNode target) {
        if (target == null) {
            return;
        }
        for (TypedDependency td : tdls) {
            // 依存关系的出发节点，依存关系，以及结束节点
            TreeGraphNode gov = td.gov();
            GrammaticalRelation reln = td.reln();
            String shortName = reln.getShortName();
            TreeGraphNode dep = td.dep();
            if (gov == target) {
                if (shortName.equals("nn")) {
                    target.setValue(dep.toString("value") + target.value());
                    return;
                }
            }
        }
    }

    private static TreeGraphNode getRootNode(Collection<TypedDependency> tdls) {
        for (TypedDependency td : tdls) {
            if (td.reln() == GrammaticalRelation.ROOT) {
                return td.dep();
            }
        }
        return null;
    }

    /**
     * 分词
     *
     * @param sentence 句子
     * @return 分词结果
     */
    private static List<Word> seg(String sentence) {
        // 分词
        log.info("正在对短句进行分词：{}", sentence);
        List<Word> wordList = new LinkedList<>();
        List<Term> terms = HanLP.segment(sentence);
        StringBuilder sbLogInfo = new StringBuilder();
        for (Term term : terms) {
            Word word = new Word(term.word);
            wordList.add(word);
            sbLogInfo.append(word);
            sbLogInfo.append(' ');
        }
        log.info("分词结果为：{}", sbLogInfo);
        return wordList;
    }

    @SuppressWarnings("unused")
    public static MainPart getMainPart(String sentence, String delimiter) {
        List<Word> wordList = new LinkedList<>();
        for (String word : sentence.split(delimiter)) {
            wordList.add(new Word(word));
        }
        return getMainPart(wordList);
    }

    /**
     * 调用演示
     */
    public static void main(String[] args) {
        String[] testCaseArray = {
                "我一直很喜欢你",
                "你被我喜欢",
                "美丽又善良的你被卑微的我深深的喜欢着……",
                "小米公司主要生产智能手机",
                "他送给了我一份礼物",
                "这类算法在有限的一段时间内终止",
                "如果大海能够带走我的哀愁",
                "天青色等烟雨，而我在等你",
                "我昨天看见了一个非常可爱的小孩"
        };
        for (String testCase : testCaseArray) {
            MainPart mp = MainPartExtractor.getMainPart(testCase);
            System.out.printf("%s   %s   %s \n",
                    GraphUtil.getNodeValue(mp.getSubject()),
                    GraphUtil.getNodeValue(mp.getPredicate()),
                    GraphUtil.getNodeValue(mp.getObject()));
        }
    }

}