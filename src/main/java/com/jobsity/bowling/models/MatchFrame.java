package com.jobsity.bowling.models;

public class MatchFrame {
    protected int firstShot;
    protected int secondShot;
    private int score;

    public int getFirstShot() {
        return firstShot;
    }

    public void setFirstShot(int firstShot) {
        this.firstShot = firstShot;
    }

    public int getSecondShot() {
        return secondShot;
    }

    public void setSecondShot(int secondShot) {
        this.secondShot = secondShot;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public MatchFrame withComputedScore() {
        this.score = firstShot + secondShot;
        return this;
    }
}
