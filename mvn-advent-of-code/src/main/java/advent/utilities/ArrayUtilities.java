package advent.utilities;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface ArrayUtilities {
    static void print(Object[][] input) {
        for (int c = 0; c != input.length; c++) {
            for (int r = 0; r != input[c].length; r++) {
                System.out.print(input[c][r]);
            }
            System.out.println();
        }
    }

    static void print(char[][] input) {
        System.out.println(dumpToString(input));
    }

    static void print(int[][] input) {
        System.out.println(dumpToString(input));
    }

    static int[][] createIntArray(int width, int height, BiFunction<Integer, Integer, Integer> populator) {
        int[][] result = new int[height][];
        for (int y = 0; y != result.length; y++) {
            result[y] = new int[width];
            for (int x = 0; x != result[0].length; x++) {
                result[y][x] = populator.apply(x, y);
            }
        }
        return result;
    }

    static char[][] createCharArray(int width, int height, BiFunction<Integer, Integer, Character> populator) {
        char[][] result = new char[height][];
        for (int y = 0; y != result.length; y++) {
            result[y] = new char[width];
            for (int x = 0; x != result[0].length; x++) {
                result[y][x] = populator.apply(x, y).charValue();
            }
        }
        return result;
    }

    static int count(char target, char[][] input) {
        return count(target, input, input.length);
    }

    static int count(char target, char[][] input, int rowsToCount) {
        int result = 0;
        for (int r = 0; r != rowsToCount; r++) {
            for (int c = 0; c != input[r].length; c++) {
                if (input[r][c] == target) {
                    result++;
                }
            }
        }
        return result;
    }

    static int count(int[][] input, Function<Integer, Boolean> filter) {
        int result = 0;
        for (int r = 0; r != input.length; r++) {
            for (int c = 0; c != input[r].length; c++) {
                if (filter.apply(input[r][c])) {
                    result++;
                }
            }
        }
        return result;
    }

    static String dumpToString(char[][] input) {
        String result = "";
        for (int y = 0; y != input.length; y++) {
            for (int x = 0; x != input[y].length; x++) {
                result += input[y][x];
            }
            result += "\n";
        }
        return result;
    }

    static String dumpToString(int[][] input) {
        String result = "";
        for (int y = 0; y != input.length; y++) {
            for (int x = 0; x != input[y].length; x++) {
                result += input[y][x] + " ";
            }
            result += "\n";
        }
        return result;
    }

    static boolean isValidCoordinates(char[][] map, int row, int col) {
        return row >= 0 && col >= 0 && row < map.length && col < map[0].length;
    }
}
