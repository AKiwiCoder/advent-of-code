package advent.twenty_seventeen.support;

import java.util.function.BiFunction;

public class DecrementOperation extends Instruction {
    public DecrementOperation(String register, int delta,String compareRegister, int compareValue,  BiFunction<Integer, Integer, Boolean> conditional) {
        super(register, delta, compareRegister, compareValue, conditional);
    }

    @Override
    protected int calc(int current, int delta) {
        return current - delta;
    }
}
