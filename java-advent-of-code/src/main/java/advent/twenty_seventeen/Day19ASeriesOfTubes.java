package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.EDirection;
import advent.common.ETurn;
import advent.common.Point;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import javax.swing.text.EditorKit;
import java.util.*;

public class Day19ASeriesOfTubes implements DailyProblem<String, Integer> {
    private final String part1Answer;
    private final int part2Answer;

    public Day19ASeriesOfTubes(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING_NO_TRIM);

        char[][] map = new char[lines.size()][];
        for (int row = 0; row != lines.size(); row++) {
            map[row] = lines.get(row).toCharArray();
        }

        int col = findStartColumn(map);
        int row = 0;

        Point p = new Point(col, row);
        EDirection direction = EDirection.South;

        int steps = 0;
        String path = "";
        boolean finished = false;
        while (!finished) {
            p = PointUtilities.move(direction, p);
            char c = map[p.getRow()][p.getCol()];
            switch (c) {
                case '+':
                    Point left = PointUtilities.move(EDirection.turn(direction, ETurn.Left), p);
                    Point right = PointUtilities.move(EDirection.turn(direction, ETurn.Right), p);
                    if (valid(map, left.getRow(), left.getCol()) && map[left.getRow()][left.getCol()] != ' ') {
                        direction = EDirection.turn(direction, ETurn.Left);
                    } else if (valid(map, right.getRow(), right.getCol()) && map[right.getRow()][right.getCol()] != ' ') {
                        direction = EDirection.turn(direction, ETurn.Right);
                    }
                    break;
                case ' ':
                    finished = true;
                    break;
                default:
                    if (Character.isLetter(c))
                        path += Character.toString(c);
                    break;
            }
            steps++;
        }

        this.part1Answer = path;
        this.part2Answer = steps;
    }

    private boolean valid(char[][] map, int row, int col) {
        return (row >= 0 && row < map.length && col >= 0 && col < map[row].length);
    }

    private int findStartColumn(char[][] map) {
        for (int c = 0; c != map.length; c++) {
            if (map[0][c] == '|') {
                return c;
            }
        }
        return -1;
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
