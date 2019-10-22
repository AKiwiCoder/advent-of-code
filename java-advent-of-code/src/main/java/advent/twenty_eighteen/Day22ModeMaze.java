package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.common.Point;
import advent.twenty_eighteen.support.Step;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import java.util.*;

public class Day22ModeMaze implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    enum Type {
        Rocky, Wet, Narrow, Source, Target
    }

    private final long[][] geological;
    private final long[][] erosion;
    private final Type[][] types;

    public Day22ModeMaze(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        int depth = Integer.parseInt(lines.get(0).substring(7));
        String[] bits = lines.get(1).substring(8).split(",");
        int targetX = Integer.parseInt(bits[0]);
        int targetY = Integer.parseInt(bits[1]);

        int maxWidth = targetX + 200;
        int maxHeight = targetY + 50;

        this.geological = new long[maxHeight][];
        this.erosion = new long[maxHeight][];
        this.types = new Type[maxHeight][];
        for (int y = 0; y != geological.length; y++) {
            geological[y] = new long[maxWidth];
            erosion[y] = new long[maxWidth];
            types[y] = new Type[maxWidth];
        }

        for (int y = 0; y != geological.length; y++) {
            for (int x = 0; x != geological[y].length; x++) {
                if (x == 0 && y == 0) {
                    geological[y][x] = 0;
                } else if (x == targetX && y == targetY) {
                    geological[y][x] = 0;
                } else if (x == 0) {
                    geological[y][x] = y * 48271;
                } else if (y == 0) {
                    geological[y][x] = x * 16807;
                } else {
                    geological[y][x] = erosion[y][x - 1] * erosion[y - 1][x];
                }

                erosion[y][x] = (depth + geological[y][x]) % 20183;

                if (erosion[y][x] % 3 == 0) {
                    types[y][x] = Type.Rocky;
                } else if (erosion[y][x] % 3 == 1) {
                    types[y][x] = Type.Wet;
                } else {
                    types[y][x] = Type.Narrow;
                }
            }
        }

        int risk = 0;
        for (int y = 0; y != targetY + 1; y++) {
            for (int x = 0; x != targetX + 1; x++) {
                if (types[y][x] == Type.Rocky)
                    risk += 0;
                if (types[y][x] == Type.Wet)
                    risk += 1;
                if (types[y][x] == Type.Narrow)
                    risk += 2;
            }
        }
        this.part1Answer = risk;

        types[0][0] = Type.Source;
        types[targetY][targetX] = Type.Target;
        this.part2Answer = calculateQuickestPath(targetX, targetY);
    }

    private static final long TOOL_CHANGE_TIME = 7;
    private static final long STEP_TIME = 1;

    private static final Point ORIGIN = new Point(0, 0);

    private static final Comparator<Step> SORT = Comparator.comparingLong((Step o) -> o.getCost()).thenComparingInt(o -> PointUtilities.calculateManhattenDistance(ORIGIN, o.getPoint()));

    private Map<Point, Map<Step.Tool, Long>> bestStepForTool = new HashMap<>();
    private LinkedList<Step> steps = new LinkedList<>();

    private int calculateQuickestPath(int targetX, int targetY) {
        steps.push(new Step(ORIGIN, 0, Step.Tool.Torch));

        Point target = new Point(targetX, targetY);

        while (!steps.isEmpty()) {
            steps.sort(SORT);
            Step previousStep = steps.pop();

            considerNeighbour(previousStep, PointUtilities.north(previousStep.getPoint()));
            considerNeighbour(previousStep, PointUtilities.south(previousStep.getPoint()));
            considerNeighbour(previousStep, PointUtilities.west(previousStep.getPoint()));
            considerNeighbour(previousStep, PointUtilities.east(previousStep.getPoint()));
        }

        return bestStepForTool.get(target).get(Step.Tool.Torch).intValue();
    }

    private void considerNeighbour(Step previousStep, Point point) {
        if (point.getX() < 0 || point.getY() < 0 || point.getY() >= types.length || point.getX() >= types[0].length)
            return;

        if (toolPermitted(previousStep.getTool(), point)) {
            // If tool is permitted keep going
            long stepTime = previousStep.getCost() + STEP_TIME;
            if (!hasBestPoint(point, previousStep.getTool()) || getBestForPoint(point, previousStep.getTool()) > stepTime) {
                addBestPoint(point, previousStep.getTool(), stepTime);
                steps.push(new Step(point, stepTime, previousStep.getTool()));
            }
        } else {
            long stepTime = previousStep.getCost() + STEP_TIME + TOOL_CHANGE_TIME;

            // Tool not permitted
            for (Step.Tool tool : allowableTools(previousStep.getPoint(), point)) {
                if (!hasBestPoint(point, tool) || getBestForPoint(point, tool) > stepTime) {
                    addBestPoint(point, tool, stepTime);
                    steps.push(new Step(point, stepTime, tool));
                }
            }
        }
    }

    private boolean hasBestPoint(Point key, Step.Tool tool) {
        if (bestStepForTool.containsKey(key)) {
            return bestStepForTool.get(key).containsKey(tool);
        }
        return false;
    }

    private void addBestPoint(Point key, Step.Tool tool, long dist) {
        if (!bestStepForTool.containsKey(key)) {
            bestStepForTool.put(key, new HashMap<>());
        }
        bestStepForTool.get(key).put(tool, dist);
    }

    public long getBestForPoint(Point key, Step.Tool tool) {
        if (!bestStepForTool.containsKey(key)) {
            bestStepForTool.put(key, new HashMap<>());
        }
        return bestStepForTool.get(key).get(tool);
    }

    private boolean toolPermitted(Step.Tool tool, Point point) {
        switch (types[point.getY()][point.getX()]) {
            case Rocky:
                return tool == Step.Tool.ClimbingGear || tool == Step.Tool.Torch;
            case Wet:
                return tool == Step.Tool.ClimbingGear || tool == Step.Tool.Neither;
            case Narrow:
                return tool == Step.Tool.Torch || tool == Step.Tool.Neither;
            case Source:
            case Target:
                return tool == Step.Tool.Torch;
        }
        throw new IllegalArgumentException("No permitted tools " + tool + " " + point);
    }

    private static final Map<Pair<Type, Type>, Step.Tool[]> ALLOWABLE_TOOLS = new HashMap<>();

    static {
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Rocky, Type.Rocky), new Step.Tool[]{Step.Tool.ClimbingGear, Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Rocky, Type.Narrow), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Rocky, Type.Wet), new Step.Tool[]{Step.Tool.ClimbingGear});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Rocky, Type.Source), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Rocky, Type.Target), new Step.Tool[]{Step.Tool.Torch});

        ALLOWABLE_TOOLS.put(new Pair<>(Type.Narrow, Type.Rocky), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Narrow, Type.Narrow), new Step.Tool[]{Step.Tool.Torch, Step.Tool.Neither});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Narrow, Type.Wet), new Step.Tool[]{Step.Tool.Neither});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Narrow, Type.Source), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Narrow, Type.Target), new Step.Tool[]{Step.Tool.Torch});

        ALLOWABLE_TOOLS.put(new Pair<>(Type.Wet, Type.Rocky), new Step.Tool[]{Step.Tool.ClimbingGear});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Wet, Type.Narrow), new Step.Tool[]{Step.Tool.Neither});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Wet, Type.Wet), new Step.Tool[]{Step.Tool.ClimbingGear, Step.Tool.Neither});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Wet, Type.Source), new Step.Tool[]{});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Wet, Type.Target), new Step.Tool[]{});

        ALLOWABLE_TOOLS.put(new Pair<>(Type.Source, Type.Rocky), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Source, Type.Narrow), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Source, Type.Wet), new Step.Tool[]{});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Source, Type.Source), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Source, Type.Target), new Step.Tool[]{Step.Tool.Torch});

        ALLOWABLE_TOOLS.put(new Pair<>(Type.Target, Type.Rocky), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Target, Type.Narrow), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Target, Type.Wet), new Step.Tool[]{});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Target, Type.Source), new Step.Tool[]{Step.Tool.Torch});
        ALLOWABLE_TOOLS.put(new Pair<>(Type.Target, Type.Target), new Step.Tool[]{Step.Tool.Torch});
    }

    private Step.Tool[] allowableTools(Point here, Point there) {
        Type current = types[here.getY()][here.getX()];
        Type next = types[there.getY()][there.getX()];

        return ALLOWABLE_TOOLS.get(new Pair<>(current, next));
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
