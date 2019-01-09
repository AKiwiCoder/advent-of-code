package advent.twenty_sixteen;

import advent.common.DailyProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day16DragonChecksum implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day16DragonChecksum(int length1, int length2, String initialData) {
        this.part1Answer = calculateChecksum(length1, initialData);
        this.part2Answer = calculateChecksum(length2, initialData);
    }

    private String calculateChecksum(int length, String initialData) {
        List<Character> current = new ArrayList<>();
        for (char c : initialData.toCharArray()) {
            current.add(c);
        }

        while (inflateDataUsingDragonCurve(current).size() < length);

        current = current.stream().limit(length).collect(Collectors.toList());

        while (current.size() % 2 == 0) {
            List<Character> output = new ArrayList<>();
            for (int i = 0; i < current.size() - 1; i += 2) {
                if (current.get(i) == current.get(i + 1)) {
                    // Same
                    output.add('1');
                } else {
                    // Different
                    output.add('0');
                }
            }
            current = output;
        }
        return current.stream().map(c -> "" + c).collect(Collectors.joining());
    }

    // Call the data you have at this point "a".
    // Make a copy of "a"; call this copy "b".
    // Reverse the order of the characters in "b".
    // In "b", replace all instances of 0 with 1 and all 1s with 0.
    // The resulting data is "a", then a single 0, then "b".
    public List<Character> inflateDataUsingDragonCurve(List<Character> data) {
        List<Character> b = new ArrayList<>(data);
        Collections.reverse(b);
        b = b.stream().map(c -> {
            if (c == '0') return '1';
            else return '0';
        }).collect(Collectors.toList());
        data.add('0');
        data.addAll(b);
        return data;
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
