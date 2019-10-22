package advent.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ListUtilities {
    public static List<List<String>> generateCombinations(String current, Set<String> items, List<String> sequence) {
        List<List<String>> result = new LinkedList<>();

        if (sequence == null) {
            sequence = new LinkedList<>();
        }

        for (String item : items) {
            if (item != current && !sequence.contains(item)) {
                List<String> working = new LinkedList<>(sequence);
                working.add(item);
                List<List<String>> subPaths = generateCombinations(item, items, working);
                result.addAll(subPaths);
            }
        }

        if (result.size() == 0 && sequence != null && sequence.size() > 0) {
            result.add(sequence);
        }

        return result;
    }

}
