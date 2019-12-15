package com.jobsity.bowling.models;

import java.util.ArrayList;
import java.util.List;

public abstract class MatchFrame implements IScoredFrame {
    protected List<PinCount> shots;

    public MatchFrame(PinCount firstShot) {
        shots = new ArrayList<>();
        shots.add(firstShot);
    }

    public MatchFrame addShot(PinCount shot) {
        shots.add(shot);
        return this;
    }

    public List<PinCount> getShots() {
        return shots;
    }

    @Override
    public int shotsCount() {
        return shots.size();
    }

    @Override
    public int getShotsScore() {
        return shots.stream()
                .map(PinCount::getScore)
                .reduce(0, Integer::sum);
    }
}
