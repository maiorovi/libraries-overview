package org.ymaiorov.overview.micrometer.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloHandler {

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    @GetMapping(path = "/hello")
    public Mono<String> helloWorld() {
        registerCounter();
        log.info("hello world executed {}", prometheusMeterRegistry.get("hello.world").counter().count());
        return Mono.just("Hello World");
    }

    private void registerCounter() {
        Counter.builder("hello.world")
                .tag("key1", "value")
                .description("Hello world counter")
                .register(prometheusMeterRegistry)
                .increment();
    }
}
