package com.example.object.movieSystem;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void createMovieInstance(){
        Movie avartar = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10000),
                new AmountDiscountPolicy(Money.wons(800), new SequenceCondition(1), new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
                        new PeriodCondition(DayOfWeek.WEDNESDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
                ));
        System.out.println(avartar);

        Movie startWars = new Movie("스타워즈", Duration.ofMinutes(210), Money.wons(10000), new NoneDiscountPolicy());
        System.out.println(startWars);
    }

}