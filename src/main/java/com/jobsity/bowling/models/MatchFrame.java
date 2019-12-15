package com.jobsity.bowling.models;

public abstract class MatchFrame implements IScoredFrame {
    protected PinCount firstShot;
    protected PinCount secondShot;

    public MatchFrame(PinCount firstShot) {
        this.firstShot = firstShot;
    }

    public PinCount getFirstShot() {
        return firstShot;
    }

    public PinCount getSecondShot() {
        return secondShot;
    }

    public MatchFrame setSecondShot(PinCount secondShot) {
        this.secondShot = secondShot;
        return this;
    }

    @Override
    public int shotsCount() {
        return secondShot == null ? 1 : 2;
    }

    @Override
    public int getShotsScore() {
        int secondShotScore = secondShot != null ? secondShot.getScore() : 0;
        return firstShot.getScore() + secondShotScore;
    }
}
