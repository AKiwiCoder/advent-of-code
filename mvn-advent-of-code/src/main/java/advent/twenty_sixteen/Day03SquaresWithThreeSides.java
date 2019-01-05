package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Triple;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day03SquaresWithThreeSides implements DailyProblem<Integer, Integer> {
    private static final Function<String, Triple<Integer, Integer, Integer>> TO_TRIPLE_INT = line -> {
        String[] bits = line.trim().split("\\s+");
        return new Triple<>(Integer.parseInt(bits[0]), Integer.parseInt(bits[1]), Integer.parseInt(bits[2]));
    };

    private final int part1Answer;
    private final int part2Answer;

    public Day03SquaresWithThreeSides(String filename) {
        List<String> input = FileUtilities.readLines(filename, Parsers::TO_STRING);

        List<Triple<Integer, Integer, Integer>> digits = input.stream().map(TO_TRIPLE_INT).collect(Collectors.toList());

        this.part1Answer = (int) digits.stream().filter(t -> isValid(t)).count();

        int count = 0;
        for (int i = 0; i < digits.size(); i += 3) {
            if (isValid(digits.get(i).getFirst(), digits.get(i + 1).getFirst(), digits.get(i + 2).getFirst()))
                count++;
            if (isValid(digits.get(i).getSecond(), digits.get(i + 1).getSecond(), digits.get(i + 2).getSecond()))
                count++;
            if (isValid(digits.get(i).getThird(), digits.get(i + 1).getThird(), digits.get(i + 2).getThird()))
                count++;
        }

        this.part2Answer = count;
    }

    private boolean isValid(Triple<Integer, Integer, Integer> triple) {
        int a = triple.getFirst();
        int b = triple.getSecond();
        int c = triple.getThird();
        return isValid(a, b, c);
    }

    private boolean isValid(int a, int b, int c) {
        if (a > b && a > c) {
            return a < b + c;
        }
        if (b > a && b > c) {
            return b < a + c;
        }
        return c < a + b;
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
