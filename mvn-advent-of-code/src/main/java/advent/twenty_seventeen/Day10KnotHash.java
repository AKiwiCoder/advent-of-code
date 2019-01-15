package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.StringUtilities;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day10KnotHash implements DailyProblem<Integer, String> {
    private final int part1Answer;
    private final String part2Answer;

    public Day10KnotHash(String filename, int count) {
        String line = FileUtilities.readLines(filename, String::trim).get(0);

        List<Integer> lengths = new LinkedList<>();
        for (String bit : line.split(",")) {
            lengths.add(Integer.parseInt(bit));
        }

        List<Integer> working = new LinkedList<>();
        for (int i = 0; i != count; i++) {
            working.add(Integer.valueOf(i));
        }

        int current = 0;
        int skip = 0;
        for (Integer length : lengths) {
            current = knot(current, length, skip++, working);
        }

        this.part1Answer = working.get(0) * working.get(1);
        this.part2Answer = hash(line);
    }

    private static int knot(int current, int length, int skip, List<Integer> working) {
        int reverseStart = current;
        int reverseEnd = (current + length - 1) % working.size();

        for (int i = 0; i != length / 2; i++) {
            Integer s = working.get(reverseStart);
            Integer e = working.get(reverseEnd);

            working.set(reverseStart, e);
            working.set(reverseEnd, s);

            reverseStart = (reverseStart + 1) % working.size();
            reverseEnd = (reverseEnd == 0) ? working.size() - 1 : reverseEnd - 1;
        }
        return (current + length + skip) % working.size();
    }

    public static String hash(String input) {
        List<Integer> lengths = StringUtilities.toCharacterList(input).stream().map(c -> Integer.valueOf(c)).collect(Collectors.toList());
        lengths.add(17);
        lengths.add(31);
        lengths.add(73);
        lengths.add(47);
        lengths.add(23);

        List<Integer> working = new LinkedList<>();
        for (int i = 0; i != 256; i++) {
            working.add(Integer.valueOf(i));
        }

        int current = 0;
        int skip = 0;

        for (int i =0; i != 64; i++) {
            for (Integer length : lengths) {
                current = knot(current, length, skip++, working);
            }
        }

        String result="";
        int index = 0;
        for (int block = 0; block != 16; block++) {
            int start = working.get(index).intValue();
            index++;
            for (int sub = 1; sub != 16; sub++) {
                start = start ^ working.get(index).intValue();
                index++;
            }
            result += String.format("%02x", start);
        }
        return result;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
