package com.baek.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    public void roll_메소드_호출_테스트(){
        game.roll(0);
    }

    private void rollMany(int pins, int frames) {
        for (int i = 0; i < frames; i++) {
            game.roll(pins);
        }
    }

    @Test
    public void gutterGame(){
        // 핀을 하나도 못 쓰러뜨리고 끝나는 경우
        rollMany(0, 20);
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    public void 모든세트에서_핀을_하나씩만_쓰러뜨림(){
        // 핀을 하나도 못 쓰러뜨리고 끝나는 경우
        rollMany(1, 20);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    public void oneSpareGame(){
        rollSpare();
        game.roll(3);
        rollMany(0, 17);
        assertThat(game.score()).isEqualTo(16); //13 + 3
    }

    @Test
    public void oneStrike(){
        RollStrike();
        game.roll(5);
        game.roll(3);
        rollMany(0, 16);
        assertThat(game.score()).isEqualTo(26); //18 + 8
    }

    @Test
    public void perfectGame(){
        rollMany(10, 10);
        game.roll(10);
        game.roll(10);
        assertThat(game.score()).isEqualTo(300);
    }

    private void RollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }
}
