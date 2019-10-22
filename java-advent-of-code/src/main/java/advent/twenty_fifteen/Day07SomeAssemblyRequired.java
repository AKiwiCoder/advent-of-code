package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.twenty_fifteen.support.BitwiseParser;
import advent.twenty_fifteen.support.IBitwiseLogic;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day07SomeAssemblyRequired implements DailyProblem<Map<String, Integer>, Map<String, Integer>> {
    private final Map<String, Integer> part1Answer;
    private final Map<String, Integer> part2Answer;

    public Day07SomeAssemblyRequired(String filename) {
        List<IBitwiseLogic> operations = FileUtilities.readLines(filename, BitwiseParser::PARSE_BITWISE);

        this.part1Answer = new HashMap<>();
        executeLogic(part1Answer, new LinkedList<>(operations));

        this.part2Answer = new HashMap<>();
        part2Answer.put("b", part1Answer.get("a"));
        executeLogic(part2Answer, new LinkedList<>(operations));
    }

    private static void executeLogic(Map<String, Integer> values, List<IBitwiseLogic> working) {
        boolean somethingChanged = true;
        while (somethingChanged) {
            int previous = values.size();

            for (IBitwiseLogic operation : new LinkedList<>(working)) {
                if (operation.canBeApplied(values)) {
                    operation.apply(values);
                    working.remove(operation);
                }
            }
            somethingChanged = values.size() > previous;
        }
    }

    @Override
    public Map<String, Integer> getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Map<String, Integer> getPart2Answer() {
        return part2Answer;
    }
}
