package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.Point;
import advent.utilities.PointUtilities;

public class Day03SpiralMemory implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day03SpiralMemory(int input) {
        final int side = 1000;

        int[][] grid = new int[side][];
        for (int row = 0; row != side; row++) {
            grid[row] = new int[side];
        }

        int row0 = side / 2;
        int col0 = side / 2;

        Point origin = new Point(row0, col0);
        Point endPoint = walkSpiral1(input, grid, row0, col0);

        this.part1Answer = PointUtilities.calculateManhattenDistance(origin, endPoint);

        for (int row = 0; row != grid.length; row++) {
            for (int col = 0; col != grid[row].length; col++) {
                grid[row][col] = 0;
            }
        }

        this.part2Answer = walkSpiral2(input, grid, row0, col0);
    }

    private Point walkSpiral1(int input, int[][] grid, int startRow, int startCol) {
        int row = startRow;
        int col = startCol;

        Point result = null;

        int d = 0;
        for (int i = 1; i != input + 1; i++) {
            grid[row][col] = i;
            result = new Point(row, col);

            if (d == 0) {
                // East
                col++;
                if (grid[row - 1][col] == 0) {
                    d = 1;
                }
            } else if (d == 1) {
                // North
                row--;
                if (grid[row][col - 1] == 0) {
                    d = 2;
                }
            } else if (d == 2) {
                // West
                col--;
                if (grid[row + 1][col] == 0) {
                    d = 3;
                }
            } else if (d == 3) {
                // South
                row++;
                if (grid[row][col + 1] == 0) {
                    d = 0;
                }
            }
        }
        return result;
    }

    private static final int[] STEPS = {-1, 0, 1};

    private int walkSpiral2(int input, int[][] grid, int startRow, int startCol) {
        int row = startRow;
        int col = startCol;

        grid[row][col] = 1;

        int d = 0;
        while (true) {
            int value = 0;
            for (int dr : STEPS) {
                for (int dc : STEPS) {
                    value += grid[row + dr][col + dc];
                }
            }
            grid[row][col] = value;

            if (value > input) {
                return value;
            }

            if (d == 0) {
                // East
                col++;
                if (grid[row - 1][col] == 0) {
                    d = 1;
                }
            } else if (d == 1) {
                // North
                row--;
                if (grid[row][col - 1] == 0) {
                    d = 2;
                }
            } else if (d == 2) {
                // West
                col--;
                if (grid[row + 1][col] == 0) {
                    d = 3;
                }
            } else if (d == 3) {
                // South
                row++;
                if (grid[row][col + 1] == 0) {
                    d = 0;
                }
            }
        }
    }

    static String dumpToString(int[][] input) {
        String result = "";
        for (int y = 0; y != input.length; y++) {
            for (int x = 0; x != input[y].length; x++) {
                result += String.format("%3d ", input[y][x]);
            }
            result += "\n";
        }
        return result;
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
