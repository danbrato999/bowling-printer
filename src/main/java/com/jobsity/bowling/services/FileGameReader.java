package com.jobsity.bowling.services;

import com.jobsity.bowling.exceptions.InvalidFormatException;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.mappers.IPinCountMapper;
import com.jobsity.bowling.mappers.IPlayerScoreMapper;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileGameReader implements IGameReader {
    private static final Pattern LINE_REGEX = Pattern.compile("^(\\w+)\\s+([0-9]{1,2}|F)");

    private Path path;
    private IPinCountMapper pinCountMapper;
    private IPlayerScoreMapper scoreMapper;

    public FileGameReader(String path, IPinCountMapper pinCountMapper, IPlayerScoreMapper scoreMapper) {
        this.path = Paths.get(path);
        this.pinCountMapper = pinCountMapper;
        this.scoreMapper = scoreMapper;
    }

    @Override
    public BowlingGame readGame() throws SourceNotFoundException {
        try {
            List<String> allLines = Files.readAllLines(path);
            // Stores the players in the order found in the file
            List<String> players = new ArrayList<>();
            Map<String, Deque<PinCount>> scores = new HashMap<>();

            for (String line: allLines) {
                Matcher matcher = LINE_REGEX.matcher(line);
                if (!matcher.matches())
                    throw new InvalidFormatException("Game file has an invalid format. Line " + line);

                String name = matcher.group(1);
                PinCount pinCount = pinCountMapper.fromString(matcher.group(2));

                scores.compute(name, (key, currentPinCount) -> {
                    if (currentPinCount == null) {
                        players.add(key);
                        return singleQueue(pinCount);
                    } else {
                        currentPinCount.addLast(pinCount);
                        return currentPinCount;
                    }
                });
            }

            return toBowlingGame(players, scores);
        } catch (IOException e) {
            throw new SourceNotFoundException(e);
        }
    }

    private BowlingGame toBowlingGame(List<String> sortedPlayers, Map<String, Deque<PinCount>> playersAndScores) {
        List<PlayerScore> playerScores = sortedPlayers
                .stream()
                .map(name -> {
                    Deque<PinCount> scores = playersAndScores.get(name);
                    return scoreMapper.toPlayerScore(name, scores);
                }).collect(Collectors.toList());

        return new BowlingGame(playerScores);
    }

    private static Deque<PinCount> singleQueue(PinCount pinCount) {
        Deque<PinCount> deque = new LinkedList<>();
        deque.addLast(pinCount);
        return deque;
    }
}
