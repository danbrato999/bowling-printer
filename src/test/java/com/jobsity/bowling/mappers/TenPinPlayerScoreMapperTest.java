package com.jobsity.bowling.mappers;

import com.jobsity.bowling.exceptions.InvalidGameException;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;
import com.jobsity.bowling.validators.IGameValidator;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TenPinPlayerScoreMapperTest {
    private static final String DEFAULT_PLAYER = "player1";
    private static final TenPinCountMapper mapper = new TenPinCountMapper();

    @Test
    void mapPerfectGame() {
        PinCount strike = mapper.fromString("10");
        Deque<PinCount> queue = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            queue.add(strike);
        }

        IGameValidator gameValidator = mock(IGameValidator.class);
        doNothing().when(gameValidator).validateMatchFrame(any());
        doNothing().when(gameValidator).validatePlayerScore(any());
        when(gameValidator.isStrike(strike)).thenReturn(true);

        TenPinPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(gameValidator);
        PlayerScore score = scoreMapper.toPlayerScore(DEFAULT_PLAYER, queue);

        assertEquals(DEFAULT_PLAYER, score.getPlayerName(), "Wrong player score name");
        assertScoreAndFinalFrameShots(score, 300, 3);
    }

    @Test
    void mapWorstGame() {
        PinCount f = mapper.fromString("F");
        Deque<PinCount> queue = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            queue.add(f);
        }

        IGameValidator gameValidator = mock(IGameValidator.class);
        doNothing().when(gameValidator).validateMatchFrame(any());
        doNothing().when(gameValidator).validatePlayerScore(any());

        TenPinPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(gameValidator);
        PlayerScore score = scoreMapper.toPlayerScore(DEFAULT_PLAYER, queue);

        assertScoreAndFinalFrameShots(score, 0, 2);
    }

    @Test
    void mapSplitGame() {
        PinCount five = mapper.fromString("5");
        Deque<PinCount> queue = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            queue.add(five);
        }

        IGameValidator gameValidator = mock(IGameValidator.class);
        doNothing().when(gameValidator).validateMatchFrame(any());
        doNothing().when(gameValidator).validatePlayerScore(any());
        when(gameValidator.areSplit(five, five)).thenReturn(true);

        TenPinPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(gameValidator);
        PlayerScore score = scoreMapper.toPlayerScore(DEFAULT_PLAYER, queue);
        assertScoreAndFinalFrameShots(score, 145, 2);
    }

    @Test
    void failIncompleteGame() {
        IGameValidator gameValidator = mock(IGameValidator.class);
        doNothing().when(gameValidator).validateMatchFrame(any());
        doNothing().when(gameValidator).validatePlayerScore(any());

        Deque<PinCount> queue = new LinkedList<>();
        queue.addFirst(mapper.fromString("5"));
        queue.addFirst(mapper.fromString("6"));
        queue.addFirst(mapper.fromString("7"));

        TenPinPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(gameValidator);
        assertThrows(InvalidGameException.class,() -> scoreMapper.toPlayerScore(DEFAULT_PLAYER, queue));
    }

    private void assertScoreAndFinalFrameShots(PlayerScore score, int expectedScore, int finalFrameShots) {
        assertEquals(10, score.getMatchFrames().size(), "Wrong number of frames in player's game");
        assertEquals(expectedScore, score.getPlayerScore(), "Wrong total score for game");
        assertEquals(finalFrameShots, score.getMatchFrames().get(score.getMatchFrames().size() - 1).shotsCount(), "Wrong number of attempts at final frame");
    }
}