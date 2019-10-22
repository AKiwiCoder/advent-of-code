package advent.common;

import java.util.Objects;

public final class Rectangle {
    private final int top;
    private final int left;
    private final int bottom;
    private final int right;

    public Rectangle(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return top == rectangle.top &&
                left == rectangle.left &&
                bottom == rectangle.bottom &&
                right == rectangle.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, left, bottom, right);
    }
}
