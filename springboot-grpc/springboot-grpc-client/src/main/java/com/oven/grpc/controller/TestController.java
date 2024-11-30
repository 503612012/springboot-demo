package com.oven.grpc.controller;

import com.oven.grpc.entity.StudentRequest;
import com.oven.grpc.entity.StudentResponse;
import com.oven.grpc.service.StudentGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GrpcClient("studentGrpcServer")
    private StudentGrpc.StudentBlockingStub studentBlockingStub;

    @RequestMapping("/test")
    public Object test() {
        StudentRequest request = StudentRequest.newBuilder()
                .setPageNum(1)
                .setPageSize(10)
                .setSearcher("啪啪啪")
                .build();
        StudentResponse response = studentBlockingStub.getByPage(request);
        log.info("调用grpc结果：{}", response);
        return response;
    }

}
