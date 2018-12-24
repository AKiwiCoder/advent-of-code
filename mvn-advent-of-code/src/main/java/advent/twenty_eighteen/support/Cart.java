package advent.twenty_eighteen.support;

import java.util.Objects;

public class Cart {
    private int row;
    private int col;
    private EDirection direction;
    private ETurn lastTurned;

    public Cart(int row, int col, EDirection direction, ETurn lastTurned) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.lastTurned = lastTurned;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public EDirection getDirection() {
        return direction;
    }

    public void setDirection(EDirection direction) {
        this.direction = direction;
    }

    public ETurn getLastTurned() {
        return lastTurned;
    }

    public void setLastTurned(ETurn lastTurned) {
        this.lastTurned = lastTurned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return row == cart.row &&
                col == cart.col &&
                direction == cart.direction &&
                lastTurned == cart.lastTurned;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, direction, lastTurned);
    }
}
