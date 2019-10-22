package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

public class Day08TwoFactorAuthentication implements DailyProblem<Integer, String> {
    private final int part1Answer;
    private final String part2Answer;

    public Day08TwoFactorAuthentication(String filename, int height, int width) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        char[][] screen = new char[height][];
        for (int row = 0; row != screen.length; row++) {
            screen[row] = new char[width];
            for (int col = 0; col != screen[row].length; col++) {
                screen[row][col] = '.';
            }
        }

        for (String line : lines) {
            if (line.startsWith("rect ")) {
                int indexX = line.indexOf("x");
                int indexS = line.indexOf(" ");
                int w = Integer.parseInt(line.substring(indexS, indexX).trim());
                int h = Integer.parseInt(line.substring(indexX + 1).trim());
                for (int r = 0; r != h; r++) {
                    for (int c = 0; c != w; c++) {
                        screen[r][c] = '#';
                    }
                }
            }
            if (line.startsWith("rotate row y=")) {
                line = line.substring(13);
                int indexOf = line.indexOf("by");
                int row = Integer.parseInt(line.substring(0, indexOf).trim());
                int count = Integer.parseInt(line.substring(indexOf + 2).trim());
                for (int i = 0; i != count; i++) {
                    rotateRow(row, screen);
                }
            }
            if (line.startsWith("rotate column x=")) {
                line = line.substring(16);
                int indexOf = line.indexOf("by");
                int col = Integer.parseInt(line.substring(0, indexOf).trim());
                int count = Integer.parseInt(line.substring(indexOf + 2).trim());
                for (int i = 0; i != count; i++) {
                    rotateCol(col, screen);
                }
            }
        }

        this.part1Answer = ArrayUtilities.count('#', screen);
        this.part2Answer = ArrayUtilities.dumpToString(screen);
    }

    private void rotateCol(int col, char[][] screen) {
        int height = screen.length;

        char c = screen[height - 1][col];
        for (int i = height - 1; i != 0; i--) {
            screen[i][col] = screen[i - 1][col];
        }
        screen[0][col] = c;
    }

    private void rotateRow(int row, char[][] screen) {
        int width = screen[row].length;

        char c = screen[row][width - 1];
        for (int i = width - 1; i != 0; i--) {
            screen[row][i] = screen[row][i - 1];
        }
        screen[row][0] = c;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
