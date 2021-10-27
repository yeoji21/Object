package com.example.object.lecture;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    @Test
    void lecture(){
        //given
        Lecture lecture = new Lecture("객체지향 프로그래밍", 70, Arrays.asList(81, 95, 75, 50, 45));
        String evaluation = lecture.evaluate();
        //when
        System.out.println(evaluation);
        //then
    }


    @Test
    void gradeLecture(){
        //given
        Lecture lecture = new GradeLecture("객체지향 프로그래밍", 70,
                Arrays.asList(81, 95, 75, 50, 45),
                Arrays.asList(new Grade("A",100, 95),
                        new Grade("B", 94, 80),
                        new Grade("C", 79, 70),
                        new Grade("D", 69, 50),
                        new Grade("F", 49, 0)));

        //when
        //then
        System.out.println(lecture.evaluate());
    }

    @Test
    void gradeAverage(){
        //given
        GradeLecture lecture = new GradeLecture("객체지향 프로그래밍", 70,
                Arrays.asList(81, 95, 75, 50, 45),
                Arrays.asList(new Grade("A",100, 95),
                        new Grade("B", 94, 80),
                        new Grade("C", 79, 70),
                        new Grade("D", 69, 50),
                        new Grade("F", 49, 0)));
        //when
        //then
        System.out.println(lecture.average("A"));
    }

    @Test
    void professorAvg(){
        //given
        Professor professor = new Professor("다익스트라",
                new Lecture("알고리즘", 70,
                        Arrays.asList(81, 95, 75, 50, 45)));
        //when
        //then
        System.out.println(professor.compileStatistics());
    }

    @Test
    void professorGradeAvg(){
        //given
        Professor professor = new Professor("다익스트라",
                new GradeLecture("객체지향 프로그래밍", 70,
                        Arrays.asList(81, 95, 75, 50, 45),
                        Arrays.asList(new Grade("A",100, 95),
                                new Grade("B", 94, 80),
                                new Grade("C", 79, 70),
                                new Grade("D", 69, 50),
                                new Grade("F", 49, 0))));
        //when
        //then
        System.out.println(professor.compileStatistics());
    }

    @Test
    void superMethodTest(){
        //given
        FormattedGradeLecture lecture = new FormattedGradeLecture("객체지향 프로그래밍", 70,
                Arrays.asList(81, 95, 75, 50, 45),
                Arrays.asList(new Grade("A",100, 95),
                        new Grade("B", 94, 80),
                        new Grade("C", 79, 70),
                        new Grade("D", 69, 50),
                        new Grade("F", 49, 0)));
        //when
        System.out.println(lecture.formatAverage());
        //then
    }
}