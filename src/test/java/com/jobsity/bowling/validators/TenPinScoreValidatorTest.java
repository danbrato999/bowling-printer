package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TenPinScoreValidatorTest {
    private final TenPinScoreValidator validator = new TenPinScoreValidator();

    @Test
    void validateMatchFrame() {
        PinCount count1 = new PinCount("10", 10);
        PinCount count2 = new PinCount("3", 3);

        MatchFrame negative = new AccumulatedMatchFrame(count1, -20);
        assertThrows(AssertionError.class, () -> validator.validateMatchFrame(negative));

        MatchFrame invalidFrame = new BasicMatchFrame(count1, count2);
        assertThrows(AssertionError.class, () -> validator.validateMatchFrame(invalidFrame));

        MatchFrame strike = new AccumulatedMatchFrame(count1, 20);
        assertDoesNotThrow(() -> validator.validateMatchFrame(strike));
    }

    @Test
    void validateFinalFrame() {
        PinCount count1 = new PinCount("10", 10);
        PinCount count2 = new PinCount("3", 3);
        MatchFrame lastFrame = new FinalMatchFrame(count1, count2);

        assertThrows(AssertionError.class, () -> validator.validateMatchFrame(lastFrame));

        lastFrame.addShot(new PinCount("4", 4));
        assertDoesNotThrow(() -> validator.validateMatchFrame(lastFrame));

        lastFrame.addShot(count2);
        assertThrows(AssertionError.class, () -> validator.validateMatchFrame(lastFrame));
    }

    @Test
    void validatePlayerScore() {
        PlayerScore nullScore = new PlayerScore("", Collections.nCopies(10, null));
        assertThrows(AssertionError.class, () -> validator.validatePlayerScore(nullScore));

        MatchFrame frame = new AccumulatedMatchFrame(new PinCount("5", 5), 0);

        PlayerScore incomplete = new PlayerScore("", Collections.nCopies(5, frame));
        assertThrows(AssertionError.class, () -> validator.validatePlayerScore(incomplete));

        PlayerScore complete = new PlayerScore("", Collections.nCopies(10, frame));
        assertDoesNotThrow(() -> validator.validatePlayerScore(complete));
    }

    @Test
    void isStrikeFrame() {
        MatchFrame frame = new AccumulatedMatchFrame(new PinCount("10", 10), 0);
        assertTrue(validator.isStrike(frame));

        frame.addShot(new PinCount("5", 5));
        assertFalse(validator.isStrike(frame));
    }

    @Test
    void isSplitFrame() {
        MatchFrame frame = new BasicMatchFrame(new PinCount("3", 3), new PinCount("7", 7));
        assertTrue(validator.isSplit(frame));

        frame.addShot(new PinCount("4", 4));
        assertFalse(validator.isSplit(frame));
    }

    @Test
    void testPinCountStrike() {
        assertTrue(validator.isStrike(new PinCount("10", 10)));
        assertFalse(validator.isStrike(new PinCount("10", 0)));
    }

    @Test
    void areSplit() {
        assertTrue(validator.areSplit(new PinCount("7", 7), new PinCount("3", 3)));
        assertFalse(validator.areSplit(new PinCount("7", 7), new PinCount("6", 6)));
    }
}