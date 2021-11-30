package com.choi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OneMillTest {
    @Autowired TargetGen gen;

    @Test
    void targetsGenTest() {
        Targets targets = this.gen.gen();
        assertThat(targets.getUsers()).hasSize(100_0000);
    }
}
