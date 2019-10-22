package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day09StreamProcessing implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day09StreamProcessing(String filename) {
        List<String> garbages = FileUtilities.readLines(filename, String::trim);

        int totalGroups = 0;
        int totalRemove = 0;

        for (String garbage : garbages) {
            totalGroups += scoreGarbage(garbage);
            totalRemove += removeGarbage(garbage);
        }

        this.part1Answer = totalGroups;
        this.part2Answer = totalRemove;
    }

    private int scoreGarbage(String garbage) {
        int index = 0;
        int score = 0;
        int depth = 0;
        boolean inGarbage = false;
        while (index < garbage.length()) {
            char c = garbage.charAt(index);
            if (c == '!') {
                index += 2;
                continue;
            }

            if (!inGarbage && c == '<') {
                inGarbage = true;
            }

            if (inGarbage) {
                if (c == '>') {
                    inGarbage = false;
                }
            } else {
                if (c == '{') {
                    depth++;
                }
                if (c == '}') {
                    score += depth;
                    depth--;
                }
            }
            index++;
        }
        return score;
    }

    private int removeGarbage(String garbage) {
        int index = 0;
        int score = 0;
        boolean inGarbage = false;
        while (index < garbage.length()) {
            char c = garbage.charAt(index);
            if (c == '!') {
                index += 2;
                continue;
            }

            if (!inGarbage && c == '<') {
                inGarbage = true;
            } else if (inGarbage) {
                if (c == '>') {
                    inGarbage = false;
                } else {
                    score++;
                }
            }
            index++;
        }
        return score;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
