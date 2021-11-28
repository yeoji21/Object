package com.choi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TargetsGenTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void gen(){
        clearStu();

        givenStu(101, 1);
        givenStu(102, 2);
        givenStu(103, 3);

        TargetGen targetGen = new TargetGen(jdbcTemplate);
        Targets targets = targetGen.gen();

        assertThat(targets.getUsers()).hasSize(3);
        assertThat(targets.getUsers()).contains(
                new User(101, 1), new User(102, 2), new User(103, 3)
        );
    }

    private void clearStu() {
        jdbcTemplate.update("truncate table stuinfo");
    }

    private void givenStu(int id, int grade) {
        jdbcTemplate.update("insert into stuinfo values(?, ?)", id, grade);
    }
}
