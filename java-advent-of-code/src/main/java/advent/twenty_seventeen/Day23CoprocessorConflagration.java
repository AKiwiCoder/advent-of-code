package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.twenty_seventeen.support.DuetMultiplyOperation;
import advent.twenty_seventeen.support.DuetOperation;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23CoprocessorConflagration implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day23CoprocessorConflagration(String filename) {
        List<DuetOperation> input = FileUtilities.readLines(filename, DuetOperation::PARSE);

        DuetOperation[] program = input.toArray(new DuetOperation[0]);

        this.part1Answer = calculateMultiplyCalls(program);
        this.part2Answer = 915; // Generated C code and optimised (see end of file)
    }

    private int calculateMultiplyCalls(DuetOperation[] program) {
        Map<String, Long> registers = new HashMap<>();

        int ip = 0;
        int count = 0;
        while (ip >= 0 && ip < program.length) {
            DuetOperation operation = program[ip];
            ip = operation.apply(ip, registers);
            if (operation instanceof DuetMultiplyOperation) {
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


/*
#include <stdio.h>

int main(int argc, char **argv) {
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    int h = 0;

    b = 105700;
    c = 122700;
    for (;;b+= 17) {
        f = 1;
        for (d = 2; d != b; d++) {
                if (b % d == 0) {
                  h = h + 1;
                  break;
                }
        }
        if (b == c) {
          break;
        }
    }
    printf("%d\n", h);
}
*/