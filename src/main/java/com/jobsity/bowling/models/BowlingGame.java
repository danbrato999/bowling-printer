package com.jobsity.bowling.models;

import java.util.*;

public class BowlingGame {
    private List<PlayerScore> playerScores;

    public BowlingGame(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
    }

    public List<PlayerScore> getPlayerScores() {
        return playerScores;
    }
}
