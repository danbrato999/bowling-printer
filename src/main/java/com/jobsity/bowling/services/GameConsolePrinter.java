package com.jobsity.bowling.services;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.IScoreEncoder;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.models.PlayerScore;

public class GameConsolePrinter implements IGamePrinter {
    private static final String DEFAULT_SEPARATOR = "\t";
    private IFrameEncoder frameEncoder;
    private IScoreEncoder scoreEncoder;

    public GameConsolePrinter(IFrameEncoder frameEncoder, IScoreEncoder scoreEncoder) {
        this.frameEncoder = frameEncoder;
        this.scoreEncoder = scoreEncoder;
    }

    @Override
    public void printGame(BowlingGame game) {
        System.out.print("Frame" + DEFAULT_SEPARATOR + DEFAULT_SEPARATOR);
        for (int i = 1; i < 11; i++)
            System.out.printf("%1$d%2$s%2$s", i, DEFAULT_SEPARATOR);
        System.out.print("\n");

        game.getPlayerScores()
                .forEach(this::printPlayerScore);
    }

    private void printPlayerScore(PlayerScore playerScore) {
        System.out.println(playerScore.getPlayerName());
        System.out.printf("Pinfalls%2$s%1$s\n", frameEncoder.encode(playerScore.getMatchFrames(), DEFAULT_SEPARATOR), DEFAULT_SEPARATOR);
        System.out.printf("Score%2$s%2$s%1$s\n", scoreEncoder.encode(playerScore.getMatchFrames(), DEFAULT_SEPARATOR), DEFAULT_SEPARATOR);
    }
}
