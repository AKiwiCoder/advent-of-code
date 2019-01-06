package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day06SignalsAndNoise implements DailyProblem<String, String> {
    private static final Comparator<Map.Entry<Character, Integer>> SORTER = (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue());

    private final String part1Answer;
    private final String part2Answer;

    public Day06SignalsAndNoise(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        Map<Integer, Map<Character, Integer>> counts = new HashMap<>();
        for (String line : lines) {
            for (int i = 0; i != line.length(); i++) {
                Map<Character, Integer> charMap = counts.getOrDefault(i, new HashMap<>());
                char c = line.charAt(i);
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
                counts.put(i, charMap);
            }
        }

        String signal1 = "";
        String signal2 = "";
        for (int i = 0; i != lines.get(0).length(); i++) {
            Map<Character, Integer> charCountMap = counts.get(i);
            LinkedList<Map.Entry<Character, Integer>> entries = new LinkedList<>(charCountMap.entrySet());

            Collections.sort(entries, SORTER);

            signal1 += entries.getFirst().getKey();
            signal2 += entries.getLast().getKey();
        }

        this.part1Answer = signal1;
        this.part2Answer = signal2;
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
