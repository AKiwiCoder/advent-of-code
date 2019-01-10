package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.support.ILeonardoOperation;
import advent.twenty_sixteen.support.LeonardoToggleOperation;
import advent.utilities.FileUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23SafeCracking implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day23SafeCracking(String filename) {
        List<ILeonardoOperation> program = FileUtilities.readLines(filename, ILeonardoOperation::PARSE);

        Map<String, Integer> registers = new HashMap<>();

        registers.put("a", 7);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        executeProgram(new ArrayList<>(program), registers);

        this.part1Answer = registers.get("a");

        registers.put("a", 12);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        executeProgram(new ArrayList<>(program), registers);

        this.part2Answer = registers.get("a");
    }

    private void executeProgram(List<ILeonardoOperation> program, Map<String, Integer> registers) {
        int ip = 0;
        while (ip < program.size()) {
            ILeonardoOperation operation = program.get(ip);
            if (operation instanceof LeonardoToggleOperation) {
                ip = ((LeonardoToggleOperation) operation).modify(ip, registers, program);
            } else {
                if ((ip == 5)) {
                    registers.put("a", registers.get("a") + registers.get("c") * registers.get("d"));
                    ip = ip + 5;
                } else if (ip == 21) {
                    registers.put("a", registers.get("a") + registers.get("c") * registers.get("d"));
                    ip = ip + 5;
                } else{
                    ip = operation.execute(ip, registers);
                }
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
