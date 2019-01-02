package advent.twenty_fifteen.support;

import java.util.Map;

public class BitwiseWireInput implements IInput {
    private final String input;

    public BitwiseWireInput(String input) {
        this.input = input;
    }

    @Override
    public boolean canBeApplied(Map<String, Integer> values) {
        return values.containsKey(input);
    }

    @Override
    public int getValue(Map<String, Integer> values) {
        return values.get(input);
    }

    @Override
    public String getId() {
        return input;
    }

    @Override
    public String toString() {
        return input;
    }
}
