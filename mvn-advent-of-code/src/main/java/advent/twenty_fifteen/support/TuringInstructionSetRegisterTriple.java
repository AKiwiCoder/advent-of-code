package advent.twenty_fifteen.support;

public class TuringInstructionSetRegisterTriple extends TuringInstruction {
    private int register;

    public TuringInstructionSetRegisterTriple(int register) {
        this.register = register;
    }

    @Override
    public int execute(int ip, int[] registers) {
        registers[register] = registers[register] * 3;
        return ip + 1;
    }
}
