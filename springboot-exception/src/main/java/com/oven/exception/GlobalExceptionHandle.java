package com.oven.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 处理捕获的异常
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleException(HttpServletRequest request) {
        log.info("请求地址：{}", request.getRequestURL().toString());
        log.info("请求方法：{}", request.getMethod());
        log.info("请求者IP：{}", request.getRemoteAddr());
        return "异常了";
    }

}
