package com.example.object.rdd;

import com.example.object.movieSystem.Money;

import java.time.Duration;

public class PercentDiscountMovie extends Movie {

    private double discountPercent;

    public PercentDiscountMovie(String title, Duration runningTime, Money fee, double discountPercent,
                                DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountPercent = discountPercent;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return getFee().times(discountPercent);
    }
}
