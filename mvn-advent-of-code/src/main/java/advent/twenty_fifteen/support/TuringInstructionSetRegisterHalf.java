package advent.twenty_fifteen.support;

public class TuringInstructionSetRegisterHalf extends TuringInstruction {
    private int register;

    public TuringInstructionSetRegisterHalf(int register) {
        this.register = register;
    }

    @Override
    public int execute(int ip, int[] registers) {
        registers[register] = registers[register] / 2;
        return ip + 1;
    }
}
