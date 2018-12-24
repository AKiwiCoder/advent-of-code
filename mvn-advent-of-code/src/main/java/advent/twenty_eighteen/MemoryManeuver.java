package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.StringUtilities;

import java.util.LinkedList;
import java.util.List;

public class MemoryManeuver implements DailyProblem<Integer, Integer> {
    private final List<Integer> input;

    public MemoryManeuver(String name) {
        String line = FileUtilities.readLines(name, Parsers::ToString).get(0);
        this.input = StringUtilities.splitLine(line, "\\s", Integer::parseInt);
    }

    private static int walkTree1(LinkedList<Integer> steps) {
        int numChildren = steps.pop();
        int numMetadata = steps.pop();

        int result = 0;
        for (int i = 0; i != numChildren; i++) {
            result += walkTree1(steps);
        }
        for (int i = 0; i != numMetadata; i++) {
            result += steps.pop();
        }
        return result;
    }

    private static int walkTree2(LinkedList<Integer> steps) {
        int numChildren = steps.pop();
        int numMetadata = steps.pop();

        int[] childValue = new int[numChildren];
        for (int i = 0; i != numChildren; i++) {
            childValue[i] = walkTree2(steps);
        }

        int result = 0;
        for (int i = 0; i != numMetadata; i++) {
            int metaData = steps.pop();
            if (numChildren == 0) {
                result += metaData;
            } else {
                if ((metaData - 1) >= numChildren) {
                    // skip
                } else {
                    result += childValue[metaData - 1];
                }
            }
        }
        return result;
    }


    @Override
    public Integer getPart1Answer() {
        return walkTree1(new LinkedList<>(input));
    }

    @Override
    public Integer getPart2Answer() {
        return walkTree2(new LinkedList<>(input));
    }
}
