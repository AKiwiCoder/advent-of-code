package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Point;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import java.util.HashSet;
import java.util.Set;

public class Day03PerfectlySphericalHousesInAVacuum implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;


    public Day03PerfectlySphericalHousesInAVacuum(String filename) {
        String line = FileUtilities.readLines(filename, Parsers::TO_STRING).get(0);

        boolean santasTurn = true;

        Set<Point> part1Visits = new HashSet<>();
        Set<Point> part2Visits = new HashSet<>();

        Point current = new Point(0, 0);
        Point santa = new Point(0, 0);
        Point roboSanta = new Point(0, 0);

        part1Visits.add(current);
        part2Visits.add(santa);
        part2Visits.add(roboSanta);
        for (char c : line.toCharArray()) {
            current = deliverPresent(current, c);

            if (santasTurn) {
                santa = deliverPresent(santa, c);
            } else {
                roboSanta = deliverPresent(roboSanta, c);
            }
            santasTurn = !santasTurn;

            part1Visits.add(current);
            part2Visits.add(santa);
            part2Visits.add(roboSanta);
        }

        this.part1Answer = part1Visits.size();
        this.part2Answer = part2Visits.size();
    }

    private Point deliverPresent(Point current, char c) {
        switch (c) {
            case '^':
                current = PointUtilities.north(current);
                break;
            case 'v':
                current = PointUtilities.south(current);
                break;
            case '<':
                current = PointUtilities.west(current);
                break;
            case '>':
                current = PointUtilities.east(current);
                break;
            default:
                throw new IllegalStateException("Unknown character " + c);
        }
        return current;
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
