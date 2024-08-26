package com.oven.grpc.service;

import com.oven.grpc.entity.StudentRequest;
import com.oven.grpc.entity.StudentResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class StudentService extends StudentGrpc.StudentImplBase {

    @Override
    public void getByPage(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("请求grpc服务端");
        StudentResponse response = StudentResponse.newBuilder()
                .setCode(200)
                .setMessage("成功")
                .setData("结果数据")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
