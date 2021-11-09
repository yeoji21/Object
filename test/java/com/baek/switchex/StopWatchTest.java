package com.baek.switchex;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StopWatchTest {

    private long expectedElapsedTime = 100l;

    @Test
    public void should_return_elapsed_millis() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        doSomething();
        stopWatch.stop();

        Time time = stopWatch.getElapsedTime();
        assertThat(time.getMilliTime()).isGreaterThanOrEqualTo(expectedElapsedTime);
    }

    private void doSomething() {
    }
}
