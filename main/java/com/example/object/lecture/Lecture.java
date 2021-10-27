package com.example.object.lecture;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
    private String title;
    private int pass;
    private List<Integer> scores = new ArrayList<>();

    public Lecture(String title, int pass, List<Integer> scores) {
        this.title = title;
        this.pass = pass;
        this.scores = scores;
    }

    public double average() {
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public String evaluate() {
        return String.format("Pass:%d Fail:%d", passCount(), failCount());
    }

    private long passCount() {
        return scores.stream().filter(score->score >= pass).count();
    }

    private long failCount() {
        return scores.size() - passCount();
    }

    public List<Integer> getScores() {
        return scores;
    }

    public String stats(){
        return String.format("Title : %s, Evaluation Method : %s", title, getEvaluationMethod());
    }

    public String getEvaluationMethod() {
        return "Pass or Fail";
    }
}
