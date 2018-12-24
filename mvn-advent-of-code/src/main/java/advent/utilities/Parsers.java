package advent.utilities;

import advent.common.IndexedPoint;
import advent.common.Point;

public interface Parsers {
    static Integer ToInteger(String input) {
        return Integer.parseInt(input);
    }

    static String ToString(String input) {
        return input.trim();
    }

    static String ToStringNoTrim(String input) {
        return input;
    }

    static Point ToPoint(String input) {
        String[] bits = input.split(",");
        return new Point(Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()));
    }

    static IndexedPoint ToIndexedPoint(int index, String input) {
        String[] bits = input.split(",");
        return new IndexedPoint(index, Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()));
    }
}
