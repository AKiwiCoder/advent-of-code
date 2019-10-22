package advent.twenty_sixteen;

import advent.twenty_sixteen.support.CircularLinkedList;
import advent.common.DailyProblem;

public class Day19AnElephantNamedJoseph implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day19AnElephantNamedJoseph(int numberOfElves) {
        this.part1Answer = calculatePart1(numberOfElves);
        this.part2Answer = calculatePart2(numberOfElves);
    }

    private CircularLinkedList<Integer> buildElfCircle(int numberOfElves) {
        CircularLinkedList<Integer> elves = new CircularLinkedList<>();
        long[] presents = new long[numberOfElves];
        for (int i = 0; i != numberOfElves; i++) {
            presents[i] = 1;
            elves.insertBeforeCurrent(i + 1);
        }
        return elves;
    }

    private Integer calculatePart1(int numberOfElves) {
        CircularLinkedList<Integer> elves = buildElfCircle(numberOfElves);

        while (elves.size() > 1) {
            elves.step(1);
            elves.delete();
        }
        return elves.head();
    }

    private Integer calculatePart2(int numberOfElves) {
        CircularLinkedList<Integer> elves = buildElfCircle(numberOfElves);

        while (elves.size() > 1) {
            elves.deleteMiddle();
            elves.step(1);
        }
        return elves.head();
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
