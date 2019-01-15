package advent.twenty_seventeen.support;

import java.util.Map;
import java.util.function.BiFunction;

public abstract class Instruction {
    private final String register;
    private final int delta;
    private final String compareRegister;
    private final int compareValue;
    private final BiFunction<Integer, Integer, Boolean> conditional;

    protected Instruction(String register, int delta, String compareRegister, int compareValue, BiFunction<Integer, Integer, Boolean> conditional) {
        this.register = register;
        this.delta = delta;
        this.compareRegister = compareRegister;
        this.compareValue = compareValue;
        this.conditional = conditional;
    }

    public void apply(Map<String, Integer> registers) {
        Integer current = registers.getOrDefault(register, 0);
        if (conditional.apply(registers.getOrDefault(compareRegister, 0), compareValue)) {
            registers.put(register, calc(current, delta));
        } else {
            registers.put(register, current);
        }
    }

    protected abstract int calc(int current, int delta);

    public static Instruction PARSE(String line) {
        String[] bits = line.split("\\s+");

        BiFunction<Integer, Integer, Boolean> conditional;
        switch (bits[5]) {
            case "<":
                conditional = (l,r) -> l.intValue() < r.intValue();
                break;
            case ">":
                conditional = (l,r) -> l.intValue() > r.intValue();
                break;
            case ">=":
                conditional = (l,r) -> l.intValue() >= r.intValue();
                break;
            case "<=":
                conditional = (l,r) -> l.intValue() <= r.intValue();
                break;
            case "==":
                conditional = (l,r) -> l.intValue() == r.intValue();
                break;
            case "!=":
                conditional = (l,r) -> l.intValue() != r.intValue();
                break;
            default:
                throw new IllegalStateException("Cannot parse " + line);
        }

        if (bits[1].equals("inc")) {
            return new IncrementOperation(bits[0], Integer.parseInt(bits[2]),bits[4], Integer.parseInt(bits[6]), conditional);
        } else if (bits[1].equals("dec")) {
            return new DecrementOperation(bits[0], Integer.parseInt(bits[2]), bits[4], Integer.parseInt(bits[6]), conditional);
        }
        throw new IllegalStateException("Cannot parse " + line);
    }
}
