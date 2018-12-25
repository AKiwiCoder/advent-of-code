package advent.twenty_eighteen.support;

import java.util.Comparator;
import java.util.Objects;

public class Location {
    private final int row;
    private final int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row &&
                col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public boolean adjacent(Location loc) {
        if (loc.col == col) {
            return Math.abs(loc.row - row) == 1;
        }
        if (loc.row == row) {
            return Math.abs(loc.col - col) == 1;
        }
        return false;
    }

    public static final Comparator<Location> LOCATION_SORTER = (o1, o2) -> {
        if (o1.row == o2.row) {
            return Integer.compare(o1.col, o2.col);
        }
        return Integer.compare(o1.row, o2.row);
    };
}
