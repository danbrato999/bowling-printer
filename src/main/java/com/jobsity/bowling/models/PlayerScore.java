package com.jobsity.bowling.models;

import java.util.List;

public class PlayerScore {
    private String playerName;

    private List<MatchFrame> matchFrames;

    public PlayerScore(String playerName, List<MatchFrame> matchFrames) {
        this.playerName = playerName;
        this.matchFrames = matchFrames;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<MatchFrame> getMatchFrames() {
        return matchFrames;
    }

    public void setMatchFrames(List<MatchFrame> matchFrames) {
        this.matchFrames = matchFrames;
    }

    public int getPlayerScore() {
        return matchFrames
                .stream()
                .map(MatchFrame::getFrameScore)
                .reduce(0, Integer::sum);
    }
}
