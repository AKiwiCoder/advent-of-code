package advent.utilities;

import advent.common.IndexedPoint;
import advent.common.Point;

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
}
