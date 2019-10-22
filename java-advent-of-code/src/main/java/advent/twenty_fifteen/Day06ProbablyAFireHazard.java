package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.twenty_fifteen.support.Light;
import advent.twenty_fifteen.support.LightCommand;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day06ProbablyAFireHazard implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day06ProbablyAFireHazard(String filename) {
        List<LightCommand> commands = FileUtilities.readLines(filename, LightCommand::PARSE);

        Light[][] lights = new Light[1000][];
        for (int row = 0; row != 1000; row++) {
            lights[row] = new Light[1000];
            for (int col = 0; col != 1000; col++) {
                lights[row][col] = new Light();
            }
        }

        commands.forEach(command -> {
            for (int r = command.getStartRow(); r != command.getEndRow() + 1; r++) {
                for (int c = command.getStartCol(); c != command.getEndCol() + 1; c++) {
                    command.modify(lights[r][c]);
                }
            }
        });

        int nicePart1 = 0;
        int nicePart2 = 0;

        for (int row = 0; row != 1000; row++) {
            for (int col = 0; col != 1000; col++) {
                if (lights[row][col].isOn()) {
                    nicePart1++;
                }
                nicePart2 += lights[row][col].getBrightness();
            }
        }

        this.part1Answer = nicePart1;
        this.part2Answer = nicePart2;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
