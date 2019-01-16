package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.twenty_seventeen.support.DuetOperation;
import advent.twenty_seventeen.support.DuetRecoverOperation;
import advent.twenty_seventeen.support.DuetSoundOperation;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day18Duet implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day18Duet(String filename, boolean skipPartTwo) {
        List<DuetOperation> input = FileUtilities.readLines(filename, DuetOperation::PARSE);

        DuetOperation[] program = input.toArray(new DuetOperation[0]);

        this.part1Answer = getRecoveredFrequency(program);
        this.part2Answer = skipPartTwo ? -1 : runInParallel(program);
    }

    private int getRecoveredFrequency(DuetOperation[] program) {
        Map<String, Long> registers = new HashMap<>();
        int ip = 0;
        long recoveredFrequency = -1;
        while (ip >= 0 && ip < program.length) {
            DuetOperation operation = program[ip];
            ip = operation.apply(ip, registers);
            if (recoveredFrequency == -1 && operation instanceof DuetRecoverOperation) {
                if (registers.containsKey(DuetRecoverOperation.KEY)) {
                    recoveredFrequency = registers.get(DuetRecoverOperation.KEY);
                    break;
                }
            }
        }
        return (int) recoveredFrequency;
    }

    private int runInParallel(DuetOperation[] program) {
        LinkedList<Long> queueZeroToOne = new LinkedList<>();
        LinkedList<Long> queueOneToZero = new LinkedList<>();

        int ip0 = 0;
        int ip1 = 0;

        Map<String, Long> registers0 = new HashMap<>();
        Map<String, Long> registers1 = new HashMap<>();

        registers0.put("p", 0l);
        registers1.put("p", 1l);

        boolean canRun0 = true;
        boolean canRun1 = true;

        int sends[] = new int[2];
        while (canRun0 || canRun1) {
            ip0 = executeUntilAwait(0, program, ip0, registers0, queueZeroToOne, queueOneToZero, sends);
            ip1 = executeUntilAwait(1, program, ip1, registers1, queueOneToZero, queueZeroToOne, sends);

            canRun0 = !queueOneToZero.isEmpty();
            canRun1 = !queueZeroToOne.isEmpty();
        }

        return sends[1];
    }

    private int executeUntilAwait(int id, DuetOperation[] program, int ip, Map<String, Long> registers, LinkedList<Long> outgoing, LinkedList<Long> incoming, int[] sends) {
        while (ip >= 0 && ip < program.length) {
            DuetOperation operation = program[ip];
            if (operation instanceof DuetRecoverOperation) {
                // Highjack into a receive
                if (incoming.isEmpty()) {
                    return ip;
                }
//                System.out.println("Receive " + id + " value " + incoming.peek());
                registers.put(((DuetRecoverOperation) operation).getRegister(), incoming.pop());
                ip = ip + 1;
            } else if (operation instanceof DuetSoundOperation) {
                // Highjack into a send
                long value = ((DuetSoundOperation) operation).getValue(registers);
                sends[id]++;
                outgoing.addLast(value);
//                System.out.println("Send " + id + " value " + outgoing.peekLast());
                ip = ip + 1;
            } else {
                ip = operation.apply(ip, registers);
            }
        }
        return ip;
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
