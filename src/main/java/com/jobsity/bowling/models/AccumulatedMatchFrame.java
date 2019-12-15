package com.jobsity.bowling.models;

public class AccumulatedMatchFrame extends MatchFrame {
    private int adjacentExtraScore;

    public AccumulatedMatchFrame(PinCount firstShot, int adjacentExtraScore) {
        super(firstShot);
        this.adjacentExtraScore = adjacentExtraScore;
    }

    @Override
    public int getFrameScore() {
        return getShotsScore() + adjacentExtraScore;
    }
}
