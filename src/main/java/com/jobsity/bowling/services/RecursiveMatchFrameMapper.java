package com.jobsity.bowling.services;

import com.jobsity.bowling.models.*;

import java.util.*;

public class RecursiveMatchFrameMapper implements IMatchFrameMapper {
    @Override
    public List<MatchFrame> toMatchFrameList(Deque<PinCount> pinCounts) {
        return getMatchFrames(new ArrayList<>(), pinCounts);
    }

    private static List<MatchFrame> getMatchFrames(List<MatchFrame> currentList, Deque<PinCount> pinsCount) throws NoSuchElementException {
        PinCount firstShot = pinsCount.pop();
        PinCount secondShot = pinsCount.pop();

        if (currentList.size() == 9) {
            FinalMatchFrame frame = new FinalMatchFrame(firstShot, secondShot);

            if (!pinsCount.isEmpty())
                frame.addExtraShot(pinsCount.pop());

            currentList.add(frame);
            return currentList;
        }

        MatchFrame frame;

        if (firstShot.toInt() == 10) {
            int extraScore = secondShot.toInt() + pinsCount.getFirst().toInt();
            frame = new AccumulatedMatchFrame(firstShot, extraScore);
            // Return the popped shot, as it will be the first one of the next frame
            pinsCount.addFirst(secondShot);
        } else {
            if (firstShot.toInt() + secondShot.toInt() == 10)
                frame = new AccumulatedMatchFrame(firstShot, pinsCount.getFirst().toInt())
                        .setSecondShot(secondShot);
            else
                frame = new BasicMatchFrame(firstShot, secondShot);
        }

        currentList.add(frame);
        return getMatchFrames(currentList, pinsCount);
    }
}
