package advent.twenty_sixteen.support;

import java.util.Map;

public interface ILeonardoOperation {
    int execute(int ip, Map<String, Integer> registers);

    ILeonardoOperation toggle();

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
        } else if (line.startsWith("tgl")) {
            // tgl x
            String[] bits = line.split("\\s+");
            return new LeonardoToggleOperation(bits[1]);
        } else if (line.startsWith("out")) {
            // out x
            String[] bits = line.split("\\s+");
            return new LeonardoOutOperation(bits[1]);
        }
        throw new IllegalStateException("Cannot parse '" + line + "'");
    }
}
