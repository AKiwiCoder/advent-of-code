package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02InventoryManagementSystem implements DailyProblem<Integer, String> {
    private final List<String> input;

    public Day02InventoryManagementSystem(String filename) {
        this.input = FileUtilities.readLines(filename, Parsers::ToString);
    }

    @Override
    public Integer getPart1Answer() {
        int d = 0;
        int t = 0;
        for (String id : input) {
            Map<Character, Integer> letters = new HashMap<>();
            for (Character c : id.toCharArray()) {
                if (letters.containsKey(c)) {
                    letters.put(c, letters.get(c) + 1);
                } else {
                    letters.put(c, 1);
                }
            }

            boolean doub = false;
            boolean trip = false;

            for (Map.Entry<Character, Integer> entries : letters.entrySet()) {
                if (entries.getValue() == 2) {
                    doub = true;
                }
                if (entries.getValue() == 3) {
                    trip = true;
                }
            }
            if (doub) d++;
            if (trip) t++;
        }
        return d * t;
    }

    private static String diff(String one, String two) {
        int diffCount = 0;
        String t = "";
        for (int i = 0; i != one.length(); i++) {
            if (one.charAt(i) == two.charAt(i)) {
                t += one.charAt(i);
            } else {
                diffCount++;
            }
        }
        if (diffCount == 1) {
            return t;
        }
        return null;
    }

    @Override
    public String getPart2Answer() {
        for (String id1 : input) {
            for (String id2 : input) {
                String found = diff(id1, id2);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
