package com.oven.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class MyJob2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("任务222开始执行。。。{}", new DateTime().toString("HH:mm:ss"));
    }

}
