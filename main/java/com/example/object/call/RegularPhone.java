package com.example.object.call;

import com.example.object.movieSystem.Money;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RegularPhone extends Phone {
    private Money amount;
    private Duration seconds;

    public RegularPhone(double taxRate, Money amount, Duration seconds) {
        super(taxRate);
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
