package com.baek.bowling;

public class Game {

    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int firstRollInFrame = 0;
        for (int frame = 0; frame < 10; frame++) {
            if(isStrike(firstRollInFrame)){
                score += 10 + nextBallsForStrike(firstRollInFrame);
                firstRollInFrame += 1;
            }
            else if(isSpare(firstRollInFrame)){
                score += 10 + nextBallsForSpare(firstRollInFrame);
                firstRollInFrame += 2;
            }
            else{
                score += nextBallsInFrame(firstRollInFrame);
                firstRollInFrame += 2;
            }
        }
        return score;
    }

    private int nextBallsInFrame(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1];
    }

    private int nextBallsForSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame+2];
    }

    private int nextBallsForStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame + 1] + rolls[firstRollInFrame + 2];
    }

    private boolean isStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame] == 10;
    }

    private boolean isSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1] == 10;
    }
}
