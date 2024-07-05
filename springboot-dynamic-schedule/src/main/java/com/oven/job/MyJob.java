package com.oven.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        // 获取参数
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String param1 = (String) dataMap.get("param1");
        String param2 = (String) dataMap.get("param2");
        String param3 = (String) dataMap.get("param3");

        log.info(param1);
        log.info(param2);
        log.info(param3);
    }

}
