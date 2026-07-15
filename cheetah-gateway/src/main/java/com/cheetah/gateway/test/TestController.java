package com.cheetah.gateway.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {




    @GetMapping("mono")
    public Mono<List<String>> mono(ServerHttpRequest request, ServerHttpResponse response) {
        return Mono.just(Arrays.asList("mono","mono1"));
    }

    @GetMapping("flux")
    public Flux<String> flux(ServerHttpRequest request, ServerHttpResponse response) {
        return Flux.just("mono",request.getLocalAddress().getAddress().getHostAddress());
    }

}
