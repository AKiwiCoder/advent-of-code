package advent.twenty_fifteen.support;

public class TuringInstructionJumpEven extends TuringInstruction {
    private int register;
    private int offset;

    public TuringInstructionJumpEven(int register, int offset) {
        this.register = register;
        this.offset = offset;
    }

    @Override
    public int execute(int ip, int[] registers) {
        if (registers[register] % 2 == 0) {
            return ip + offset;
        }
        return ip + 1;
    }
}
