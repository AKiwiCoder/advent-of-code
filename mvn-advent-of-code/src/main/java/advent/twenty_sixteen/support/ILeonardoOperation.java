package advent.twenty_sixteen.support;

import java.util.Map;

public interface ILeonardoOperation {
    int execute(int ip, Map<String, Integer> registers);

    static ILeonardoOperation PARSE(String line) {
        if (line.startsWith("cpy")) {
            // cpy x y
            String[] bits = line.split("\\s+");
            return new LeonardoCopyOperation(bits[1], bits[2]);
        } else if (line.startsWith("inc")) {
            // inc x
            String[] bits = line.split("\\s+");
            return new LeonardoIncrementOperation(bits[1]);
        } else if (line.startsWith("dec")) {
            // dec x
            String[] bits = line.split("\\s+");
            return new LeonardoDecrementOperation(bits[1]);
        } else if (line.startsWith("jnz")) {
            // jnz x y
            String[] bits = line.split("\\s+");
            return new LeonardoJumpNotZeroOperation(bits[1], bits[2]);
        }
        throw new IllegalStateException("Cannot parse '" + line + "'");
    }
}
