package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlersOfTheNorthPole implements DailyProblem<Long, Long> {
    private static final long LIMIT = 2000;

    private final long part1Answer;
    private final long part2Answer;

    public SettlersOfTheNorthPole(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::ToStringNoTrim);

        char[][] map = new char[lines.size()][];
        char[][] working = new char[lines.size()][];

        for (int i = 0; i != lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
            working[i] = lines.get(i).toCharArray();
        }

        char[][] temp = map;
        Map<Long, Long> cycleDetector = new HashMap<>();
        for (long i = 1; i != LIMIT; i++) {
            process(map, working);

            long result = (count(map, '#') * count(map, '|'));

            cycleDetector.put(i, result);

            temp = working;
            working = map;
            map = temp;
        }

        long cycleStartIndex = 1000;
        long cycleStartValue = cycleDetector.get(cycleStartIndex);
        long cycleEndIndex = 1000;

        for (long i = cycleStartIndex + 1; i != LIMIT; i++) {
            if (cycleDetector.get(i) == cycleStartValue) {
                cycleEndIndex = i;
                break;
            }
        }

        long index2 = (1000000000 - 999) % (cycleEndIndex - cycleStartIndex) + 1000;

        this.part1Answer = cycleDetector.get(11l);
        this.part2Answer = cycleDetector.get(index2);
    }

    private static void process(char[][] input, char[][] output) {
        clear(output);

        for (int y = 0; y != input.length; y++) {
            for (int x = 0; x != input[y].length; x++) {

                if (tree(input, y, x)) {
                    if (count(input, y, x, '#') >= 3)
                        output[y][x] = '#';
                    else
                        output[y][x] = '|';
                }
                if (open(input, y, x)) {
                    if (count(input, y, x, '|') >= 3)
                        output[y][x] = '|';
                    else
                        output[y][x] = '.';
                }
                if (lumberyard(input, y, x)) {
                    int yardCount = count(input, y, x, '#');
                    int treeCount = count(input, y, x, '|');
                    if (yardCount >= 1 && treeCount >= 1)
                        output[y][x] = '#';
                    else
                        output[y][x] = '.';
                }
            }

        }
    }

    private static int count(char[][] map, char type) {
        int count = 0;
        for (int y = 0; y != map.length; y++) {
            for (int x = 0; x != map[y].length; x++) {
                if (map[y][x] == type) {
                    count++;
                }
            }
        }
        return count;
    }


    private static int count(char[][] input, int y, int x, char type) {
        int count = 0;
        if (contains(input, y - 1, x - 1, type)) {
            count++;
        }
        if (contains(input, y - 1, x, type)) {
            count++;
        }
        if (contains(input, y - 1, x + 1, type)) {
            count++;
        }
        if (contains(input, y, x - 1, type)) {
            count++;
        }
        if (contains(input, y, x + 1, type)) {
            count++;
        }
        if (contains(input, y + 1, x - 1, type)) {
            count++;
        }
        if (contains(input, y + 1, x, type)) {
            count++;
        }
        if (contains(input, y + 1, x + 1, type)) {
            count++;
        }
        return count;
    }

    private static boolean tree(char[][] input, int y, int x) {
        return contains(input, y, x, '|');
    }

    private static boolean open(char[][] input, int y, int x) {
        return contains(input, y, x, '.');
    }

    private static boolean lumberyard(char[][] input, int y, int x) {
        return contains(input, y, x, '#');
    }

    private static boolean contains(char[][] input, int y, int x, char c) {
        if (y < 0 || x < 0 || y >= input.length || x >= input[y].length) {
            return false;
        }
        return input[y][x] == c;
    }

    private static void clear(char[][] map) {
        for (int y = 0; y != map.length; y++) {
            for (int x = 0; x != map[y].length; x++) {
                map[y][x] = ' ';
            }
        }
    }

    @Override
    public Long getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        return part2Answer;
    }
}
