package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.List;

public class Day09ExplosivesInCyberspace implements DailyProblem<Long, Long> {
    private final long part1Answer;
    private final long part2Answer;

    public Day09ExplosivesInCyberspace(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        this.part1Answer = lines.stream().mapToLong(s -> decompress(s, false)).sum();
        this.part2Answer = lines.stream().mapToLong(s -> decompress(s, true)).sum();
    }

    private long decompress(String raw, boolean decompressSubstrings) {
        long output = 0;

        for (int i = 0; i != raw.length(); ) {
            if (raw.charAt(i) == '(') {
                int index = raw.indexOf("x", i);
                int width = Integer.parseInt(raw.substring(i+1, index));
                int index2 = raw.indexOf(")", index);
                int count = Integer.parseInt(raw.substring(index + 1, index2));

                String sub = raw.substring(index2 + 1, index2 + 1 + width);

                long subLength = sub.length();
                if (decompressSubstrings) {
                    subLength = decompress(raw.substring(index2 + 1, index2 + 1 + width), decompressSubstrings);
                }

                for (int c = 0; c != count; c++) {
                    output += subLength;
                }

                i = index2 + width + 1;
            } else {
                output++;
                i++;
            }
        }
        return output;
    }

    @Override
    public Long getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        return part2Answer;
    }
}
