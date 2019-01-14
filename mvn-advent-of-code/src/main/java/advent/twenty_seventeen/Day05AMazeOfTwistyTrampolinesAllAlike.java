package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day05AMazeOfTwistyTrampolinesAllAlike implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day05AMazeOfTwistyTrampolinesAllAlike(String filename) {
        List<Integer> program = FileUtilities.readLines(filename, Integer::parseInt);

        this.part1Answer = doRun1(program);
        this.part2Answer = doRun2(program);
    }

    private int doRun1(List<Integer> program) {
        int[] instructions = new int[program.size()];
        for (int i = 0; i != instructions.length; i++) {
            instructions[i] = program.get(i);
        }

        int steps = 0;
        int ip = 0;
        while (ip < instructions.length) {
            int current = instructions[ip];
            instructions[ip] += 1;
            ip = ip + current;
            steps++;
        }
        return steps;
    }

    private int doRun2(List<Integer> program) {
        int[] instructions = new int[program.size()];
        for (int i = 0; i != instructions.length; i++) {
            instructions[i] = program.get(i);
        }

        int steps = 0;
        int ip = 0;
        while (ip < instructions.length) {
            int current = instructions[ip];
            if (current >= 3) {
                instructions[ip] -= 1;
            } else {
                instructions[ip] += 1;
            }
            ip = ip + current;
            steps++;
        }
        return steps;
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
