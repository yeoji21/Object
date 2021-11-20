package com.baek.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyCalcTest {

    @Test
    public void basic_calculation_test() {
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
    }

    @Test
    public void convert_negative_number_at_the_front(){
        assertThat(invertMinus("-1+3")).isEqualTo("0-1+3");
    }

    @Test
    public void expressionInitiallyHasNegativeNumber(){
        assertCalculate("-1+3",2);
        assertCalculate("-10*2+3",-17);
    }

    @Test
    public void change_return_value_to_double() {
        assertCalculate("10/3",3.33);
        assertCalculate("45+3*5-2+5/2*7", 75.5);
        assertCalculate("75.5-10", 65.5);
    }

    private String invertMinus(String origin) {
        if (origin.charAt(0)=='-')
            origin = origin.replaceFirst("[-]", "0-");
        return origin;
    }

    private void assertCalculate(String expression, double expected) {
        assertThat(new MyCalc(expression).calculate()).isEqualTo(expected);
    }
}
