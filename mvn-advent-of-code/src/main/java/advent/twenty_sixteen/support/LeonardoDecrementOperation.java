package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoDecrementOperation implements ILeonardoOperation {
    private final String x;

    public LeonardoDecrementOperation(String x) {
        this.x = x;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        registers.put(x, registers.get(x) - 1);
        return ip + 1;
    }

    @Override
    public String toString() {
        return "dec " + x;
    }
}
