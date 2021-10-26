package com.example.object.call;

import com.example.object.movieSystem.Money;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NightlyDiscountPhone extends Phone {
    private static final int LATE_NIGHT_HOUR = 22;
    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;

    public NightlyDiscountPhone(double taxRate, Money nightlyAmount, Money regularAmount, Duration seconds) {
        super(taxRate);
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    protected Money calculateCallFee(Call call) {
        if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
            return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }else{
            return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }
    }
}
