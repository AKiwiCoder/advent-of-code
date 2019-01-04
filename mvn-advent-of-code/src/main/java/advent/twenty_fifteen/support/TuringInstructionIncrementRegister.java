package advent.twenty_fifteen.support;

public class TuringInstructionIncrementRegister extends TuringInstruction {
    private int register;

    public TuringInstructionIncrementRegister(int register) {
        this.register = register;
    }

    @Override
    public int execute(int ip, int[] registers) {
        registers[register] += 1;
        return ip + 1;
    }
}
