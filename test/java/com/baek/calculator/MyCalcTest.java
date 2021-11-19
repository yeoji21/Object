package com.baek.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCalcTest {

    @Test
    public void test() {
        assertCalculate("", 0);
        assertCalculate("1", 1);
        assertCalculate("2", 2);
        assertCalculate("1+1", 2);
        assertCalculate("1-1", 0);
        assertCalculate("2*3", 6);
        assertCalculate("4/2", 2);
        assertCalculate("10+1", 11);
        assertCalculate("22/2", 11);
        assertCalculate("100/10", 10);
        assertCalculate("0/10", 0);
        assertThrows(IllegalArgumentException.class, () -> new MyCalc("1/0").calculate());
    }

    @Test
    public void third_expression(){
        assertCalculate("3+3-4", 2);
        assertCalculate("3-3-4", -4);
        assertCalculate("2-3+6-4+15", 16);
        assertCalculate("2*3/6", 1);
    }

    @Test
    public void priority_expression(){
        assertCalculate("2+3*4",14);
        assertCalculate("10/2+4*4",21);
        assertCalculate("45+3*5-2+5/2*7",72);
    }

    private void assertCalculate(String expression, int expected) {
        assertThat(new MyCalc(expression).calculate()).isEqualTo(expected);
    }
}
