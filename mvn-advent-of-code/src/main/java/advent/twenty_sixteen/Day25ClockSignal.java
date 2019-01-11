package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.support.ILeonardoOperation;
import advent.twenty_sixteen.support.LeonardoOutOperation;
import advent.utilities.FileUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day25ClockSignal implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day25ClockSignal(String filename) {
        List<ILeonardoOperation> program = FileUtilities.readLines(filename, ILeonardoOperation::PARSE);

        Map<String, Integer> registers = new HashMap<>();

        String result = "";
        int i = 0;
        while (!result.equals("0101010101010101010101010101010101010101")) {
            result = getOutputFromRunWithRegisterA(program, registers, i++);
        }

        this.part1Answer = i - 1;
        this.part2Answer = 0;
    }

    private String getOutputFromRunWithRegisterA(List<ILeonardoOperation> program, Map<String, Integer> registers, int a) {
        registers.put("a", a);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        return executeProgram(new ArrayList<>(program), registers);
    }

    private String executeProgram(List<ILeonardoOperation> program, Map<String, Integer> registers) {
        int ip = 0;
        String result = "";
        while (ip < program.size()) {
            ILeonardoOperation operation = program.get(ip);
            if (operation instanceof LeonardoOutOperation) {
                result += ((LeonardoOutOperation)operation).getOutput(registers);
                if (result.length() == 40) {
                    return result;
                }
            }

//            if (ip == 3) {
//                registers.put("d", registers.get("d") + registers.get("b") * registers.get("c"));
//                ip = 8;
//            } else {
                ip = operation.execute(ip, registers);
//            }
        }
        return "";
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
