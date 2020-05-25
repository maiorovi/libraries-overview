package org.resilience.sandbox;

import io.github.resilience4j.timelimiter.TimeLimiter;
import org.junit.jupiter.api.Test;

public class TimeLimiterTest {

    @Test
    void name() {
        var limiter = TimeLimiter.ofDefaults();

//        limiter.decorateFutureSupplier()
    }
}
