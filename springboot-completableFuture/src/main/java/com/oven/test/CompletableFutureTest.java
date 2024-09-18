package com.oven.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CompletableFutureTest implements CommandLineRunner {

    @Resource(name = "myThreadPool")
    private Executor myThreadPool;

    /**
     * 分批处理，并获取结果
     */
    @Override
    public void run(String... args) {
        // 保存结果数据
        StringJoiner joiner = new StringJoiner(" | ");

        // 生成模拟数据
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            dataList.add("" + i);
        }

        // 每批数量
        int batchSize = 5;
        // 总批次数
        int totalBatches = (int) Math.ceil((double) dataList.size() / batchSize);
        for (int i = 0; i < totalBatches; i++) {
            // 获取当前批次数据
            int start = i * batchSize;
            int end = Math.min(start + batchSize, dataList.size());
            List<String> batch = dataList.subList(start, end);

            // 创建CompletableFuture任务来处理当前批次的数据
            List<CompletableFuture<String>> futures = batch.stream().map(this::processData).collect(Collectors.toList());

            // 等待所有任务完成
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            try {
                allOf.get();
                List<String> resultList = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
                resultList.forEach(joiner::add);
            } catch (Exception e) {
                log.error("系统异常：", e);
            }
        }
        log.info("执行结果为：{}", joiner);
    }

    private CompletableFuture<String> processData(String data) {
        return CompletableFuture.supplyAsync(() -> executeTask(data), myThreadPool);
        // ExecutorService executor = Executors.newFixedThreadPool(5);
        // return CompletableFuture.supplyAsync(() -> executeTask(data), executor);
    }

    private String executeTask(String data) {
        log.info("开始执行任务：{}", data);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            log.error("系统异常：", e);
        }
        return "Processed-" + data;
    }

}
