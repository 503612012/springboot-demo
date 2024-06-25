package com.oven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Flux<String> hello() {
        return Flux.just("Welcome to reactive world~");
    }

}