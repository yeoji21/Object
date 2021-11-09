package com.baek.switchex;

public class Time {
    private long nano;

    public Time(long nano) {
        this.nano = nano;
    }

    public long getMilliTime() {
        return (long) (nano / Math.pow(10, 6));
    }
}
