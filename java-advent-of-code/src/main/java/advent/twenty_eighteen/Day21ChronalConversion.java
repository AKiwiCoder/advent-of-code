package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.ElfComputer;
import advent.twenty_eighteen.support.Statement;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21ChronalConversion implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day21ChronalConversion(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        String ipLine = lines.get(0).trim();
        int ipRegister = Integer.parseInt(ipLine.substring(3).trim());

        List<Statement> statements = new ArrayList<>(lines.size());
        for (int i = 1; i != lines.size(); i++) {
            statements.add(Statement.ParseStatement(lines.get(i)));
        }

        ElfComputer computer = new ElfComputer(statements);

        int[] answers = new int[]{0, 0};
        int[] registers = new int[]{0, 0, 0, 0, 0, 0};

        int[] previous = new int[]{0};
        Set<Integer> loop = new HashSet<>();

        computer.execute(ipRegister, registers, (reg) -> {
            if (reg[ipRegister] == 28) {
                if (loop.isEmpty()) {
                    answers[0] = registers[2];
                }
                if (loop.contains(registers[2])) {
                    answers[1] = previous[0];
                    return true;
                }
                previous[0] = registers[2];
                loop.add(registers[2]);
                return false;
            }
            return false;
        });

        this.part1Answer = answers[0];
        this.part2Answer = answers[1];
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
