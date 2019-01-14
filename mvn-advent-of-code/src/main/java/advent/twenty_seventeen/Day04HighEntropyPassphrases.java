package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.*;

public class Day04HighEntropyPassphrases implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day04HighEntropyPassphrases(String filename) {
        List<String> passphrases = FileUtilities.readLines(filename, String::trim);

        int count1 = 0;
        int count2 = 0;
        for (String passphrase : passphrases) {
            if (isValid1(passphrase)) {
                count1++;
            }
            if (isValid2(passphrase)) {
                count2++;
            }
        }

        this.part1Answer = count1;
        this.part2Answer = count2;
    }

    private boolean isValid1(String passphrase) {
        String[] bits = passphrase.split("\\s+");
        Map<String, Integer> counts = new HashMap<>();
        for (String bit : bits) {
            counts.put(bit, counts.getOrDefault(bit, 0) + 1);
        }
        return counts.values().stream().filter(cnt -> cnt > 1).count() == 0;
    }

    private boolean isValid2(String passphrase) {
        String[] bits = passphrase.split("\\s+");
        Set<String> seen = new HashSet<>();
        for (String bit : bits) {
            String sorted = bit.chars()
                    .sorted()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            if (seen.contains(sorted))
                return false;
            seen.add(sorted);
        }
        return true;
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
