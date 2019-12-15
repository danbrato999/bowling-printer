package com.jobsity.bowling.models;

import java.util.ArrayList;
import java.util.List;

public class FinalMatchFrame extends MatchFrame {
    private List<PinCount> extraShots;

    public FinalMatchFrame(PinCount firstShot, PinCount secondShot) {
        super(firstShot, secondShot);
        extraShots = new ArrayList<>();
    }

    public PinCount getThirdShot() {
        return extraShots.isEmpty() ? null : extraShots.get(0);
    }

    public void addExtraShot(PinCount extraShot) {
        extraShots.add(extraShot);
    }

    public int getExtraShotsCount() {
        return extraShots.size();
    }

    // TODO Maybe we should remove this and calculate it when needed (?)
    @Override
    public int getScore() {
        return super.getScore() + extraShots.stream()
                .map(PinCount::toInt)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
