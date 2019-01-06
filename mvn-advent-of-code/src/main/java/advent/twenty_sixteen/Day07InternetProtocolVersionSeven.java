package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Day07InternetProtocolVersionSeven implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private static boolean check(String text) {
        for (int i = 0; i != text.length() - 3; i++) {
            if (text.charAt(i) == text.charAt(i + 3) && text.charAt(i + 1) == text.charAt(i + 2) && text.charAt(i) != text.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean check(List<String> text) {
        for (String t : text) {
            if (check(t)) {
                return true;
            }
        }
        return false;
    }

    private static final Function<String, Pair<List<String>, List<String>>> PARSE = new Function<String, Pair<List<String>, List<String>>>() {
        @Override
        public Pair<List<String>, List<String>> apply(String line) {
            List<String> outside = new ArrayList<>();
            List<String> inside = new ArrayList<>();
            boolean ip = true;
            int current = 0;
            while (current < line.length()) {
                if (ip) {
                    int index = line.indexOf("[", current);
                    if (index < 0) {
                        index = line.length();
                    }
                    outside.add(line.substring(current, index));
                    current = index + 1;
                } else {
                    int index = line.indexOf("]", current);
                    inside.add(line.substring(current, index));
                    current = index + 1;
                }
                ip = !ip;
            }
            return new Pair<>(outside, inside);
        }
    };

    public Day07InternetProtocolVersionSeven(String filename) {
        List<Pair<List<String>, List<String>>> addresses = FileUtilities.readLines(filename, PARSE);

        int part1Count = 0;
        int part2Count = 0;
        for (Pair<List<String>, List<String>> e : addresses) {
            if (check(e.getFirst()) && !check(e.getSecond())) {
                part1Count++;
            }

            if (check(split(e.getFirst()), split(e.getSecond()))) {
                part2Count++;
            }
        }

        this.part1Answer = part1Count;
        this.part2Answer = part2Count;
    }

    private boolean check(Set<String> firstParings, Set<String> secondParings) {
        for (String first : firstParings) {
            String search = String.format("%c%c%c", first.charAt(1), first.charAt(0), first.charAt(1));
            if (secondParings.contains(search)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> split(List<String> chunks) {
        Set<String> result = new HashSet<>();
        for (String chunk : chunks) {
            for (int i = 0; i != chunk.length() - 2; i++) {
                if (chunk.charAt(i) == chunk.charAt(i + 2) && chunk.charAt(i) != chunk.charAt(i + 1)) {
                    result.add(chunk.substring(i, i + 3));
                }
            }
        }
        return result;
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
