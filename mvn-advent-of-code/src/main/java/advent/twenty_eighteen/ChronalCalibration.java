package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChronalCalibration implements DailyProblem<Integer, Integer> {
    private final List<Integer> input;

    public ChronalCalibration(String filename) {
        this.input = FileUtilities.readLines(filename, Parsers::ToInteger);
    }

    @Override
    public Integer getPart1Answer() {
        return input.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    @Override
    public Integer getPart2Answer() {
        Set<Integer> set = new HashSet<>();
        int frequency = 0;
        boolean found = false;
        set.add(0);
        while (!found) {
            for (int i = 0; i != input.size(); i++) {
                frequency += input.get(i);
                if (set.contains(frequency)) {
                    found = true;
                    break;
                }
                set.add(frequency);
            }
        }
        return frequency;
    }
}
