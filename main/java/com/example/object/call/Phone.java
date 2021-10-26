package com.example.object.call;

import com.example.object.movieSystem.Money;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {

    private double taxRate;
    private List<Call> calls = new ArrayList<>();

    public Phone(double taxRate) {
        this.taxRate = taxRate;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }
        return result.plus(result.times(taxRate));
    }

    abstract protected Money calculateCallFee(Call call);

    public void call(Call call) {
        calls.add(call);
    }
}
