package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.*;

public class Day16AuntSue implements DailyProblem<Integer, Integer> {

    // Sue 464: pomeranians: 8, cars: 5, vizslas: 0
    private static Map<String, Integer> PARSE(String line) {
        Map<String, Integer> result = new HashMap<>();

        int index = line.indexOf(":");
        result.put("INDEX", Integer.parseInt(line.substring(3, index).trim()));
        String[] bits = line.substring(index + 1).split(",");
        for (String bit : bits) {
            String[] chunks = bit.split(":");
            result.put(chunks[0].trim(), Integer.parseInt(chunks[1].trim()));
        }

        result.put("SCORE 1", calculateScoreOne(result));
        result.put("SCORE 2", calculateScoreTwo(result));

        return result;
    }

    private static int calculateScoreOne(Map<String, Integer> value) {
        int score = 0;

        if (Integer.valueOf(3).equals(value.get("children"))) score++;
        if (Integer.valueOf(7).equals(value.get("cats"))) score++;
        if (Integer.valueOf(2).equals(value.get("samoyeds"))) score++;
        if (Integer.valueOf(3).equals(value.get("pomeranians"))) score++;
        if (Integer.valueOf(0).equals(value.get("akitas"))) score++;
        if (Integer.valueOf(0).equals(value.get("vizslas"))) score++;
        if (Integer.valueOf(5).equals(value.get("goldfish"))) score++;
        if (Integer.valueOf(3).equals(value.get("trees"))) score++;
        if (Integer.valueOf(2).equals(value.get("cars"))) score++;
        if (Integer.valueOf(1).equals(value.get("perfumes"))) score++;

        return score;
    }

    private static int calculateScoreTwo(Map<String, Integer> value) {
        int score = 0;

        if (Integer.valueOf(3).equals(value.get("children"))) score++;
        if (Integer.valueOf(2).equals(value.get("samoyeds"))) score++;
        if (Integer.valueOf(0).equals(value.get("akitas"))) score++;
        if (Integer.valueOf(0).equals(value.get("vizslas"))) score++;
        if (Integer.valueOf(2).equals(value.get("cars"))) score++;
        if (Integer.valueOf(1).equals(value.get("perfumes"))) score++;

        if (value.containsKey("cats") && Integer.valueOf(7).compareTo(value.get("cats")) <= 0) score++;
        if (value.containsKey("trees") && Integer.valueOf(3).compareTo(value.get("trees")) <= 0) score++;

        if (value.containsKey("pomeranians") && Integer.valueOf(3).compareTo(value.get("pomeranians")) >= 0) score++;
        if (value.containsKey("goldfish") && Integer.valueOf(5).compareTo(value.get("goldfish")) >= 0) score++;

        return score;
    }

    private final int part1Answer;
    private final int part2Answer;

    public Day16AuntSue(String filename) {
        List<Map<String, Integer>> lines = FileUtilities.readLines(filename, Day16AuntSue::PARSE);

        lines.sort(Comparator.comparingLong(o -> o.get("SCORE 1")));
        Collections.reverse(lines);

        this.part1Answer = lines.get(0).get("INDEX");

        lines.sort(Comparator.comparingLong(o -> o.get("SCORE 2")));
        Collections.reverse(lines);

        this.part2Answer = lines.get(0).get("INDEX");
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
