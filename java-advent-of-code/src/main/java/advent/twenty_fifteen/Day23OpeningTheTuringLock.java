package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.twenty_fifteen.support.TuringInstruction;
import advent.utilities.FileUtilities;

import java.util.ArrayList;
import java.util.List;

public class Day23OpeningTheTuringLock implements DailyProblem<Pair<Integer, Integer>, Pair<Integer, Integer>> {
    private final Pair<Integer, Integer> part1Answer;
    private final Pair<Integer, Integer> part2Answer;

    public Day23OpeningTheTuringLock(String filename) {
        List<TuringInstruction> program = new ArrayList<>(FileUtilities.readLines(filename, TuringInstruction::PARSE));

        int[] registers1 = executeProgram(program, 0, 0);
        this.part1Answer = new Pair<>(registers1[0], registers1[1]);

        int[] registers2 = executeProgram(program, 1, 0);
        this.part2Answer = new Pair<>(registers2[0], registers2[1]);
    }

    private int[] executeProgram(List<TuringInstruction> program, int a, int b) {
        int[] registers = new int[]{a, b};
        int ip = 0;

        while (ip < program.size()) {
            TuringInstruction instruction = program.get(ip);
            ip = instruction.execute(ip, registers);
        }
        return registers;
    }

    @Override
    public Pair<Integer, Integer> getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Pair<Integer, Integer> getPart2Answer() {
        return part2Answer;
    }
}
