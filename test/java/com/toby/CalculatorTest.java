package com.toby;

import com.toby.calc.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    Calculator calculator;
    String filePath;

    @BeforeEach
    void before() {
        calculator = new Calculator();
        filePath = "/Users/yeojiwon/Desktop/Spring Project/object/src/main/java/com/toby/numbers.txt";
    }

    @Test
    void sumOfNumbers() throws Exception{
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(filePath);
        assertThat(sum).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws Exception{
        assertThat(calculator.calcMultiply(filePath)).isEqualTo(24);
    }
}