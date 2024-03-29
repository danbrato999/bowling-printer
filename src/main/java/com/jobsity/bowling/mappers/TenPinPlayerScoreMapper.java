package com.jobsity.bowling.mappers;

import com.jobsity.bowling.exceptions.InvalidGameException;
import com.jobsity.bowling.models.*;
import com.jobsity.bowling.validators.IGameValidator;

import java.util.*;

public class TenPinPlayerScoreMapper implements IPlayerScoreMapper {
    private IGameValidator validator;

    public TenPinPlayerScoreMapper(IGameValidator validator) {
        this.validator = validator;
    }

    @Override
    public PlayerScore toPlayerScore(String playerName, Deque<PinCount> pinCounts) {
        try {
            List<MatchFrame> playerFrames = getMatchFrames(new ArrayList<>(), new LinkedList<>(pinCounts));
            PlayerScore playerScore = new PlayerScore(playerName, playerFrames);
            validator.validatePlayerScore(playerScore);
            return playerScore;
        } catch (NoSuchElementException e) {
            throw new InvalidGameException("Provided an invalid number of shots per player");
        }
    }

    private List<MatchFrame> getMatchFrames(List<MatchFrame> currentList, Deque<PinCount> pinCounts) {
        boolean isLastFrame = currentList.size() == 9;
        MatchFrame frame = buildFrame(pinCounts, isLastFrame);
        validator.validateMatchFrame(frame);
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
            while (!pinCounts.isEmpty()) frame.addShot(pinCounts.pop());

            return frame;
        }

        // Check if it's strike
        if (validator.isStrike(firstShot)) {
            int extraScore = secondShot.getScore() + pinCounts.getFirst().getScore();
            // Return the popped shot, as it will be the first one of the next frame
            pinCounts.addFirst(secondShot);
            return new AccumulatedMatchFrame(firstShot, extraScore);
        }

        // Check if it's split
        if (validator.areSplit(firstShot, secondShot))
            return new AccumulatedMatchFrame(firstShot, pinCounts.getFirst().getScore())
                    .addShot(secondShot);

        return new BasicMatchFrame(firstShot, secondShot);
    }

}
