package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.ETurn;
import advent.utilities.FileUtilities;

import java.util.*;

public class Day25TheHaltingProblem implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private String getLastWord(String line) {
        return line.substring(line.lastIndexOf(' ')+1, line.length() - 1);
    }

    static class Action {
        int write;
        ETurn move;
        String next;
    }

    static class State {
        Action a0;
        Action a1;
    }

    public Day25TheHaltingProblem(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        String startState = getLastWord(lines.get(0));
        Integer stateCount = Integer.parseInt(lines.get(1).split("\\s+")[5]);

        Map<String, State> states = new HashMap<>();

        int lineNo = 2;
        while (lineNo < lines.size()) {
            lineNo++; // Skip Blank Line

            /*
             In state A:
             If the current value is 0:
              - Write the value 1.
              - Move one slot to the right.
              - Continue with state B.
             If the current value is 1:
              - Write the value 0.
              - Move one slot to the left.
              - Continue with state C.
             */
            String name = getLastWord(lines.get(lineNo++));

            lineNo++;
            Action a0 = new Action();
            a0.write = Integer.parseInt(getLastWord(lines.get(lineNo++)));
            a0.move = getLastWord(lines.get(lineNo++)).equals("right")?ETurn.Right:ETurn.Left;
            a0.next = getLastWord(lines.get(lineNo++));
            lineNo++;

            Action a1 = new Action();
            a1.write = Integer.parseInt(getLastWord(lines.get(lineNo++)));
            a1.move = getLastWord(lines.get(lineNo++)).equals("right")?ETurn.Right:ETurn.Left;
            a1.next = getLastWord(lines.get(lineNo++));

            State state = new State();
            state.a0 = a0;
            state.a1 = a1;

            states.put(name, state);
        }

        int pos = 0;
        Set<Integer> ones = new HashSet<>();

        State current = states.get(startState);
        for (int i = 0; i != stateCount; i++) {
            Integer key = Integer.valueOf(pos);

            Action a;
            if (ones.contains(key)) {
                a = current.a1;
            } else {
                a = current.a0;
            }

            if (a.write == 1) {
                ones.add(key);
            } else {
                ones.remove(key);
            }

            if (a.move == ETurn.Right) {
                pos--;
            } else {
                pos++;
            }

            current = states.get(a.next);
        }

        this.part1Answer = ones.size();
        this.part2Answer = 0;
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
