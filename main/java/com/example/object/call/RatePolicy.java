package com.example.object.call;

import com.example.object.movieSystem.Money;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
