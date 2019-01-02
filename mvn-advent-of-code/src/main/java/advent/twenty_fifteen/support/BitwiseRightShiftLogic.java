package advent.twenty_fifteen.support;

import java.util.Map;

public class BitwiseRightShiftLogic implements IBitwiseLogic {
    private final IInput output;
    private final IInput input;
    private final int count;

    public BitwiseRightShiftLogic(IInput output, IInput input, int count) {
        this.output = output;
        this.input = input;
        this.count = count;
    }

    @Override
    public boolean canBeApplied(Map<String, Integer> values) {
        return !output.canBeApplied(values) && input.canBeApplied(values);
    }

    @Override
    public void apply(Map<String, Integer> values) {
        values.put(output.getId(), input.getValue(values) >> count);
    }


    @Override
    public String toString() {
        return input + " RSHIFT " + count + " -> " + output;
    }
}
