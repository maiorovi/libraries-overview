package org.resilience.sandbox;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

public class CircuitBreakerTest {

    @Test
    void cbReturnsResultOnFastOperation() throws Exception {
        var expectedString = "operation string";

        var cb = CircuitBreaker.ofDefaults("hello-world");
        var decoratedCallable = cb.decorateCallable(() -> "operation string");

        var actualString = decoratedCallable.call();

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void cbBreaksCallWhenItTakesLongerThanAllowedAndTransitionToOpenState() throws Exception {
        var config = CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(1)
                .failureRateThreshold(60)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();

        var cb = CircuitBreaker.of("my-circuit-breaker", config);

        var decoratedCallable = cb.decorateCallable(() -> {
            Thread.sleep(2000);
            return "operation string";
        });

        decoratedCallable.call();

        assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.OPEN);
    }

    @Test
    void cbTransitionStaysCloseWhenNotEnoughCallsMade() throws Exception {
        var config = CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(11)
                .failureRateThreshold(60)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();

        var cb = CircuitBreaker.of("my-circuit-breaker", config);

        var decoratedCallable = cb.decorateSupplier(() -> {
            throw new RuntimeException();
        });

        IntStream.range(0, 10)
                .forEach(i -> {
                    try {
                        decoratedCallable.get();
                    } catch (RuntimeException runtimeException) {
                        assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
                    }
                });

        assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
    }

    @Test
    void cbOpensWhenFailedCallsMadeMoreThanThreshold() throws Exception {
        var config = CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(11)
                .failureRateThreshold(60)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();

        var cb = CircuitBreaker.of("my-circuit-breaker", config);

        var decoratedCallable = cb.decorateSupplier(() -> {
            throw new RuntimeException();
        });

        IntStream.range(0, 10)
                .forEach(i -> {
                    try {
                        decoratedCallable.get();
                    } catch (RuntimeException runtimeException) {
                        assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.CLOSED);
                    }
                });

        try {
            decoratedCallable.get();
            fail("should throw runtimeException");
        } catch (RuntimeException ex) {
            assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.OPEN);
        }
    }

    @Test
    void shouldNotExecuteCallWhenCircuitIsOpen() {
        var config = CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(1)
                .failureRateThreshold(60)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .build();

        var cb = CircuitBreaker.of("my-circuit-breaker", config);

        var decoratedCallable = cb.decorateSupplier(() -> {
            throw new RuntimeException();
        });

        try {
            decoratedCallable.get();
            fail("should throw runtimeException");
        } catch (RuntimeException ex) {
            assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.OPEN);
        }

        assertThatThrownBy(decoratedCallable::get)
                .isInstanceOf(CallNotPermittedException.class);
    }
}
