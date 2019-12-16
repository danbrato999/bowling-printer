package com.jobsity.bowling.services;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.IScoreEncoder;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.models.PlayerScore;

public class GameConsolePrinter implements IGamePrinter {
    private IFrameEncoder frameEncoder;
    private IScoreEncoder scoreEncoder;
    private String separator;

    public GameConsolePrinter(IFrameEncoder frameEncoder, IScoreEncoder scoreEncoder, String separator) {
        this.frameEncoder = frameEncoder;
        this.scoreEncoder = scoreEncoder;
        this.separator = separator;
    }

    @Override
    public void printGame(BowlingGame game) {
        System.out.print("Frame" + separator + separator);
        for (int i = 1; i < 10; i++)
            System.out.printf("%1$d%2$s%2$s", i, separator);
        System.out.print("10\n");

        game.getPlayerScores()
                .forEach(this::printPlayerScore);
    }

    private void printPlayerScore(PlayerScore playerScore) {
        System.out.println(playerScore.getPlayerName());
        String encodedPins = frameEncoder.encode(playerScore);
        System.out.printf("Pinfalls%2$s%1$s\n", encodedPins, separator);
        String encodedScore = scoreEncoder.encode(playerScore);
        System.out.printf("Score%2$s%2$s%1$s\n", encodedScore, separator);
    }
}
