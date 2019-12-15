package com.jobsity.bowling.services;

import com.jobsity.bowling.models.FinalMatchFrame;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PlayerScore;

import java.util.*;
import java.util.stream.Collectors;

public class MatchBuilder {
    public static List<PlayerScore> buildMatch(Map<String, Deque<Integer>> playersWithScores) {
        return playersWithScores.entrySet()
                .stream()
                .map(scoreEntry -> {
                    PlayerScore playerScore = new PlayerScore();
                    playerScore.setPlayerName(scoreEntry.getKey());
                    playerScore.setMatchFrames(getMatchFrames(new ArrayList<>(), scoreEntry.getValue()));
                    return playerScore;
                }).collect(Collectors.toList());
    }

    private static List<MatchFrame> getMatchFrames(List<MatchFrame> currentList, Deque<Integer> pinsCount) throws NoSuchElementException, NullPointerException {
        Integer currentCount = pinsCount.pop();
        Integer secondShot = pinsCount.pop();

        if (currentList.size() == 9) {
            FinalMatchFrame frame = new FinalMatchFrame();
            frame.setFirstShot(currentCount);
            frame.setSecondShot(secondShot);

            if (!pinsCount.isEmpty())
                frame.setThirdShot(pinsCount.pop());

            currentList.add(frame.withComputedScore());
            return currentList;
        }

        MatchFrame frame = new MatchFrame();
        frame.setFirstShot(currentCount);

        if (currentCount == 10) {
            frame.setScore(currentCount + secondShot + pinsCount.getFirst());
            pinsCount.addFirst(secondShot);
        } else {
            frame.setSecondShot(secondShot);
            if (currentCount + secondShot == 10)
                frame.setScore(10 + pinsCount.getFirst());
            else
                frame.withComputedScore();
        }

        currentList.add(frame);
        return getMatchFrames(currentList, pinsCount);
    }
}
