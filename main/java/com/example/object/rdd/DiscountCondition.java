package com.example.object.rdd;

import com.example.object.ddd.DiscountConditionType;

public interface DiscountCondition {
    public boolean isSatisfiedBy(Screening screening);
}
