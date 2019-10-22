package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day01InverseCaptcha implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day01InverseCaptcha(String filename) {
        List<String> input = FileUtilities.readLines(filename, String::trim);

        this.part1Answer = calculatePart1(input);
        this.part2Answer = calculatePart2(input);
    }

    private int calculatePart1(List<String> input) {
        int total1 = 0;
        for (String line : input) {
            int x = 0;
            for (int i = 0; i < line.length() - 1; i++) {
                if (line.charAt(i) == line.charAt(i+1)) {
                    int x1 = Integer.parseInt(Character.toString(line.charAt(i)));
                    x += x1;
                }
            }
            if (line.charAt(0) == line.charAt(line.length() - 1)) {
                x += Integer.parseInt(Character.toString(line.charAt(0)));
            }
            total1 += x;
        }
        return total1;
    }

    private int calculatePart2(List<String> input) {
        int total1 = 0;
        for (String line : input) {
            int x = 0;
            int len = line.length() / 2;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == line.charAt((i+len) % line.length())) {
                    int x1 = Integer.parseInt(Character.toString(line.charAt(i)));
                    x += x1;
                }
            }
            total1 += x;
        }
        return total1;
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
