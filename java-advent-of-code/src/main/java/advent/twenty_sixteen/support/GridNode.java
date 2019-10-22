package advent.twenty_sixteen.support;

import java.util.Objects;

public final class GridNode {
    private final String name;
    final int row;
    final int col;
    final int size;
    final int avail;
    final int used;

    public GridNode(String name, int row, int col, int size, int avail, int used) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.size = size;
        this.avail = avail;
        this.used = used;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSize() {
        return size;
    }

    public int getAvail() {
        return avail;
    }

    public int getUsed() {
        return used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridNode gridNode = (GridNode) o;
        return row == gridNode.row &&
                col == gridNode.col &&
                size == gridNode.size &&
                avail == gridNode.avail &&
                used == gridNode.used &&
                Objects.equals(name, gridNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, row, col, size, avail, used);
    }

    @Override
    public String toString() {
        return "GridNode{" +
                "name='" + name + '\'' +
                ", row=" + row +
                ", col=" + col +
                ", size=" + size +
                ", avail=" + avail +
                ", used=" + used +
                '}';
    }

    // A viable pair is any two nodes (A,B), regardless of whether they are directly connected, such that:
    //
    //    Node A is not empty (its Used is not zero).
    //    Nodes A and B are not the same node.
    //    The data on node A (its Used) would fit on node B (its Avail).
    public static boolean isViablePair(GridNode a, GridNode b) {
        if (!a.isEmpty()) {
            if (!a.equals(b)) {
                if (a.getUsed() <= b.getAvail()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return used == 0;
    }

    public boolean isFull() {
        return avail == 0;
    }
}