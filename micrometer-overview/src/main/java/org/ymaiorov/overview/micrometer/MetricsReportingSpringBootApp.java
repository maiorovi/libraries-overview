package org.ymaiorov.overview.micrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.WebFilter;

@SpringBootApplication
public class MetricsReportingSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(MetricsReportingSpringBootApp.class, args);
    }

    @Bean
    public WebFilter contextPathWebFilter() {
        String contextPath = "/my-service";
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getURI().getPath().startsWith(contextPath)) {
                return chain.filter(
                        exchange.mutate()
                                .request(request.mutate().contextPath(contextPath).build())
                                .build());
            }

            return chain.filter(exchange);
        };
    }
}
