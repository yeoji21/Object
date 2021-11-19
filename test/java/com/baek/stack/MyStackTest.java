package com.baek.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStackTest {
    @Test
    public void pop_should_return_pushed_value() {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);

        assertThat(stack.pop()).isEqualTo(2);
        assertThat(stack.pop()).isEqualTo(1);

    }

    private class MyStack {
//        List<Integer> value = new ArrayList<>();
        private static final int STACK_SIZE = 10;
        private int[] value = new int[STACK_SIZE];
        private int index = 0;

        public int pop() {
            return value[--index];
        }

        public void push(int value) {
            this.value[index++] = value;
        }
    }
}
