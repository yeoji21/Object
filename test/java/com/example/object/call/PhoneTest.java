package com.example.object.call;

import com.example.object.movieSystem.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class PhoneTest {

    @Test
    void calcFee(){
        //given
//        RegularPhone phone = new RegularPhone(Money.wons(5), Duration.ofSeconds(10));
        Phone phone = new Phone(new RegularPolicy(Money.wons(5), Duration.ofSeconds(10)));

        phone.call(new Call(LocalDateTime.of(2018,1,1,
                12,10,0),
                LocalDateTime.of(2018,1,1,12,11,0)));
        phone.call(new Call(LocalDateTime.of(2018,1,2,12,10,0),
                LocalDateTime.of(2018,1,2,12,11,0)));

        //when
        Money money = phone.calculateFee();
        System.out.println(money);
        //then
    }

    @Test
    void multiPolicy(){
        //given
        Phone phone = new Phone(
                new TaxablePolicy(new RegularPolicy(Money.wons(5), Duration.ofSeconds(10)),0.1)
        );

        //when
        phone.call(new Call(LocalDateTime.of(2018,1,1,
                12,10,0),
                LocalDateTime.of(2018,1,1,12,11,0)));
        phone.call(new Call(LocalDateTime.of(2018,1,2,12,10,0),
                LocalDateTime.of(2018,1,2,12,11,0)));

        //then
        System.out.println(phone.calculateFee());
    }

    @Test
    void taxAndRateRegular(){
        //given
        Phone phone = new Phone(
                new TaxablePolicy(new RateDiscountPolicy(
                        new RegularPolicy(Money.wons(5), Duration.ofSeconds(10)),Money.wons(10)
                ),0.1)
        );

        //when
        phone.call(new Call(LocalDateTime.of(2018,1,1,
                12,10,0),
                LocalDateTime.of(2018,1,1,12,11,0)));
        phone.call(new Call(LocalDateTime.of(2018,1,2,12,10,0),
                LocalDateTime.of(2018,1,2,12,11,0)));

        //then
        System.out.println(phone.calculateFee());
    }
}