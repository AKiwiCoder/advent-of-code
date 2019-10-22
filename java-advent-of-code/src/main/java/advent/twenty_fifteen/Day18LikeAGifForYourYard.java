package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

public class Day18LikeAGifForYourYard implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day18LikeAGifForYourYard(String filename, int steps, int steps2) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING_NO_TRIM);

        char[][] grid = ArrayUtilities.createCharArray(lines.get(0).length(), lines.size(), (x, y) -> lines.get(y).charAt(x));
        char[][] working = ArrayUtilities.createCharArray(lines.get(0).length(), lines.size(), (x, y) -> '.');

        char[][] current = grid;
        for (int i = 0; i != steps; i++) {
            char[][] temp = current;
            current = iterateGrid(current, working);
            working = temp;
        }

        this.part1Answer = ArrayUtilities.count('#', current);

        // Reset for Part 2
        grid = ArrayUtilities.createCharArray(lines.get(0).length(), lines.size(), (x, y) -> lines.get(y).charAt(x));
        working = ArrayUtilities.createCharArray(lines.get(0).length(), lines.size(), (x, y) -> '.');

        current = grid;
        turnOnFourCorners(current);

        for (int i = 0; i != steps2; i++) {
            char[][] temp = current;
            current = iterateGrid(current, working);
            working = temp;
            turnOnFourCorners(current);
        }

        this.part2Answer = ArrayUtilities.count('#', current);
    }

    private void turnOnFourCorners(char[][] current) {
        current[0][0] = '#';
        current[current.length - 1][0] = '#';
        current[0][current[0].length - 1] = '#';
        current[current.length - 1][current[0].length - 1] = '#';
    }

    private static int[] OFFSETS = {-1, 0, +1};

    private char[][] iterateGrid(char[][] from, char[][] to) {
        for (int row = 0; row != from.length; row++) {
            for (int col = 0; col != from[row].length; col++) {
                int count = 0;
                for (int dRow : OFFSETS) {
                    for (int dCol : OFFSETS) {
                        if (!(dRow == 0 && dCol == 0) && isOn(dRow + row, dCol + col, from)) {
                            count++;
                        }
                    }
                }

                to[row][col] = '.';
                if (isOn(row, col, from)) {
                    // Light is currently on
                    if (count == 2 || count == 3) {
                        to[row][col] = '#';
                    }
                } else {
                    // Light is currently off
                    if (count == 3) {
                        to[row][col] = '#';
                    }
                }
            }
        }
        return to;
    }

    private boolean isOn(int row, int col, char[][] input) {
        if (row < 0 || col < 0) {
            return false;
        }
        if (row >= input.length || col >= input[row].length) {
            return false;
        }
        return input[row][col] == '#';
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
