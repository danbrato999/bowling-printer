package com.jobsity.bowling.services;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PlayerScore;

import java.util.List;
import java.util.stream.Collectors;

public class MatchPrinter {
    private IFrameEncoder frameEncoder;

    public MatchPrinter(IFrameEncoder frameEncoder) {
        this.frameEncoder = frameEncoder;
    }

    public void printMatch(List<PlayerScore> playerScores) {
        printHeader();
        playerScores.forEach(this::printPlayerScore);
    }

    private void printPlayerScore(PlayerScore playerScore) {
        System.out.println(playerScore.getPlayerName());
        String pinData = playerScore.getMatchFrames()
                .stream()
                .map(frameEncoder::encode)
                .collect(Collectors.joining());

        System.out.println("Pinfalls\t" + pinData);

        System.out.print("Score\t\t");
        printScoreData(0, playerScore.getMatchFrames().stream().map(MatchFrame::getScore).collect(Collectors.toList()));
    }

    private void printScoreData(int score, List<Integer> scoresLeft) {
        if (!scoresLeft.isEmpty()) {
            int newScore = score + scoresLeft.remove(0);
            System.out.printf("%d\t\t", newScore);
            printScoreData(newScore, scoresLeft);
        } else
            System.out.print("\n");
    }

    private void printHeader() {
        System.out.print("Frame\t\t");
        for (int i = 1; i < 11; i++)
            System.out.printf("%d\t\t", i);
        System.out.print("\n");
    }
}
