package com.oven.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 动态定时任务
 *
 * @author Oven
 */
@Component
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Mapper
    public interface CrontabMapper {
        @Select("select cron from t_crontab where _key='cronKey'")
        String getCron();
    }

    @Autowired
    @SuppressWarnings("all")
    private CrontabMapper crontabMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(this::doSomething, triggerContext -> {
            String cron = crontabMapper.getCron();
            if (StringUtils.isEmpty(cron)) {
                System.out.println("cron is null...");
            }
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }

    private void doSomething() {
        System.out.println("do something..." + new DateTime().getSecondOfMinute());
    }

}
