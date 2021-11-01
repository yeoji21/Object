package com.example.object.call.feeconditions;

import com.example.object.call.Call;
import com.example.object.call.DateTimeInterval;
import com.example.object.call.FeeCondition;

import java.util.Arrays;
import java.util.List;

public class FixedFeeCondition implements FeeCondition {
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return Arrays.asList(call.getInterval());
    }
}
