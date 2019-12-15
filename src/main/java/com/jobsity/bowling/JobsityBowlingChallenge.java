package com.jobsity.bowling;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.TenPinConsoleEncoder;
import com.jobsity.bowling.models.PlayerScore;
import com.jobsity.bowling.services.FileGameReader;
import com.jobsity.bowling.services.MatchBuilder;
import com.jobsity.bowling.services.MatchPrinter;

import java.util.*;

public class JobsityBowlingChallenge {
    public static void main(String[] args) {
        try {
            Map<String, Deque<Integer>> dataSource = new FileGameReader("src/main/resources/test-game.txt")
                    .readGame();
            IFrameEncoder encoder = new TenPinConsoleEncoder();
            MatchPrinter printer = new MatchPrinter(encoder);

            List<PlayerScore> scores = MatchBuilder.buildMatch(dataSource);
            printer.printMatch(scores);
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }
}
