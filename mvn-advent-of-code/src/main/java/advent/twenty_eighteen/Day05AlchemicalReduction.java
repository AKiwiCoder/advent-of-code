package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.HashMap;
import java.util.Map;

public class Day05AlchemicalReduction implements DailyProblem<Integer, Integer> {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String input;

    public Day05AlchemicalReduction(String filename) {
        this.input = FileUtilities.readLines(filename, Parsers::ToString).get(0);
    }

    private int performReduction(String input) {
        char[] replaced = input.toCharArray();
        int limit = replaced.length;

        int i = 0;
        while (i < limit - 1) {
            if (Math.abs('A' - 'a') == Math.abs(replaced[i] - replaced[i + 1])) {
                System.arraycopy(replaced, i + 2, replaced, i, limit - i - 2);
                limit -= 2;
                if (i > 0)
                    i--;
            } else {
                i++;
            }
        }
        return limit;
    }

    @Override
    public Integer getPart1Answer() {
        return performReduction(input);
    }

    @Override
    public Integer getPart2Answer() {
        Map<String, Integer> reductions = new HashMap<>();
        for (int k = 0; k != 26; k++) {
            String key = "" + LOWER.charAt(k) + UPPER.charAt(k);
            String replaced = input.replaceAll("[" + key + "]", "");
            reductions.put(key, performReduction(replaced));
        }
        return reductions.values().stream().min(Integer::compareTo).get();
    }
}
