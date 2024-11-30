package com.oven.picocli.command;

import com.oven.picocli.service.MailService;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "mailCommand")
public class MailCommand implements Callable<Integer> {

    @Resource
    private MailService mailService;

    @Option(names = "--to", description = "发给谁", required = true)
    List<String> to;

    @Option(names = "--subject", description = "标题")
    String subject;

    @Parameters(description = "内容")
    String[] body = {};

    @Override
    public Integer call() {
        mailService.sendMessage(to, subject, String.join(" ", body));
        return 0;
    }

}