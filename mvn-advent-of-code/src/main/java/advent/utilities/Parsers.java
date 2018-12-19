package advent.utilities;

public interface Parsers {
    static Integer ToInteger(String input) {
        return Integer.parseInt(input);
    }

    static String ToString(String input) {
        return input.trim();
    }
}
