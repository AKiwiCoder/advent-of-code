package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.support.ILeonardoOperation;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12LeonardosMonorail implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day12LeonardosMonorail(String filename) {
        List<ILeonardoOperation> program = FileUtilities.readLines(filename, ILeonardoOperation::PARSE);

        Map<String, Integer> registers = new HashMap<>();

        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        executeProgram(program, registers);
        this.part1Answer = registers.get("a");

        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);

        executeProgram(program, registers);
        this.part2Answer = registers.get("a");
    }

    private void executeProgram(List<ILeonardoOperation> program, Map<String, Integer> registers) {
        int ip = 0;
        while (ip < program.size()) {
            ILeonardoOperation operation = program.get(ip);
            try {
                ip = operation.execute(ip, registers);
            } catch (NullPointerException e) {
                System.out.println(operation);
                throw e;
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
}