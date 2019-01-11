package advent.twenty_sixteen.support;

import advent.common.Point;

import java.util.Objects;

public final class Step {

    private final Point point;
    private final int cost;

    public Step(Point point, int cost) {
        this.point = point;
        this.cost = cost;
    }

    public Point getPoint() {
        return point;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return cost == step.cost &&
                Objects.equals(point, step.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, cost);
    }

    @Override
    public String toString() {
        return "Step{" +
                "point=" + point +
                ", cost=" + cost +
                '}';
    }
}
