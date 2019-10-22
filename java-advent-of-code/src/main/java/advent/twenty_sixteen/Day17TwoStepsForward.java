package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Point;
import advent.utilities.MessageDigestUtilities;
import advent.utilities.PointUtilities;

import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Day17TwoStepsForward implements DailyProblem<String, Integer> {
    private final String part1Answer;
    private final int part2Answer;

    private static final Point destination = new Point(3, 3);

    public Day17TwoStepsForward(String passcode) throws NoSuchAlgorithmException {
        MessageDigestUtilities md = new MessageDigestUtilities();

        Point start = new Point(0, 0);

        Set<String> solutions = new HashSet<>();
        generatePath(md, passcode, start, "", solutions);

        this.part1Answer = solutions.stream().sorted(Comparator.comparingInt(String::length)).findFirst().get();
        this.part2Answer = solutions.stream().sorted((o1,o2) -> Integer.compare(o2.length(), o1.length())).findFirst().get().length();
    }

    private void generatePath(MessageDigestUtilities md, String passcode, Point current, String path, Set<String> solutions) {
        int minSoFar = solutions.stream().mapToInt(s -> s.length()).min().orElse(Integer.MAX_VALUE);

        if (current.equals(destination)) {
            solutions.add(path);
            return;
        }

        String code = md.md5hashString(passcode + path);

        // Up
        if (current.getY() > 0 && open(code.charAt(0))) {
            generatePath(md, passcode, PointUtilities.north(current), path + "U", solutions);
        }
        // Down
        if (current.getY() < 3 && open(code.charAt(1))) {
            generatePath(md, passcode, PointUtilities.south(current), path + "D", solutions);
        }
        // Left
        if (current.getX() > 0 && open(code.charAt(2))) {
            generatePath(md, passcode, PointUtilities.west(current), path + "L", solutions);
        }
        // Right
        if (current.getX() < 3 && open(code.charAt(3))) {
            generatePath(md, passcode, PointUtilities.east(current), path + "R", solutions);
        }
        return;
    }

    private boolean open(char c) {
        return c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f';
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
