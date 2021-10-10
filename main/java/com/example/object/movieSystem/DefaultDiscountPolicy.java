package com.example.object.movieSystem;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(List<DiscountCondition> conditions) {
        this.conditions = conditions;
    }

    public Money calculateDiscountAmount(Screening screening) {
//        conditions.stream().filter(c -> c.isSatisfiedBy(screening)).findFirst().ifPresent(getDiscountAmount(screening));

        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);

}
