package com.jobsity.bowling.mappers;

import com.jobsity.bowling.models.*;
import com.jobsity.bowling.validators.IScoreValidator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class TenPinPlayerScoreMapper implements IPlayerScoreMapper {
    private IScoreValidator gameConfigurator;

    public TenPinPlayerScoreMapper(IScoreValidator gameConfigurator) {
        this.gameConfigurator = gameConfigurator;
    }

    @Override
    public PlayerScore toPlayerScore(String playerName, Deque<PinCount> pinCounts) {
        List<MatchFrame> playerFrames = getMatchFrames(new ArrayList<>(), pinCounts);
        PlayerScore playerScore = new PlayerScore();
        playerScore.setPlayerName(playerName);
        playerScore.setMatchFrames(playerFrames);

        gameConfigurator.validatePlayerScore(playerScore);
        return playerScore;
    }

    private List<MatchFrame> getMatchFrames(List<MatchFrame> currentList, Deque<PinCount> pinCounts) throws NoSuchElementException {
        boolean isLastFrame = currentList.size() == 9;
        MatchFrame frame = buildFrame(pinCounts, isLastFrame);
        gameConfigurator.validateMatchFrame(frame);
        currentList.add(frame);

        if (isLastFrame)
            return currentList;

        return getMatchFrames(currentList, pinCounts);
    }

    private MatchFrame buildFrame(Deque<PinCount> pinCounts, boolean isFinal) {
        PinCount firstShot = pinCounts.pop();
        PinCount secondShot = pinCounts.pop();

        // If is the last frame, add extra attempts
        if (isFinal) {
            FinalMatchFrame frame = new FinalMatchFrame(firstShot, secondShot);
            while (!pinCounts.isEmpty()) frame.addExtraShot(pinCounts.pop());

            return frame;
        }

        // Check if it's strike
        if (firstShot.toInt() == 10) {
            int extraScore = secondShot.toInt() + pinCounts.getFirst().toInt();
            // Return the popped shot, as it will be the first one of the next frame
            pinCounts.addFirst(secondShot);
            return new AccumulatedMatchFrame(firstShot, extraScore);
        }

        // Check if it's split
        if (firstShot.toInt() + secondShot.toInt() == 10)
            return new AccumulatedMatchFrame(firstShot, pinCounts.getFirst().toInt())
                    .setSecondShot(secondShot);

        return new BasicMatchFrame(firstShot, secondShot);
    }

}
