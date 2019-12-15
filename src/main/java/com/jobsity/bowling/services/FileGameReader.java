package com.jobsity.bowling.services;

import com.jobsity.bowling.SourceNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileGameReader {
    private static final Pattern LINE_REGEX = Pattern.compile("^(\\w+)\\s+([0-9]{1,2}|F)");
    private Path path;

    public FileGameReader(String path) {
        this.path = Paths.get(path);
    }

    public Map<String, Deque<Integer>> readGame() throws SourceNotFoundException {
        try {
            List<String> allLines = Files.readAllLines(path);

            return allLines.stream()
                    .map(line -> {
                        Matcher matcher = LINE_REGEX.matcher(line);
                        if (!matcher.matches())
                            throw new IllegalArgumentException();

                        return new NameAndScore(matcher.group(1), matcher.group(2));
                    })
                    .collect(
                            Collectors.toMap(
                                    NameAndScore::getName,
                                    nameAndScore -> {
                                        Deque<Integer> deque = new LinkedList<>();
                                        deque.addLast(nameAndScore.score);
                                        return deque;
                                    },
                                    (a, b) -> {
                                        a.addAll(b);
                                        return a;
                                    }
                            )
                    );
        } catch (IOException e) {
            throw new SourceNotFoundException(e);
        }
    }

    private static class NameAndScore {
        private String name;
        private int score;

        private NameAndScore(String name, String score) {
            this.name = name;

            if ("F".equals(score))
                this.score = 0;
            else
                this.score = Integer.parseInt(score);
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}