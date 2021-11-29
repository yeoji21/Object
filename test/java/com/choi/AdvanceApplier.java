package com.choi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdvanceApplier {
    private JdbcTemplate jdbcTemplate;

    public AdvanceApplier(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ApplyResult apply(Targets targets) {
        Map<Integer, GradeCount> gradeCountMap = new HashMap<>();
        targets.getUsers().forEach(
                user -> {
                    int nextGrade = user.getGrade() + 1;
                    jdbcTemplate.update("update stuinfo set grade=? where stu_id=?",
                            nextGrade, user.getId());
                    GradeCount gradeCount = gradeCountMap.get(nextGrade);
                    if (gradeCount == null) gradeCountMap.put(nextGrade, new GradeCount(nextGrade,1));
//                    if (gradeCount == null) gradeCountMap.put(nextGrade, gradeCount);
                    else gradeCount.increase();
                });
        return new ApplyResult(gradeCountMap);
    }
}
