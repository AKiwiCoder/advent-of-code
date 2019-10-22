package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.support.IScrambleCommand;
import advent.utilities.FileUtilities;
import advent.utilities.StringUtilities;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Day21ScrambledLettersAndHash implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day21ScrambledLettersAndHash(String filename, String toEncode, String toDecode) {
        List<IScrambleCommand> commands = FileUtilities.readLines(filename, IScrambleCommand::PARSE);

        List<Character> encode = StringUtilities.toCharacterList(toEncode);

        for (IScrambleCommand command : commands) {
            command.encode(encode);
        }

        List<Character> decode = new LinkedList<>();
        if (toDecode != null) {
            decode = StringUtilities.toCharacterList(toDecode);

            Collections.reverse(commands);
            for (IScrambleCommand command : commands) {
                command.decode(decode);
            }
        }

        this.part1Answer = StringUtilities.toString(encode);
        this.part2Answer = StringUtilities.toString(decode);
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
