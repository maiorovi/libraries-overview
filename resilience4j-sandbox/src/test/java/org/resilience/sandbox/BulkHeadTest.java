package org.resilience.sandbox;

import io.github.resilience4j.bulkhead.Bulkhead;
import org.junit.jupiter.api.Test;

public class BulkHeadTest {

    @Test
    void name() {
        var bulkhead = Bulkhead.ofDefaults("my-bulkhead");

        var decorated = Bulkhead.decorateCallable(bulkhead, () -> {
           return "hello world!";
        });
    }
}
