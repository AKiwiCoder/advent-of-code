package advent.twenty_fifteen.support;

import java.util.Map;

public class BitwiseOrLogic implements IBitwiseLogic {
    private final IInput output;
    private final IInput lhs;
    private final IInput rhs;

    public BitwiseOrLogic(IInput output, IInput lhs, IInput rhs) {
        this.output = output;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public boolean canBeApplied(Map<String, Integer> values) {
        return !output.canBeApplied(values) && lhs.canBeApplied(values) && rhs.canBeApplied(values);
    }

    @Override
    public void apply(Map<String, Integer> values) {
        values.put(output.getId(), lhs.getValue(values) | rhs.getValue(values));
    }

    @Override
    public String toString() {
        return lhs + " OR " + rhs + " -> " + output;
    }
}
