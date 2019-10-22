package advent.common;

import java.util.Objects;

public final class Velocity {
    private final int dx;
    private final int dy;

    public Velocity(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return dx == velocity.dx &&
                dy == velocity.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }
}
