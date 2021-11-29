package com.choi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TargetGen {

    private JdbcTemplate jdbcTemplate;

    public TargetGen(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Targets gen() {
        List<User> users = jdbcTemplate.query("select * from stuinfo",
                (rs, rowNum) -> new User(rs.getInt("stu_id"), rs.getInt("grade")));
        return new Targets(users);
    }
}
