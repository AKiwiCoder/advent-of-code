package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.EMnemonic;
import advent.twenty_eighteen.support.ElfComputer;
import advent.twenty_eighteen.support.Statement;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day16ChronalClassification implements DailyProblem<Integer, Integer> {
    private final int part2Answer;
    private final int part1Answer;

    public Day16ChronalClassification(String name) {
        LinkedList<String> input = new LinkedList<>(FileUtilities.readLines(name, Parsers::TO_STRING));

        Map<Integer, Set<EMnemonic>> opcodeToMnemonicMap = new HashMap<>();
        List<Statement> statements = new LinkedList<>();

        boolean loadedMappings = true;

        int opcodeMultipleMatches = 0;
        while (!input.isEmpty()) {
            if (loadedMappings) {
                String before = input.pop();
                if (before.trim().isEmpty()) {
                    input.pop();
                    loadedMappings = false;

                    cleanMappings(opcodeToMnemonicMap);
                    continue;
                }

                int[] registersBefore = parse(before.substring(8), ",");
                int[] example = parse(input.pop(), " ");
                int[] registersAfter = parse(input.pop().substring(8), ",");

                int matches = 0;
                for (EMnemonic m : EMnemonic.values()) {
                    if (m.isPossible(example[1], example[2], example[3], registersBefore, registersAfter)) {
                        Set<EMnemonic> entry = opcodeToMnemonicMap.getOrDefault(example[0], new HashSet<>());
                        entry.add(m);
                        opcodeToMnemonicMap.put(example[0], entry);
                        matches++;
                    }
                }

                if (matches >= 3) {
                    opcodeMultipleMatches++;
                }

                input.pop(); // Trailing blank line
            } else {
                String statementDigits = input.pop();
                String[] bits = statementDigits.split(" ");
                Integer opcode = Integer.parseInt(bits[0].trim());
                if (!opcodeToMnemonicMap.containsKey(opcode)) {
                    System.out.println("Opcode: " + opcode);
                    System.out.println(opcodeToMnemonicMap);
                }
                EMnemonic mnemonic = opcodeToMnemonicMap.get(opcode).iterator().next();
                int a = Integer.parseInt(bits[1].trim());
                int b = Integer.parseInt(bits[2].trim());
                int c = Integer.parseInt(bits[3].trim());
                statements.add(new Statement(mnemonic, a, b, c));
            }
        }

        this.part1Answer = opcodeMultipleMatches;

        int[] registers = new int[]{0, 0, 0, 0, 0};
        ElfComputer computer = new ElfComputer(statements);
        computer.execute(4, registers);

        this.part2Answer = registers[0];
    }

    private static int[] parse(String string, String sep) {
        String t = string;
        if (string.startsWith("[")) {
            t = string.substring(string.indexOf("[") + 1, string.indexOf("]"));
        }

        String[] bits = t.split(sep);

        int[] result = new int[4];
        result[0] = Integer.parseInt(bits[0].trim());
        result[1] = Integer.parseInt(bits[1].trim());
        result[2] = Integer.parseInt(bits[2].trim());
        result[3] = Integer.parseInt(bits[3].trim());
        return result;
    }

    private static void cleanMappings(Map<Integer, Set<EMnemonic>> opcodeToMnemonicMap) {
        Set<EMnemonic> singles = new HashSet<>();

        while (singles.size() != opcodeToMnemonicMap.size()) {
            for (Integer key : opcodeToMnemonicMap.keySet()) {
                Set<EMnemonic> va = opcodeToMnemonicMap.get(key);

                if (va.size() > 1) {
                    va.removeAll(singles);
                    opcodeToMnemonicMap.put(key, va);
                }

                if (va.size() == 1) {
                    singles.add(va.iterator().next());
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
