package advent.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface StringUtilities {
    static <T> List<T> splitLine(String input, String delimiter, Function<String, T> parser) {
        String[] bits = input.split(delimiter);
        List<T> result = new ArrayList<>(bits.length);
        for (int i = 0; i != bits.length; i++) {
            result.add(parser.apply(bits[i]));
        }
        return result;
    }

    static int countOccurrences(String line, char target) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    char[] hexArray = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
