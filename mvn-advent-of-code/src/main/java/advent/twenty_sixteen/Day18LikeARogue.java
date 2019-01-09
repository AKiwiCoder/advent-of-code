package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.lang.reflect.Array;

public class Day18LikeARogue implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day18LikeARogue(String filename, int part1Length, int part2Length) {
        String initial = FileUtilities.readLines(filename, Parsers::TO_STRING).get(0);

        char[][] positions = new char[part2Length][];
        positions[0] = initial.toCharArray();

        int initialSafeCount = 0;
        int initialTrapCount = 0;
        for (int t = 0; t != positions[0].length; t++) {
            if (positions[0][t] == '.') {
                initialSafeCount++;
            } else {
                initialTrapCount++;
            }
        }
        Pair<Integer, Integer> count1 = populateFloors(1, part1Length, positions);
        Pair<Integer, Integer> count2 = populateFloors(part1Length, part2Length, positions);

        this.part1Answer = initialSafeCount + count1.getFirst();
        this.part2Answer = initialSafeCount + count1.getFirst() + count2.getFirst();
    }

    private Pair<Integer, Integer> populateFloors(int start, int end, char[][] positions) {
        int safeCount = 0;
        int trapCount = 0;
        for (int i = start; i != end; i++) {
            char[] floor = new char[positions[i-1].length];
            for (int p = 0; p != positions[i - 1].length; p++) {
                if (isTrap(p, positions[i - 1])) {
                    floor[p] = '^';
                    trapCount++;
                } else {
                    floor[p] = '.';
                    safeCount++;
                }
            }
            positions[i] = floor;
        }
        return new Pair<>(safeCount, trapCount);
    }


    public static boolean isTrap(int index, char[] text) {
        char left = (index >= 1) ? text[index - 1] : '.';
        char center = text[index];
        char right = (index <= text.length - 2) ? text[index + 1] : '.';
        // Its left and center tiles are traps, but its right tile is not.
        if (left == '^' && center == '^' && right == '.') {
            return true;
        }
        // Its center and right tiles are traps, but its left tile is not.
        if (left == '.' && center == '^' && right == '^') {
            return true;
        }
        // Only its left tile is a trap.
        if (left == '^' && center == '.' && right == '.') {
            return true;
        }
        // Only its right tile is a trap.
        if (left == '.' && center == '.' && right == '^') {
            return true;
        }
        return false;
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
