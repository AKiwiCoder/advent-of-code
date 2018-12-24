package advent.utilities;

import java.util.function.BiFunction;

public interface ArrayUtilities {
    static void print(Object[][] input) {
        for (int c = 0; c != input.length; c++) {
            for (int r = 0; r != input[c].length; r++) {
                System.out.print(input[c][r]);
            }
            System.out.println();
        }
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
}
