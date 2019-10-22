package advent.twenty_fifteen.support;

import java.util.Map;

public interface IBitwiseLogic {
    boolean canBeApplied(Map<String, Integer> values);

    void apply(Map<String, Integer> values);
}
