package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.EDirection;
import advent.common.ETurn;
import advent.common.Point;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import java.util.HashSet;
import java.util.Set;

public class Day01NoTimeForATaxicab implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day01NoTimeForATaxicab(String filename) {
        String line = FileUtilities.readLines(filename, Parsers::TO_STRING).get(0);

        String[] directions = line.split(",");

        EDirection facing = EDirection.North;
        Point start = new Point(0, 0);
        Point current = start;

        Point firstVisitedTwice = null;
        Set<Point> visited = new HashSet<>();
        visited.add(current);

        for (String direction : directions) {
            direction = direction.trim();

            if (direction.startsWith("L")) {
                facing = EDirection.turn(facing, ETurn.Left);
            }
            if (direction.startsWith("R")) {
                facing = EDirection.turn(facing, ETurn.Right);
            }

            int count = Integer.parseInt(direction.substring(1).trim());
            for (int i = 0; i != count; i++) {
                current = PointUtilities.move(facing, current);
                if (firstVisitedTwice == null && visited.contains(current)) {
                    firstVisitedTwice = current;
                }
                visited.add(current);
            }
        }

        this.part1Answer = PointUtilities.calculateManhattenDistance(current, start);
        this.part2Answer = PointUtilities.calculateManhattenDistance(firstVisitedTwice, start);
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
