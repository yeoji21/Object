package com.example.object.call;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeIntervalTest {

    @Test
    void daysTest(){
        //given
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.of(2021,11,8);
        long days = Duration.between(from.atStartOfDay(), to.atStartOfDay()).toDays();

        System.out.println(days);
        //when

        //then
    }

}