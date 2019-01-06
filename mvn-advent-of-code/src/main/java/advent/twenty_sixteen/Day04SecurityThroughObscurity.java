package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day04SecurityThroughObscurity implements DailyProblem<Integer, Integer> {
    private static final Comparator<Map.Entry<Character, Integer>> SORTED = (o1, o2) -> {
        if (o1.getValue() == o2.getValue())
            return Character.compare(o1.getKey(), o2.getKey());
        return Integer.compare(o2.getValue(), o1.getValue());
    };

    private final int part1Answer;
    private final int part2Answer;

    public Day04SecurityThroughObscurity(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        Map<String, Integer> decrypted = new HashMap<>();

        int count = 0;
        for (String line : lines) {
            count += checkAndDecrypt(line, decrypted);
        }

        this.part1Answer = count;
        this.part2Answer = decrypted.getOrDefault("northpole object storage", -1);
    }

    private int checkAndDecrypt(String line, Map<String, Integer> decrypted) {
        int index = line.lastIndexOf("-");
        String name = line.substring(0, index);
        int sector = Integer.parseInt(line.substring(index + 1, line.indexOf("[")));
        String checksum = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
        String realName = "";

        Map<Character, Integer> counts = new HashMap<>();
        for (Character c : name.toCharArray()) {
            if (c != '-') {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
                realName += (char) ('a' + ((c - 'a') + sector) % 26);
            } else {
                realName += " ";
            }
        }

        List<Map.Entry<Character, Integer>> sorted = counts.entrySet().stream().sorted(SORTED).collect(Collectors.toList());

        String calculatedChecksum = "";
        for (int i = 0; i != checksum.length(); i++) {
            calculatedChecksum += sorted.get(i).getKey();
        }

        if (checksum.equals(calculatedChecksum)) {
            decrypted.put(realName, sector);
            return sector;
        }

        return 0;
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
