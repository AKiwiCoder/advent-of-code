package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

import static advent.utilities.StringUtilities.countOccurrences;

public class Day05DoesntHeHaveInternElvesForThis implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;

    public Day05DoesntHeHaveInternElvesForThis(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        int nicePart1 = 0;
        int nicePart2 = 0;

        for (String line : lines) {
            if (niceMethodOne(line)) {
                nicePart1++;
            }
            if (niceMethodTwo(line)) {
                nicePart2++;
            }
        }

        this.part1Answer = nicePart1;
        this.part2Answer = nicePart2;
    }

    private boolean niceMethodOne(String line) {
        int vowelCount = countOccurrences(line, 'a') + countOccurrences(line, 'e') + countOccurrences(line, 'i') + countOccurrences(line, 'o') + countOccurrences(line, 'u');
        boolean hasPair = hasPairedCharacters(line);
        boolean hasBadString = line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy");
        return (vowelCount >= 3) && hasPair && !hasBadString;
    }

    private boolean hasPairedCharacters(String line) {
        for (int i = 0; i != line.length() - 1; i++) {
            if (line.charAt(i) == line.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDoublePairedCharacters(String line) {
        for (int i = 0; i != line.length() - 1; i++) {
            if (line.substring(i + 2).contains("" + line.charAt(i) + line.charAt(i + 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRepeatWithCharacterBetween(String line) {
        for (int i = 0; i != line.length() - 2; i++) {
            if (line.charAt(i) == line.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean niceMethodTwo(String line) {
        boolean hasPairWithoutOverlapping = hasDoublePairedCharacters(line);
        boolean repeatWithCharacterBetween = hasRepeatWithCharacterBetween(line);
        return hasPairWithoutOverlapping && repeatWithCharacterBetween;
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
