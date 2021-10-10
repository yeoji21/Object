package com.example.object.movieSystem;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
