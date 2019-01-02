package advent.twenty_fifteen.support;

import java.util.Map;

public class BitwiseSignalLogic implements IBitwiseLogic {
    private final IInput output;
    private final IInput input;

    public BitwiseSignalLogic(IInput output, IInput input) {
        this.output = output;
        this.input = input;
    }

    @Override
    public boolean canBeApplied(Map<String, Integer> values) {
        return !output.canBeApplied(values) && input.canBeApplied(values);
    }

    @Override
    public void apply(Map<String, Integer> values) {
        values.put(output.getId(), input.getValue(values));
    }


    @Override
    public String toString() {
        return input + " -> " + output;
    }
}
