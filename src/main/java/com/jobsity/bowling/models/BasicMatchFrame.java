package com.jobsity.bowling.models;

public class BasicMatchFrame extends MatchFrame {
    public BasicMatchFrame(PinCount firstShot, PinCount secondShot) {
        super(firstShot);
        shots.add(secondShot);
    }

    @Override
    public int getFrameScore() {
        return getShotsScore();
    }
}
