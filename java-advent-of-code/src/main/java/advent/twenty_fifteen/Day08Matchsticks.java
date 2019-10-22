package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;
import java.util.stream.Collectors;

public class Day08Matchsticks implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public static String decode(String line) {
        line = line.substring(1, line.length() - 1);
        String result = "";
        for (int i = 0; i != line.length(); i++) {
            if (line.charAt(i) == '\\') {
                i++;
                switch (line.charAt(i)) {
                    case '\\':
                        result += "\\";
                        break;
                    case '\"':
                        result += "\"";
                        break;
                    case 'x':
                        i++;
                        char hex0 = line.charAt(i);
                        i++;
                        char hex1 = line.charAt(i);
                        result += (char) Integer.parseInt("" + hex0 + hex1, 16);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown escape character (" + line.charAt(i) + ")");
                }
            } else {
                result += line.charAt(i);
            }
        }
        return result;
    }

    public static String encode(String line) {
        String result = "";

        for (char c : line.toCharArray()) {
            if (c == '\\') {
                result += "\\\\";
            } else if (c == '\"') {
                result += "\\\"";
            } else {
                result += c;
            }
        }
        return "\"" + result + "\"";
    }

    public Day08Matchsticks(String filename) {
        List<String> raw = FileUtilities.readLines(filename, Parsers::TO_STRING);
        List<String> decoded = raw.stream().map(s -> decode(s)).collect(Collectors.toList());
        List<String> encoded = raw.stream().map(s -> encode(s)).collect(Collectors.toList());

        int rawSpace = raw.stream().mapToInt(s -> s.length()).sum();
        int decodedSpace = decoded.stream().mapToInt(s -> s.length()).sum();
        int encodedSpace = encoded.stream().mapToInt(s -> s.length()).sum();

        this.part1Answer = rawSpace - decodedSpace;
        this.part2Answer = encodedSpace - rawSpace;
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
