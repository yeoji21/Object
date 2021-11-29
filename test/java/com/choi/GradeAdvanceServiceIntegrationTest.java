package com.choi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class GradeAdvanceServiceIntegrationTest {
    @Autowired
    private GradeAdvanceService service;
    @Autowired
    private GivenAssertHelper helper;

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(GradeAdvanceService.DEFAULT_TARGETS_FILE);
    }

    @Test
    void applySuccess(){
        helper.clearStu();
        helper.givenStu(501, 1);
        helper.givenStu(502, 2);

        AdvanceResult result = service.advance();
        assertThat(result).isEqualTo(AdvanceResult.SUCCESS);

        helper.assertStuGrade(501, 2);
        helper.assertStuGrade(502, 3);
    }
}
