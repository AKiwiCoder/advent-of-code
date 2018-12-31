package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

public class Day01NotQuiteLisp implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;

    public Day01NotQuiteLisp(String filename) {
        String line = FileUtilities.readLines(filename, Parsers::TO_STRING).get(0);

        int floor = 0;
        int position = 0;
        int firstBasementIndex = -1;
        for (char c : line.toCharArray()) {
            if (c == '(') {
                floor++;
            }
            if (c == ')') {
                floor--;
            }
            if (floor == -1 && firstBasementIndex == -1) {
                firstBasementIndex = position;
            }
            position++;
        }

        this.part1Answer = floor;
        this.part2Answer = firstBasementIndex+1;
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
