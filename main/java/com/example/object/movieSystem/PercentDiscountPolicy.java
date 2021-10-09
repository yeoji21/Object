package com.example.object.movieSystem;

import java.util.Arrays;
import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy{

    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(Arrays.asList(conditions));
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
