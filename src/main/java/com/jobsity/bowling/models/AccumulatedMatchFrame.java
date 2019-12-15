package com.jobsity.bowling.models;

public class AccumulatedMatchFrame extends MatchFrame {
    private int adjacentExtraScore;

    public AccumulatedMatchFrame(PinCount firstShot, int adjacentExtraScore) {
        super();
        this.firstShot = firstShot;
        this.adjacentExtraScore = adjacentExtraScore;
    }

    @Override
    public int getScore() {
        return super.getScore() + adjacentExtraScore;
    }
}
