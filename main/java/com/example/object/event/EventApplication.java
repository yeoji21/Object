package com.example.object.event;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventApplication {
    public static void main(String[] args) {
        Event meeting = new Event("회의", LocalDateTime.of(2019, 5, 8, 10, 30),
                Duration.ofMinutes(30));

        RecurringSchedule schedule = new RecurringSchedule("회의", DayOfWeek.WEDNESDAY,
                LocalTime.of(10, 30), Duration.ofMinutes(30));

    }
}
