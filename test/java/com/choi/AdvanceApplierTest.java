package com.choi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdvanceApplierTest {
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    void apply(){
        clearStu();
        givenStu(101, 1);
        givenStu(102, 2);
        givenStu(103, 3);

        AdvanceApplier applier = new AdvanceApplier(jdbcTemplate);

        applier.apply(new Targets(Arrays.asList(
                new User(101, 1),
                new User(102, 2),
                new User(103, 3)
        )));

        assertStuGrade(101, 2);
        assertStuGrade(102, 3);
        assertStuGrade(103, 4);
    }

    @Test
    void applyResult(){
        clearStu();
        givenStu(101, 1);
        givenStu(102, 2);

        AdvanceApplier applier = new AdvanceApplier(jdbcTemplate);

        Targets targets = new Targets(Arrays.asList(new User(101, 1), new User(102, 2)));

        ApplyResult applyResult = applier.apply(targets);
        Collection<GradeCount> cnts = applyResult.getGradeCounts();

        assertThat(cnts).contains(new GradeCount(2, 1), new GradeCount(3, 1));
    }

    private void assertStuGrade(int id, int expectedGrade) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select stu_id, grade from stuinfo where stu_id=?", id);
        rs.next();
        assertThat(rs.getInt("grade")).isEqualTo(expectedGrade);
    }

    private void clearStu() {
        jdbcTemplate.update("truncate table stuinfo");
    }

    private void givenStu(int id, int grade) {
        jdbcTemplate.update("insert into stuinfo values(?, ?)", id, grade);
    }
}
