package com.choi;


import java.util.Objects;

public class GradeCount {
    private int updatedGrade;
    private int count;

    public GradeCount(int updatedGrade, int count) {
        this.updatedGrade = updatedGrade;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeCount that = (GradeCount) o;
        return updatedGrade == that.updatedGrade && count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updatedGrade, count);
    }

    public void increase() {
        count++;
    }
}
