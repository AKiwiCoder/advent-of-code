package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Triple;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.List;

public class Day15TimingIsEverything implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day15TimingIsEverything(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        List<Triple<Integer, Integer, Integer>> discs = new ArrayList<>();

        for (String line : lines) {
            String[] bits = line.split("\\s+");
            int id = Integer.parseInt(bits[1].substring(1));
            int limits = Integer.parseInt(bits[3].trim());
            int start = Integer.parseInt(bits[11].substring(0, bits[11].length() - 1));
            discs.add(new Triple<>(id, limits, start));
        }

        this.part1Answer = runSimulation(discs) - 1;

        discs.add(new Triple<>(discs.size() + 1, 11, 0));
        this.part2Answer = runSimulation(discs) - 1;
    }

    private int runSimulation(List<Triple<Integer, Integer, Integer>> discs) {
        System.out.println(discs);
        int[] positions = new int[discs.size()];
        int[] limits = new int[discs.size()];

        for (Triple<Integer, Integer, Integer> disc : discs) {
            limits[disc.getFirst() - 1] = disc.getSecond();
            positions[disc.getFirst() - 1] = disc.getThird();
        }

        int step = 0;
        while (!checkInRightPlace(positions, limits, step)) step++;
        return step;
    }

    private void dump(int[] positions, int[] limits, int steps) {
        for (int i = 0; i != positions.length; i++) {
            System.out.print(((positions[i] + steps) % limits[i]) + " ");
        }
        System.out.println();
    }

    private boolean checkInRightPlace(int[] positions, int[] limits, int steps) {
        for (int i = 0; i != positions.length; i++) {
            if (((positions[i] + steps + i) % limits[i]) != 0)
                return false;
        }
        return true;
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
