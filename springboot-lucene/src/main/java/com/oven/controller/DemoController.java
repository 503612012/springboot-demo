package com.oven.controller;

import com.oven.entity.Article;
import com.oven.util.LuceneUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DemoController {

    @PostMapping("/add")
    public String add(Article article) {
        try {
            LuceneUtil.index(article);
            return "success";
        } catch (Exception e) {
            log.error("系统异常：", e);
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(Integer id) {
        try {
            LuceneUtil.delete(id);
            return "success";
        } catch (Exception e) {
            log.error("系统异常：", e);
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(Article article) {
        try {
            LuceneUtil.update(article);
            return "success";
        } catch (Exception e) {
            log.error("系统异常：", e);
            return "error";
        }
    }

    @PostMapping("/search")
    public List<Article> search(String keyword) {
        try {
            return LuceneUtil.search(keyword);
        } catch (Exception e) {
            log.error("系统异常：", e);
        }
        return null;
    }

}
