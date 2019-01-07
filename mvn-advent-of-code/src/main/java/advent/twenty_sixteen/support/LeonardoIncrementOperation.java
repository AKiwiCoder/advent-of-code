package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoIncrementOperation implements ILeonardoOperation {
    private final String x;

    public LeonardoIncrementOperation(String x) {
        this.x = x;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        registers.put(x, registers.get(x) + 1);
        return ip + 1;
    }

    @Override
    public String toString() {
        return "inc " + x;
    }
}
