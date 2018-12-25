package advent.twenty_eighteen.support;

import java.util.List;
import java.util.function.Function;

public class ElfComputer {
    private Statement[] statements;

    public ElfComputer(List<Statement> statements) {
        this.statements = statements.toArray(new Statement[0]);
    }

    public void execute(int ipRegister, int[] registers) {
        execute(ipRegister, registers, (ip) -> false);
    }

    public void execute(int ipRegister, int[] registers, Function<int[], Boolean> stopCondition) {
        int ip = 0;
        while (registers[ipRegister] < statements.length) {
            registers[ipRegister] = ip;
            Statement statement = statements[ip];
            statement.getMnemonic().execute(statement.getA(), statement.getB(), statement.getC(), registers);

            if (stopCondition.apply(registers)) {
                break;
            }

            ip = registers[ipRegister];
            ip++;
        }
    }
}
