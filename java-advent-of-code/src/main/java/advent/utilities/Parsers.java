package advent.utilities;

import advent.common.IndexedPoint;
import advent.common.Point;

import java.util.ArrayList;
import java.util.List;

public interface Parsers {
    static Integer TO_INTEGER(String input) {
        return Integer.parseInt(input);
    }

    static String TO_STRING(String input) {
        return input.trim();
    }

    static String TO_STRING_NO_TRIM(String input) {
        return input;
    }

    static Point TO_POINT(String input) {
        String[] bits = input.split(",");
        return new Point(Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()));
    }

    static IndexedPoint TO_INDEXEX_POINT(int index, String input) {
        String[] bits = input.split(",");
        return new IndexedPoint(index, Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()));
    }

    static List<Integer> TO_INTEGER_LIST(String line) {
        List<Integer> results = new ArrayList<>();
        String[] bits = line.split("\\s+");
        for (String bit : bits) {
            results.add(Integer.valueOf(bit));
        }
        return results;
    }
}
