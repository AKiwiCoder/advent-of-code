package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.twenty_seventeen.support.IncrementOperation;
import advent.twenty_seventeen.support.Instruction;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Day08IHeardYouLikeRegisters implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day08IHeardYouLikeRegisters(String filename) {
        List<Instruction> parsed = FileUtilities.readLines(filename, Instruction::PARSE);

        Instruction[] instructions = parsed.toArray(new Instruction[0]);

        Map<String, Integer> registers = new HashMap<>();
        Map<String, Integer> maximums = new HashMap<>();
        for (int i = 0; i != instructions.length; i++) {
            instructions[i].apply(registers);
            for (Map.Entry<String, Integer> register : registers.entrySet()) {
                if (register.getValue() > maximums.getOrDefault(register.getKey(), 0)) {
                    maximums.put(register.getKey(), register.getValue());
                }
            }
        }

        this.part1Answer = registers.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        this.part2Answer = maximums.values().stream().mapToInt(Integer::intValue).max().getAsInt();;
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
