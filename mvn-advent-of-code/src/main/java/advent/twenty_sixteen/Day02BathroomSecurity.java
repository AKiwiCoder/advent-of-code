package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

public class Day02BathroomSecurity implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day02BathroomSecurity(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        char[][] keypadPart1 = new char[][]{ //
                {'*', '*', '*', '*', '*'}, //
                {'*', '1', '2', '3', '*'}, //
                {'*', '4', '5', '6', '*'}, //
                {'*', '7', '8', '9', '*'}, //
                {'*', '*', '*', '*', '*'}  //
        };


        char[][] keypadPart2 = new char[][]{
                {'*', '*', '*', '*', '*', '*', '*'}, //
                {'*', '*', '*', '1', '*', '*', '*'}, //
                {'*', '*', '2', '3', '4', '*', '*'}, //
                {'*', '5', '6', '7', '8', '9', '*'}, //
                {'*', '*', 'A', 'B', 'C', '*', '*'}, //
                {'*', '*', '*', 'D', '*', '*', '*'}, //
                {'*', '*', '*', '*', '*', '*', '*'}  //
        };

        this.part1Answer = walkKeypad(lines, 2, 2, keypadPart1);
        this.part2Answer = walkKeypad(lines, 3, 1, keypadPart2);
    }

    private String walkKeypad(List<String> lines, int r, int c, char[][] keypad) {
        String code = "";
        for (String line : lines) {
            for (char chr : line.toCharArray()) {
                int pr = r;
                int pc = c;
                switch (chr) {
                    case 'U':
                        r--;
                        break;
                    case 'D':
                        r++;
                        break;
                    case 'L':
                        c--;
                        break;
                    case 'R':
                        c++;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown step " + chr);
                }
                if (keypad[r][c] == '*') {
                    r = pr;
                    c = pc;
                }
            }
            code += keypad[r][c];
        }
        return code;
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
