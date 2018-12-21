package advent.common;

import java.util.Objects;

public final class IndexedPoint extends Point {
    private final int id;

    public IndexedPoint(int id, int x, int y) {
        super(x,y);
    this.id =id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IndexedPoint that = (IndexedPoint) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
