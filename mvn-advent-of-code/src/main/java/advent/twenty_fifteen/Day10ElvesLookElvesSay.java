package advent.twenty_fifteen;

import advent.common.DailyProblem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day10ElvesLookElvesSay implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day10ElvesLookElvesSay(String input) {
        LinkedList<Integer> working = new LinkedList<>();
        for (Character c : input.toCharArray()) {
            working.add(Integer.parseInt(""+c));
        }

        List<Integer> part1 = null;
        for (int i = 0; i != 50; i++) {
            working = lookSay(working);

            if (i == 39) {
                part1 = new LinkedList<>(working);
            }
        }

        this.part1Answer = part1.size();
        this.part2Answer = working.size();
    }

    private LinkedList<Integer> lookSay(LinkedList<Integer> input) {
        Integer digit = input.pop();
        int count = 1;
        LinkedList<Integer> output = new LinkedList<>();

        int i = 1;
        while (input.size() > 0) {
            while (input.size() > 0 && digit == input.peekFirst()) {
                count++;
                input.pop();
            }

            output.add(count);
            output.add(digit);

            if (input.size() > 0) {
                digit = input.pop();
                count = 1;
            } else {
                count = 0;
            }
        }

        if (count > 0) {
            output.add(count);
            output.add(digit);
        }

        return output;
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
