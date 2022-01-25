package com.algorithm;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.iterable;

public class String10Test {
    @Test
    void shortestStringPath(){
        char findChar = 'e';

        assertThat(solution("te", findChar)).isEqualTo("1 0 ");
        assertThat(solution("tea", findChar)).isEqualTo("1 0 1 ");
        assertThat(solution("teach", findChar)).isEqualTo("1 0 1 2 3 ");
        assertThat(solution("teacher", findChar)).isEqualTo("1 0 1 2 1 0 1 ");
        assertThat(solution("teachermode", findChar)).isEqualTo("1 0 1 2 1 0 1 2 2 1 0 ");
        assertThat(solution("eee", findChar)).isEqualTo("0 0 0 ");
        assertThat(solution("eabae", findChar)).isEqualTo("0 1 2 1 0 ");
    }

    private String solution(String targetString, char findChar) {
        char[] targetChars = targetString.toCharArray();
        StringBuilder builder = new StringBuilder("");
        int[] result = new int[targetChars.length];

        int score = 1000;

        for (int i = 0; i<targetString.length(); i++){
            if (targetString.charAt(i) == findChar){
                score = 0;
                result[i] = score;
            }
            else
                result[i] = ++score;
        }

        score = 1000;
        for (int i = targetChars.length-1; i >= 0; i--) {
            if (targetString.charAt(i) == findChar)
                score = 0;
            else
                result[i] = Math.min(result[i], ++score);
        }

        Arrays.stream(result).forEach(r -> builder.append(r).append(" "));
        return builder.toString();
    }
}
