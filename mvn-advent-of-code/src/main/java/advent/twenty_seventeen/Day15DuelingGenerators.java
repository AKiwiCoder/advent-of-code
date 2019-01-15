package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day15DuelingGenerators implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day15DuelingGenerators(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        long factorA = 16807;
        long factorB = 48271;

        long generatorA = Long.parseLong(lines.get(0).split("\\s+")[4]);
        long generatorB = Long.parseLong(lines.get(1).split("\\s+")[4]);

        this.part1Answer = executePart1(generatorA, generatorB, factorA, factorB);
        this.part2Answer = executePart2(generatorA, generatorB, factorA, factorB);
    }

    private int executePart1(long generatorA, long generatorB, long factorA, long factorB) {
        int count = 0;
        for (int i = 0; i != 40000000; i++) {
            generatorA = (generatorA * factorA) % 2147483647;
            generatorB = (generatorB * factorB) % 2147483647;

            if ((generatorA & 0xFFFF) == (generatorB & 0xFFFF)) {
                count++;
            }
        }
        return count;
    }

    private int executePart2(long generatorA, long generatorB, long factorA, long factorB) {
        int count = 0;
        for (int i = 0; i != 5000000; i++) {
            do {
                generatorA = (generatorA * factorA) % 2147483647;
            } while (generatorA % 4 != 0);

            do {
                generatorB = (generatorB * factorB) % 2147483647;
            } while (generatorB % 8 != 0);

            if ((generatorA & 0xFFFF) == (generatorB & 0xFFFF)) {
                count++;
            }
        }
        return count;
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
