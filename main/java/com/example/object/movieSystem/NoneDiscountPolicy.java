package com.example.object.movieSystem;

public class NoneDiscountPolicy implements DiscountPolicy{

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
