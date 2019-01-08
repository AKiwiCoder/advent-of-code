package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.ArrayUtilities;
import advent.utilities.StringUtilities;

public class Day13AMazeOfTwistyLittleCubicles implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private char calculate(int seed, int x, int y) {
        int step1 = x * x + 3 * x + 2 * x * y + y + y * y;
        int step2 = step1 + seed;
        int count = StringUtilities.countOccurrences(Integer.toBinaryString(step2), '1');
        if (count % 2 == 0) {
            return '.'; // Open space
        }
        return '#'; // Wall
    }

    public Day13AMazeOfTwistyLittleCubicles(int seed, int targetCol, int targetRow) {
        char[][] map = ArrayUtilities.createCharArray(targetCol + 10, targetRow + 50, (x, y) -> calculate(seed, x, y));
        int[][] distances = ArrayUtilities.createIntArray(targetCol + 10, targetRow + 50, (x, y) -> Integer.MAX_VALUE);

        fillDistanceMap(map, distances, 1, 1, 0);

        this.part1Answer = distances[targetRow][targetCol];
        this.part2Answer = ArrayUtilities.count(distances, d -> d <= 50);
    }

    private void fillDistanceMap(char[][] map, int[][] distances, int row, int col, int step) {
        if (!ArrayUtilities.isValidCoordinates(map, row, col)) {
            return;
        }
        if (distances[row][col] < step) {
            return;
        }
        if (map[row][col] == '#') {
            return;
        }
        distances[row][col] = step;
        fillDistanceMap(map, distances, row - 1, col, step + 1);
        fillDistanceMap(map, distances, row, col - 1, step + 1);
        fillDistanceMap(map, distances, row + 1, col, step + 1);
        fillDistanceMap(map, distances, row, col + 1, step + 1);
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