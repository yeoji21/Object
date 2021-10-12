package com.example.object.rdd;

import com.example.object.ddd.MovieType;
import com.example.object.movieSystem.Money;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;

    public Movie(String title, Duration runningTime, Money fee, DiscountCondition... discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

    abstract protected Money calculateDiscountAmount();

    private boolean isDiscountable(Screening screening) {
        return discountConditions.stream().anyMatch(c -> c.isSatisfiedBy(screening));
    }

    protected Money getFee() {
        return fee;
    }
}
