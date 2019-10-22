package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day13PacketScanners implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day13PacketScanners(String filename) {
        List<Pair<Integer, Integer>> lines = FileUtilities.readLines(filename, this::parse);

        int maxScanner = lines.stream().mapToInt(p -> p.getFirst()).max().getAsInt();
        int[] scanners = new int[maxScanner + 1];
        int[] direction = new int[maxScanner + 1];
        int[] depths = new int[maxScanner + 1];

        for (int i = 0; i != scanners.length; i++) {
            scanners[i] = -1;
            direction[i] = 0;
            depths[i] = -1;
        }

        for (Pair<Integer, Integer> line : lines) {
            scanners[line.getFirst()] = 0;
            direction[line.getFirst()] = 1;
            depths[line.getFirst()] = line.getSecond() - 1;
        }

        this.part1Answer = performRunWithScoring(scanners.clone(), direction.clone(), depths.clone());

        int delay = 0;
        while (doWeGetCaught(delay, scanners.clone(), direction.clone(), depths.clone())) {
            delay++;
        }
        this.part2Answer = delay;
    }

    private int performRunWithScoring(int[] scanners, int[] direction, int[] depths) {
        int result = 0;
        for (int s = 0; s < scanners.length; s++) {
            if (scanners[s] == 0) {
                result += s * (depths[s] + 1);
            }

            moveScanners(scanners, direction, depths);
        }
        return result;
    }

    private boolean doWeGetCaught(int delay, int[] scanners, int[] direction, int[] depths) {
        int time = delay;
        int pos = 0;
        while (pos < scanners.length) {
            if (scanners[pos] != -1 && (time % (depths[pos] * 2) == 0)) {
                return true;
            }
            time++;
            pos++;
        }
        return false;
    }

    private void moveScanners(int[] scanners, int[] direction, int[] depths) {
        for (int m = 0; m < scanners.length; m++) {
            if (scanners[m] != -1) {
                if (scanners[m] == depths[m]) {
                    direction[m] = -1;
                } else if (scanners[m] == 0) {
                    direction[m] = 1;
                }
                scanners[m] = scanners[m] + direction[m];
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

    private Pair<Integer, Integer> parse(String line) {
        String[] bits = line.split(":");
        return new Pair<>(Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()));
    }
}
