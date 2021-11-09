package com.baek.switchex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProceduralStopWatchTest {

    private long expectedElapsedTime = 100l;

    @Test
    public void test(){
        ProceduralStopWatch stopWatch = new ProceduralStopWatch();
        stopWatch.startTime = System.currentTimeMillis();
        doSomething();
        stopWatch.stopTime = System.currentTimeMillis();
        long elapsedTime = stopWatch.getElapsedTime();
        assertThat(elapsedTime).isGreaterThanOrEqualTo(expectedElapsedTime);
    }

    private void doSomething() {

    }
}