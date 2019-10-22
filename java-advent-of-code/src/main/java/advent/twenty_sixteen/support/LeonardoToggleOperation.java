package advent.twenty_sixteen.support;

import java.util.List;
import java.util.Map;

public class LeonardoToggleOperation implements ILeonardoOperation {
    private final String x;

    public LeonardoToggleOperation(String x) {
        this.x = x;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        throw new UnsupportedOperationException("Don't try and execute this ... cast and use the modify() on the program");
    }

    @Override
    public ILeonardoOperation toggle() {
        return new LeonardoIncrementOperation(x);
    }

    /*
    For one-argument instructions, inc becomes dec, and all other one-argument instructions become inc.
    For two-argument instructions, jnz becomes cpy, and all other two-instructions become jnz.
    The arguments of a toggled instruction are not affected.
    If an attempt is made to toggle an instruction outside the program, nothing happens.
    If toggling produces an invalid instruction (like cpy 1 2) and an attempt is later made to execute that instruction, skip it instead.
    If tgl toggles itself (for example, if a is 0, tgl a would target itself and become inc a), the resulting instruction is not executed until the next time it is reached.
     */
    public int modify(int ip, Map<String, Integer> registers, List<ILeonardoOperation> program) {
        int offset = registers.get(x);

        int index = ip + offset;
        if (index >= 0 && index < program.size()) {
            ILeonardoOperation original = program.get(index);
            ILeonardoOperation newOp = original.toggle();
            program.set(index, newOp);
        }

        return ip + 1;
    }

    @Override
    public String toString() {
        return "tgl " + x;
    }
}
