package advent.twenty_fifteen.support;

public class BitwiseParser {
    /*
 123 -> x
 x AND y -> z
 x OR y -> z
 p LSHIFT 2 -> q
 p RSHIFT 2 -> q
 NOT e -> f
 */
    public static IBitwiseLogic PARSE_BITWISE(String line) {
        String[] bits = line.split(" ");
        if (bits.length == 3) {
            return new BitwiseSignalLogic(PARSE_INPUT(bits[2]), PARSE_INPUT(bits[0]));
        }
        if (bits.length == 5 && line.contains("AND")) {
            return new BitwiseAndLogic(PARSE_INPUT(bits[4]), PARSE_INPUT(bits[0]), PARSE_INPUT(bits[2]));
        }
        if (bits.length == 5 && line.contains("OR")) {
            return new BitwiseOrLogic(PARSE_INPUT(bits[4]), PARSE_INPUT(bits[0]), PARSE_INPUT(bits[2]));
        }
        if (bits.length == 5 && line.contains("LSHIFT")) {
            return new BitwiseLeftShiftLogic(PARSE_INPUT(bits[4]), PARSE_INPUT(bits[0]), Integer.parseInt(bits[2]));
        }
        if (bits.length == 5 && line.contains("RSHIFT")) {
            return new BitwiseRightShiftLogic(PARSE_INPUT(bits[4]), PARSE_INPUT(bits[0]), Integer.parseInt(bits[2]));
        }
        if (bits.length == 4) {
            return new BitwiseNotLogic(PARSE_INPUT(bits[3]), PARSE_INPUT(bits[1]));
        }
        throw new IllegalArgumentException("Cannot parse '" + line + "'");
    }

    public static IInput PARSE_INPUT(String input) {
        try {
            return new BitwiseConstantInput(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return new BitwiseWireInput(input);
        }
    }
}