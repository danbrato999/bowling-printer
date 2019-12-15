package com.jobsity.bowling.models;

public class MatchFrame {
    protected PinCount firstShot;
    protected PinCount secondShot;

    public MatchFrame() {
        this.firstShot = PinCount.ZERO;
        this.secondShot = PinCount.ZERO;
    }

    public MatchFrame(PinCount firstShot, PinCount secondShot) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
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

    public int getScore() {
        return firstShot.toInt() + secondShot.toInt();
    }
}
