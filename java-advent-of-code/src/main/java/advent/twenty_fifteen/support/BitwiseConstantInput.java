package advent.twenty_fifteen.support;

import java.util.Map;

public final class BitwiseConstantInput implements IInput {
    private final int constant;

    public BitwiseConstantInput(int constant) {
        this.constant = constant;
    }

    @Override
    public boolean canBeApplied(Map<String, Integer> values) {
        return true;
    }

    @Override
    public int getValue(Map<String, Integer> values) {
        return constant;
    }

    @Override
    public String getId() {
        throw new IllegalStateException("Cannot get id of constant");
    }

    @Override
    public String toString() {
        return "" + constant;
    }
}
