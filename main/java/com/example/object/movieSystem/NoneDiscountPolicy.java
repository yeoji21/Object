package com.example.object.movieSystem;

import java.util.List;

public class NoneDiscountPolicy extends DiscountPolicy {

    public NoneDiscountPolicy() {
        super(null);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
