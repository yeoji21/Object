package com.choi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class GivenAssertHelper {
    private JdbcTemplate jdbcTemplate;

    public GivenAssertHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void clearStu() {
        jdbcTemplate.update("truncate table stuinfo");
    }
    public void givenStu(int id, int grade) {
        jdbcTemplate.update("insert into stuinfo values(?, ?)", id, grade);
    }
    public void assertStuGrade(int id, int expectedGrade) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select stu_id, grade from stuinfo where stu_id=?", id);
        rs.next();
        assertThat(rs.getInt("grade")).isEqualTo(expectedGrade);
    }
}
