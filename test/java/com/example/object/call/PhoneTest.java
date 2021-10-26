package com.example.object.call;

import com.example.object.movieSystem.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class PhoneTest {

    @Test
    void calcFee(){
        //given
        RegularPhone phone = new RegularPhone(0.1, Money.wons(5), Duration.ofSeconds(10));
        phone.call(new Call(LocalDateTime.of(2018,1,1,12,10,0),
                LocalDateTime.of(2018,1,1,12,11,0)));
        phone.call(new Call(LocalDateTime.of(2018,1,2,12,10,0),
                LocalDateTime.of(2018,1,2,12,11,0)));

        //when
        System.out.println(phone.calculateFee());
        //then
    }
}