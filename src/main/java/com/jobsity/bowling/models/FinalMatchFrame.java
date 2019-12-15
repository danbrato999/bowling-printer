package com.jobsity.bowling.models;

public class FinalMatchFrame extends MatchFrame {

    public FinalMatchFrame(PinCount firstShot, PinCount secondShot) {
        super(firstShot);
        shots.add(secondShot);
    }

    @Override
    public int getFrameScore() {
        return getShotsScore();
    }
}
