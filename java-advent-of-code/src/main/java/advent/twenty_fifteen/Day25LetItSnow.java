package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

public class Day25LetItSnow implements DailyProblem<Long, Long> {
    private final long part1Answer;
    private final long part2Answer;

    public Day25LetItSnow(String filename) {
        String input = FileUtilities.readLines(filename, Parsers::TO_STRING).get(0);
        String[] bits = input.split(" ");

        int targetRow = Integer.parseInt(bits[16].substring(0, bits[16].length() - 1)) - 1;
        int targetCol = Integer.parseInt(bits[18].substring(0, bits[18].length() - 1)) - 1;

        boolean finished = false;
        int r = 1;
        int c = 0;
        long previous = 20151125;
        while (!finished) {
            previous = (previous * 252533) % 33554393;
            r--;
            c++;
            if (r < 0) {
                r = c;
                c = 0;
            }
            finished = (r == targetRow) && (c == targetCol);
        }
        previous = (previous * 252533) % 33554393;

        this.part1Answer = previous;
        this.part2Answer = 0;
    }


    @Override
    public Long getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        throw new UnsupportedOperationException("No Part 2");
    }
}
