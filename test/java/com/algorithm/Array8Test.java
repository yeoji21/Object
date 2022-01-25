package com.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Array8Test {

    @Test
    void array8(){
        assertThat(solution(100, 90, 80)).isEqualTo(new int[]{1, 2, 3});
        assertThat(solution(80, 90, 100)).isEqualTo(new int[]{3, 2, 1});
        assertThat(solution(100, 80, 90, 100)).isEqualTo(new int[]{1, 4, 3, 1});

    }

    private int[] solution(int... scores) {
        int[] result = new int[scores.length];

        for (int i = 0; i < result.length; i++) {
            int rank = 1;
            for (int j = 0; j < result.length; j++) {
                if (scores[i] < scores[j])
                    rank++;
            }
            result[i] = rank;
        }

        return result;
    }
}
