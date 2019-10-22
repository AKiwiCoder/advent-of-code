package advent.twenty_fifteen.support;

public class TuringInstructionJumpIfOne extends TuringInstruction {
    private int register;
    private int offset;

    public TuringInstructionJumpIfOne(int register, int offset) {
        this.register = register;
        this.offset = offset;
    }

    @Override
    public int execute(int ip, int[] registers) {
        if (registers[register] == 1) {
            return ip + offset;
        }
        return ip + 1;
    }
}
