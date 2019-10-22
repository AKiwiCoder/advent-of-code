package advent.twenty_fifteen.support;

import java.util.Map;

public interface IInput {
    boolean canBeApplied(Map<String, Integer> values);

    int getValue(Map<String, Integer> values);

    String getId();
}
