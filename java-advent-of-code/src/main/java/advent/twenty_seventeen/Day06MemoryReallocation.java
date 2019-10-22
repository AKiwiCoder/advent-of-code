package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.*;

public class Day06MemoryReallocation implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day06MemoryReallocation(String filename) {
        String input = FileUtilities.readLines(filename, String::trim).get(0);

        String[] bits = input.split("\\s+");

        int[] memory = new int[bits.length];
        for (int m = 0; m != memory.length; m++) {
            memory[m] = Integer.parseInt(bits[m]);
        }

        Map<String, Integer> seen = new HashMap<>();
        String current = Arrays.toString(memory);
        int count = 0;
        while (!seen.containsKey(current)) {
            seen.put(current, count++);
            int max = Integer.MIN_VALUE;
            int maxIdx = -1;
            for (int m = 0; m != memory.length; m++) {
                if (memory[m] > max) {
                    max = memory[m];
                    maxIdx = m;
                }
            }

            int counter = memory[maxIdx];
            memory[maxIdx] = 0;
            for (int c = 0; c != counter; c++) {
                int i = (maxIdx + 1 + c) % memory.length;
                memory[i] += 1;
            }

            current = Arrays.toString(memory);
        }

        this.part1Answer = seen.size();
        this.part2Answer = count - seen.get(current);
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
