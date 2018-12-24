package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.common.Rectangle;
import advent.utilities.FileUtilities;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoMatterHowYouSliceIt implements DailyProblem<Integer, Integer> {
    private static final Pattern LINE = Pattern.compile("#([0-9]*) @ ([0-9]*),([0-9]*): ([0-9]*)x([0-9]*)");
    private final Integer part1Answer;
    private final Integer part2Answer;

    private static Pair<Integer, Rectangle> PARSER(String input) {
        Matcher match = LINE.matcher(input);

        if (match.matches()) {
            int number = Integer.parseInt(match.group(1));
            int left = Integer.parseInt(match.group(2));
            int top = Integer.parseInt(match.group(3));
            int width = Integer.parseInt(match.group(4));
            int height = Integer.parseInt(match.group(5));
            return new Pair<>(number, new Rectangle(top, left, top + height, left + width));
        } else {
            throw new IllegalArgumentException("Cannot parse line '" + input + "'");
        }
    }

    private static void updateRectangleInArray(String[][] array, Rectangle rectangle, Integer id) {
        for (int col = rectangle.getLeft(); col != rectangle.getRight(); col++) {
            for (int row = rectangle.getTop(); row != rectangle.getBottom(); row++) {
                array[col][row] += id + ",";
            }
        }
    }

    public NoMatterHowYouSliceIt(String filename) {
        List<Pair<Integer, Rectangle>> input = FileUtilities.readLines(filename, NoMatterHowYouSliceIt::PARSER);

        int lowest = input.stream().map(p -> p.getSecond().getBottom()).max(Comparator.naturalOrder()).get();
        int furthest = input.stream().map(p -> p.getSecond().getRight()).max(Comparator.naturalOrder()).get();

        String[][] fabric = new String[furthest][lowest];
        for (int c = 0; c != fabric.length; c++) {
            fabric[c] = new String[fabric[c].length];
            for (int r = 0; r != fabric[c].length; r++) {
                fabric[c][r] = "";
            }
        }

        Set<Integer> numbers = new HashSet<>();
        input.stream().forEach(pair -> {
            numbers.add(pair.getFirst());
            updateRectangleInArray(fabric, pair.getSecond(), pair.getFirst());
        });

        int overlapping = 0;
        for (int c = 0; c != fabric.length; c++) {
            for (int r = 0; r != fabric[c].length; r++) {
                if (fabric[c][r].length() > 1) {
                    String[] split = fabric[c][r].split(",");
                    if (split.length > 1) {
                        for (int i = 0; i != split.length; i++) {
                            Integer n = Integer.parseInt(split[i].replace(",", ""));
                            numbers.remove(n);
                        }
                        overlapping++;
                    }
                }
            }
        }

        this.part1Answer = overlapping;
        this.part2Answer = numbers.iterator().next();
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
