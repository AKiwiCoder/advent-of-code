package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21FractalArt implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    static class Matcher {
        final char[][] filter;

        Matcher(char[][] filter) {
            this.filter = filter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Matcher matcher = (Matcher) o;
            for (int r = 0; r != filter.length; r++) {
                for (int c = 0; c != filter.length; c++) {
                    if (filter[r][c] != matcher.filter[r][c]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    static class Input {
        int size;
        Set<Matcher> matchers;
    }

    static class Output {
        char[][] pattern;
    }

    public Day21FractalArt(String filename, int steps1, int steps2) {
        List<Pair<Input, Output>> rules = FileUtilities.readLines(filename, this::parse);

        char[][] current = new char[][]{{'.', '#', '.'}, {'.', '.', '#'}, {'#', '#', '#'}};
        char[][] working;

        int part1 = -1;
        for (int i = 0; i < steps2; i++) {
            if (current.length % 2 == 0) {
                working = createNewArray(current.length + current.length / 2);
                updateWorking(rules, current, working, current.length / 2, 2, 3);
            } else if (current.length % 3 == 0) {
                working = createNewArray(current.length + current.length / 3);
                updateWorking(rules, current, working, current.length / 3, 3, 4);
            } else {
                throw new IllegalStateException("Weird array shape " + current.length);
            }

            if (i == steps1) {
                part1 = ArrayUtilities.count('#', current);
            }

            current = working;
        }

        this.part1Answer = part1;
        this.part2Answer = ArrayUtilities.count('#', current);
    }

    private void updateWorking(List<Pair<Input, Output>> rules, char[][] input, char[][] output, int numOfSquares, int inputSize, int outputSize) {
        for (int dr = 0; dr != numOfSquares; dr++) {
            for (int dc = 0; dc != numOfSquares; dc++) {
                processRules(rules, inputSize, dc * inputSize, dr * inputSize, dc * outputSize, dr * outputSize, input, output);
            }
        }
    }

    private void processRules(List<Pair<Input, Output>> rules, int size, int sourceCol, int sourceRow, int destCol, int destRow, char[][] source, char[][] destination) {
        for (Pair<Input, Output> rule : rules) {
            if (rule.getFirst().size == size) {
                if (searchForMatch(rule.getFirst(), sourceCol, sourceRow, source)) {
                    write(rule.getSecond(), destCol, destRow, destination);
                }
            }
        }
    }

    private boolean searchForMatch(Input rule, int col, int row, char[][] source) {
        for (Matcher matcher : rule.matchers) {
            if (match(matcher.filter, col, row, source)) {
                return true;
            }
        }
        return false;
    }

    private boolean match(char[][] filter, int col, int row, char[][] source) {
        if (filter == null) {
            return false;
        }
        for (int r = 0; r != filter.length; r++) {
            for (int c = 0; c != filter.length; c++) {
                if (filter[r][c] != source[row + r][col + c])
                    return false;
            }
        }
        return true;
    }

    private void write(Output second, int col, int row, char[][] destination) {
        for (int r = 0; r != second.pattern.length; r++) {
            for (int c = 0; c != second.pattern.length; c++) {
                destination[row + r][col + c] = second.pattern[r][c];
            }
        }
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }

    private char[][] createNewArray(int size) {
        return ArrayUtilities.createCharArray(size, size, (x, y) -> '.');
    }

    private static char[][] buildNewArray(char[][] input) {
        char[][] result = new char[input.length][];
        for (int r = 0; r != result.length; r++) {
            result[r] = new char[input.length];
        }
        return result;
    }

    private static char[][] spin(char[][] input) {
        char[][] result = buildNewArray(input);

        if (input.length == 2) {
            result[0][0] = input[1][0];
            result[0][1] = input[0][0];
            result[1][0] = input[1][1];
            result[1][1] = input[0][1];
        } else if (input.length == 3) {
            result[0][0] = input[2][0];
            result[0][1] = input[1][0];
            result[0][2] = input[0][0];
            result[1][0] = input[2][1];
            result[1][1] = input[1][1];
            result[1][2] = input[0][1];
            result[2][0] = input[2][2];
            result[2][1] = input[1][2];
            result[2][2] = input[0][2];
        }
        return result;
    }

    private static char[][] horizontal(char[][] input) {
        char[][] result = buildNewArray(input);

        if (input.length == 2) {
            result[0][0] = input[0][1];
            result[0][1] = input[0][0];
            result[1][0] = input[1][1];
            result[1][1] = input[1][0];
        } else if (input.length == 3) {
            result[0][0] = input[0][2];
            result[0][1] = input[0][1];
            result[0][2] = input[0][0];
            result[1][0] = input[1][2];
            result[1][1] = input[1][1];
            result[1][2] = input[1][0];
            result[2][0] = input[2][2];
            result[2][1] = input[2][1];
            result[2][2] = input[2][0];
        }
        return result;
    }

    private static char[][] vertical(char[][] input) {
        char[][] result = buildNewArray(input);

        if (input.length == 2) {
            result[1][0] = input[0][0];
            result[1][1] = input[0][1];
            result[0][0] = input[1][0];
            result[0][1] = input[1][1];
        } else if (input.length == 3) {
            result[0][0] = input[2][0];
            result[0][1] = input[2][1];
            result[0][2] = input[2][2];
            result[1][0] = input[1][0];
            result[1][1] = input[1][1];
            result[1][2] = input[1][2];
            result[2][0] = input[0][0];
            result[2][1] = input[0][1];
            result[2][2] = input[0][2];
        }
        return result;
    }

    private Pair<Input, Output> parse(String line) {
        Input in = new Input();
        Output out = new Output();

        String[] bits = line.split("=>");
        String input = bits[0].trim();

        in.matchers = new HashSet<>();

        char[][] original = convertToArray(input.split("/"));
        in.size = original.length;
        in.matchers.add(new Matcher(original));

        char[][] temp = original;
        for (int i = 0; i != 4; i++) {
            temp = spin(temp);
            in.matchers.add(new Matcher(temp));
        }

        temp = original;
        for (int i = 0; i != 4; i++) {
            temp = horizontal(spin(temp));
            in.matchers.add(new Matcher(temp));
        }

        temp = original;
        for (int i = 0; i != 4; i++) {
            temp = vertical(spin(temp));
            in.matchers.add(new Matcher(temp));
        }

        String output = bits[1].trim();
        out.pattern = convertToArray(output.split("/"));

        return new Pair<>(in, out);
    }

    private char[][] convertToArray(String[] data) {
        char[][] result = new char[data.length][];
        for (int r = 0; r != result.length; r++) {
            result[r] = data[r].toCharArray();
        }
        return result;
    }
}
