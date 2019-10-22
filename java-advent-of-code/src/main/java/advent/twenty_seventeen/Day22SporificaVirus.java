package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.EDirection;
import advent.common.ETurn;
import advent.common.Point;
import advent.utilities.FileUtilities;
import advent.utilities.PointUtilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day22SporificaVirus implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day22SporificaVirus(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        Set<Point> infected = new HashSet<>();
        for (int row = 0; row != lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col != line.length(); col++) {
                if (line.charAt(col) == '#') {
                    infected.add(new Point(col, row));
                }
            }
        }
        Point start = new Point(lines.get(0).length() / 2, lines.size() / 2);

        this.part1Answer = walkPart1(new HashSet<>(infected), start, EDirection.North);
        this.part2Answer = walkPart2(new HashSet<>(infected), start, EDirection.North);
    }

    private int walkPart1(Set<Point> infected, Point current, EDirection facing) {
        int infectedSomething = 0;
        for (int i = 0; i != 10000; i++) {
            /*
             *  If the current node is infected, it turns to its right. Otherwise, it turns to its left.
             *      (Turning is done in-place; the current node does not change.)
             *  If the current node is clean, it becomes infected. Otherwise, it becomes cleaned.
             *      (This is done after the node is considered for the purposes of changing direction.)
             *  The virus carrier moves forward one node in the direction it is facing.
             */
            if (infected.contains(current)) {
                facing = EDirection.turn(facing, ETurn.Right);
                infected.remove(current);
            } else {
                facing = EDirection.turn(facing, ETurn.Left);
                infected.add(current);
                infectedSomething++;
            }
            current = PointUtilities.move(facing, current);
        }
        return infectedSomething;
    }

    private int walkPart2(Set<Point> initial, Point current, EDirection facing) {
        Set<Point> infected = new HashSet<>(initial);
        Set<Point> flagged = new HashSet<>();
        Set<Point> weakened = new HashSet<>();

        int infectedSomething = 0;
        for (int i = 0; i != 10000000; i++) {
            /*
                If it is clean, it turns left.
                If it is weakened, it does not turn, and will continue moving in the same direction.
                If it is infected, it turns right.
                If it is flagged, it reverses direction, and will go back the way it came.


                Clean nodes become weakened.
                Weakened nodes become infected.
                Infected nodes become flagged.
                Flagged nodes become clean.
             */
            if (infected.contains(current)) {
                facing = EDirection.turn(facing, ETurn.Right);
                infected.remove(current);
                flagged.add(current);
            } else if (weakened.contains(current)) {
                facing = EDirection.turn(facing, ETurn.Straight);
                weakened.remove(current);
                infected.add(current);
                infectedSomething++;
            } else if (flagged.contains(current)) {
                facing = EDirection.turn(facing, ETurn.Around);
                flagged.remove(current);
            } else {
                facing = EDirection.turn(facing, ETurn.Left);
                weakened.add(current);
            }

            current = PointUtilities.move(facing, current);
        }
        return infectedSomething;
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
