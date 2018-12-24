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
}
