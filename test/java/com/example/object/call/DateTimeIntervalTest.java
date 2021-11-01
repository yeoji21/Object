package com.example.object.call;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeIntervalTest {

    @Test
    void daysTest(){
        //given
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.of(2021,11,3);
        long days = Duration.between(from.atStartOfDay(), to.atStartOfDay()).toDays();

        System.out.println(days);
        //when
        List<DateTimeInterval> result = new ArrayList<>(1);

        for (int loop = 1; loop < days; loop++) {
            result.add(DateTimeInterval.during(from.plusDays(loop)));
        }
        //then

        result.forEach(d -> {
            System.out.println(d.getFrom());
            System.out.println(d.getTo());
        });
    }

}