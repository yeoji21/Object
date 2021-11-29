package com.choi;

import java.util.Collection;
import java.util.Map;

public class ApplyResult {

    private Map<Integer, GradeCount> gradeCountMap;

    public ApplyResult(Map<Integer, GradeCount> gradeCountMap) {
        this.gradeCountMap = gradeCountMap;
    }

    public Collection<GradeCount> getGradeCounts() {
        return gradeCountMap.values();
    }
}
