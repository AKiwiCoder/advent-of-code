package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.Point;
import advent.utilities.ArrayUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day14DiskDefragmentation implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day14DiskDefragmentation(String input) {
        List<String> hashes = new ArrayList<>();

        for (int i = 0; i != 128; i++) {
            String hashable = input + "-" + i;
            hashes.add(Day10KnotHash.hash(hashable));
        }

        List<String> binary = hashes.stream().map(this::toBinary).collect(Collectors.toList());

        char[][] output = ArrayUtilities.createCharArray(128, 128, (col, row) -> (binary.get(row).charAt(col) == '1') ? '#' : '.');

        this.part1Answer = ArrayUtilities.count('#', output);

        int[][] regions = ArrayUtilities.createIntArray(128, 128, (col, row) -> (output[row][col] == '.') ? 0 : -1);

        int region = 1;
        Point start = findNextRegionStart(regions);
        while (start != null) {
            fillRegion(output, regions, region++, start.getCol(), start.getRow());
            start = findNextRegionStart(regions);
        }

        this.part2Answer = region - 1;
    }

    private Point findNextRegionStart(int[][] regions) {
        for (int row = 0; row != regions.length; row++) {
            for (int col = 0; col != regions[row].length; col++) {
                if (regions[row][col] == -1) {
                    return new Point(col, row);
                }
            }
        }
        return null;
    }

    private void fillRegion(char[][] input, int[][] output, int region, int col, int row) {
        if (row < 0 || col < 0 || row >= 128 || col >= 128) {
            return;
        }

        if (input[row][col] == '.') {
            return;
        }

        if (output[row][col] != -1) {
            return;
        }

        output[row][col] = region;

        fillRegion(input, output, region, col - 1, row);
        fillRegion(input, output, region, col + 1, row);
        fillRegion(input, output, region, col, row - 1);
        fillRegion(input, output, region, col, row + 1);
    }

    private String toBinary(String hash) {
        String result = "";
        for (int i = 0; i != hash.length() / 2; i++) {
            String hexByte = hash.substring(i * 2, i * 2 + 2);
            int hexValue = Integer.parseInt(hexByte, 16);
            String binaryString = String.format("%8s", Integer.toBinaryString(hexValue));
            binaryString = binaryString.replaceAll(" ", "0");
            result += binaryString;
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
