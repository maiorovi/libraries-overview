package com.ymaiorov.overview;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

import java.time.temporal.ChronoUnit;
import java.util.Map;

public class RetryPolicyDemo {

    public static void main(String[] args) {
//        var retryPolicy = new RetryPolicy<Map<String, Integer>>()
//                .abortIf(map -> map.size() != 1)
//                .withMaxRetries(5)
//                .withBackoff(500, 5000,  ChronoUnit.MILLIS);
//
//        Failsafe.with(retryPolicy)
//                .get()
    }

}
