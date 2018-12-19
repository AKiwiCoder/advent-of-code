package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.HashMap;
import java.util.Map;

public class AlchemicalReduction implements DailyProblem<Integer, Integer> {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String input;

    public AlchemicalReduction(String filename) {
        this.input = FileUtilities.readLines(filename, Parsers::ToString).get(0);
    }

    private int performReduction(String replaced) {
        int i = 0;
        while (i < replaced.length() - 1) {
            if (Math.abs('A' - 'a') == Math.abs(replaced.charAt(i) - replaced.charAt(i + 1))) {
                replaced = replaced.substring(0, i) + replaced.substring(i + 2);
                if (i > 0)
                    i--;
            } else {
                i++;
            }
        }
        return replaced.length();
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
            long start = System.currentTimeMillis();
            reductions.put(key, performReduction(replaced));
            System.out.println(System.currentTimeMillis() - start);
        }
        return reductions.values().stream().min(Integer::compareTo).get();
    }
}
