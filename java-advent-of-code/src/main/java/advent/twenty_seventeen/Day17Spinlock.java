package advent.twenty_seventeen;

import advent.common.DailyProblem;

import java.util.LinkedList;
import java.util.List;

public class Day17Spinlock implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day17Spinlock(int steps) {

        List<Integer> buffer = new LinkedList<>();
        buffer.add(Integer.valueOf(0));

        int current = 0;
        for (int cycles = 1; cycles != 2018; cycles++) {
            current = 1 + (current + steps) % buffer.size();
            buffer.add(current, Integer.valueOf(cycles));
        }

        this.part1Answer = buffer.get(current + 1);

        int afterZero = -1;
        current = 0;
        for (int cycles = 1; cycles != 50000000; cycles++) {
            current = 1 + (current + steps) % cycles;
            if (current == 1) {
                afterZero = cycles;
            }
        }
        this.part2Answer = afterZero;
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
