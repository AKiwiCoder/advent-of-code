package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

public class Day02CorruptionChecksum implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day02CorruptionChecksum(String filename) {
        List<List<Integer>> grid = FileUtilities.readLines(filename, Parsers::TO_INTEGER_LIST);

        this.part1Answer = grid.stream().mapToInt(l -> l.stream().mapToInt(Integer::intValue).max().getAsInt() - l.stream().mapToInt(Integer::intValue).min().getAsInt()).sum();
        this.part2Answer = grid.stream().mapToInt(l -> calculate(l)).sum();
    }

    private int calculate(List<Integer> values) {
        for (int i = 0; i != values.size(); i++) {
            for (int j = 0; j != values.size(); j++) {
                if (i != j) {
                    int a = values.get(i);
                    int b = values.get(j);
                    if (Math.max(a, b) % Math.min(a, b) == 0) {
                        return Math.max(a, b) / Math.min(a, b);
                    }
                }
            }
        }
        return 0;
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
