package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.EMnemonic;
import advent.twenty_eighteen.support.ElfComputer;
import advent.twenty_eighteen.support.Statement;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.List;

public class Day19GoWithTheFlow implements DailyProblem<Integer, Integer> {
    private int part1Answer;
    private int part2Answer;

    public Day19GoWithTheFlow(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        String ipLine = lines.get(0).trim();
        int ipRegister = Integer.parseInt(ipLine.substring(3).trim());

        List<Statement> statements = new ArrayList<>(lines.size());
        for (int i = 1; i != lines.size(); i++) {
            String[] bits = lines.get(i).split(" ");
            EMnemonic mnemonic = EMnemonic.valueOf(bits[0].trim());
            int a = Integer.parseInt(bits[1].trim());
            int b = Integer.parseInt(bits[2].trim());
            int c = Integer.parseInt(bits[3].trim());
            statements.add(new Statement(mnemonic, a, b, c));
        }

        ElfComputer computer = new ElfComputer(statements);

        int[] registers = new int[]{0,0,0,0,0,0};
        computer.execute(ipRegister, registers);
        this.part1Answer = registers[0];

        registers = new int[]{1,0,0,0,0,0};
        computer.execute(ipRegister, registers, (reg) -> reg[ipRegister] >= 34);
        this.part2Answer = sumOfDivisors(registers[5]);
    }

    public static int sumOfDivisors(int number) {
        int result = 1;
        for (int kth = 2; kth * kth <= number; kth++) {
            int power = 1;
            while (number % kth == 0) {
                power = power * kth + 1;
                number = number / kth;
            }
            result = result * power;
        }
        if (number > 1)
            result = result * (1 + number);
        return result;
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
