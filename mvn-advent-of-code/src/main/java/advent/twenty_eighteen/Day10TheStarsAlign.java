package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.common.MutablePoint;
import advent.common.Pair;
import advent.common.Velocity;
import advent.utilities.FileUtilities;

import java.util.List;

public class Day10TheStarsAlign implements DailyProblem<String, Integer> {
    private final int minimumSteps;
    private final String text;

    private static Pair<MutablePoint, Velocity> parse(String line) {
        String x = line.substring(line.indexOf('<') + 1, line.indexOf(',')).trim();
        String y = line.substring(line.indexOf(',') + 1, line.indexOf('>')).trim();

        String dx = line.substring(line.lastIndexOf('<') + 1, line.lastIndexOf(',')).trim();
        String dy = line.substring(line.lastIndexOf(',') + 1, line.lastIndexOf('>')).trim();

        MutablePoint p = new MutablePoint(Integer.parseInt(x), Integer.parseInt(y));
        Velocity v = new Velocity(Integer.parseInt(dx), Integer.parseInt(dy));
        return new Pair<>(p, v);
    }


    public Day10TheStarsAlign(String name) {
        List<Pair<MutablePoint, Velocity>> input = FileUtilities.readLines(name, Day10TheStarsAlign::parse);

        int previousWidth = Integer.MAX_VALUE;
        int previousHeight = Integer.MAX_VALUE;
        int currentWidth = Integer.MAX_VALUE;
        int currentHeight = Integer.MAX_VALUE;

        int top;
        int bottom = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right;

        int previousBottom = Integer.MAX_VALUE;
        int previousLeft = Integer.MAX_VALUE;


        int step = 0;

        while (previousWidth >= currentWidth && previousHeight >= currentHeight) {
            previousHeight = currentHeight;
            previousWidth = currentWidth;

            previousBottom = bottom;
            previousLeft = left;

            top = Integer.MIN_VALUE;
            bottom = Integer.MAX_VALUE;
            left = Integer.MAX_VALUE;
            right = Integer.MIN_VALUE;


            for (Pair<MutablePoint, Velocity> pv : input) {
                MutablePoint p = pv.getFirst();
                Velocity v = pv.getSecond();

                p.setX(p.getX() + v.getDx());
                p.setY(p.getY() + v.getDy());

                top = Math.max(p.getY(), top);
                bottom = Math.min(p.getY(), bottom);
                left = Math.min(p.getX(), left);
                right = Math.max(p.getX(), right);
            }

            currentWidth = right - left;
            currentHeight = top - bottom;
            step++;
        }

        this.minimumSteps = step - 1;

        char[][] dump = new char[previousHeight + 1][previousWidth + 1];
        for (Pair<MutablePoint, Velocity> pv : input) {
            int px = pv.getFirst().getX() - pv.getSecond().getDx();
            int py = pv.getFirst().getY() - pv.getSecond().getDy();
            dump[py - previousBottom][px - previousLeft] = '#';
        }

        String result = "";
        for (int y = 0; y != dump.length; y++) {
            for (int x = 0; x != dump[y].length; x++) {
                if (dump[y][x] == '#') {
                    result += "#";
                } else {
                    result += ',';
                }
            }
            result += "\n";
        }
        this.text = result;
    }

    @Override
    public String getPart1Answer() {
        return text;
    }

    @Override
    public Integer getPart2Answer() {
        return minimumSteps;
    }
}
