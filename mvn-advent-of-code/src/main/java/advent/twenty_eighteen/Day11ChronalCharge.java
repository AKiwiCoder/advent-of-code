package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

public class Day11ChronalCharge implements DailyProblem<String, String> {
    private static final int SIZE = 300;

    private final int serialNumber;
    private final int[][][] totalPowerSquares;

    public static int powerLevel(int serialNumber, int x, int y) {
        /*
         * Find the fuel cell's rack ID, which is its X coordinate plus 10.
         * Begin with a power level of the rack ID times the Y coordinate.
         * Increase the power level by the value of the grid serial number (your puzzle input).
         * Set the power level to itself multiplied by the rack ID.
         * Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
         * Subtract 5 from the power level.
         */

        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel = powerLevel + serialNumber;
        powerLevel = powerLevel * rackId;
        powerLevel = powerLevel % 1000 / 100;
        powerLevel = powerLevel - 5;
        return powerLevel;
    }


    public Day11ChronalCharge(String filename) {
        this.serialNumber = FileUtilities.readLines(filename, Parsers::ToInteger).get(0);

        int[][] powerLevels = ArrayUtilities.createIntArray(SIZE, SIZE, (x, y) -> powerLevel(serialNumber, x + 1, y + 1));

        this.totalPowerSquares = new int[SIZE][][];
        for (int size = 0; size != SIZE; size++) {
            int squareSide = size;
            totalPowerSquares[size] = ArrayUtilities.createIntArray(SIZE - size, SIZE - size, (x, y) -> {
                if (squareSide == 0) {
                    return powerLevels[y][x];
                }
                int total = totalPowerSquares[squareSide - 1][y][x];
                for (int sy = 0; sy != squareSide; sy++) {
                    total += totalPowerSquares[0][y + sy][x + squareSide];
                }
                for (int sx = 0; sx != squareSide; sx++) {
                    total += totalPowerSquares[0][y + squareSide][x + sx];
                }
                total += totalPowerSquares[0][y + squareSide][x + squareSide];
                return total;
            });
        }
    }

    @Override
    public String getPart1Answer() {
        int max = Integer.MIN_VALUE;
        int maxX = -1;
        int maxY = -1;
        for (int y = 0; y != totalPowerSquares[2].length; y++) {
            for (int x = 0; x != totalPowerSquares[2][0].length; x++) {
                int power = totalPowerSquares[2][y][x];
                if (power > max) {
                    max = power;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        return String.format("%d,%d", (maxX + 1), (maxY + 1));
    }

    @Override
    public String getPart2Answer() {
        int max = Integer.MIN_VALUE;
        int maxX = -1;
        int maxY = -1;
        int maxS = -1;
        for (int s = 0; s != totalPowerSquares.length; s++) {
            for (int y = 0; y != totalPowerSquares[s].length; y++) {
                for (int x = 0; x != totalPowerSquares[s][0].length; x++) {
                    int power = totalPowerSquares[s][y][x];

                    if (power > max) {
                        max = power;
                        maxX = x;
                        maxY = y;
                        maxS = s;
                    }
                }
            }
        }
        return String.format("%d,%d,%d", (maxX + 1), (maxY + 1), (maxS + 1));
    }
}
