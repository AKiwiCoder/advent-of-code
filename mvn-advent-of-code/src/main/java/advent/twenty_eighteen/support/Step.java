package advent.twenty_eighteen.support;

import advent.common.Point;

import java.util.Objects;

public final class Step {
    public enum Tool { Torch, ClimbingGear, Neither};

    private final Point point;
    private final long cost;
    private final Tool tool;

    public Step(Point point, long cost, Tool tool) {
        this.point = point;
        this.cost = cost;
        this.tool = tool;
    }

    public Point getPoint() {
        return point;
    }

    public long getCost() {
        return cost;
    }

    public Tool getTool() {
        return tool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return cost == step.cost &&
                tool == step.tool &&
                Objects.equals(point, step.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, cost, tool);
    }

    @Override
    public String toString() {
        return "Step{" +
                "point=" + point +
                ", cost=" + cost +
                ", tool=" + tool +
                '}';
    }
}
