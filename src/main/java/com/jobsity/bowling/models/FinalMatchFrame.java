package com.jobsity.bowling.models;

public class FinalMatchFrame extends MatchFrame {
    private int thirdShot;

    public int getThirdShot() {
        return thirdShot;
    }

    public void setThirdShot(int thirdShot) {
        this.thirdShot = thirdShot;
    }

    @Override
    public MatchFrame withComputedScore() {
        setScore(firstShot + secondShot + thirdShot);
        return this;
    }
}
