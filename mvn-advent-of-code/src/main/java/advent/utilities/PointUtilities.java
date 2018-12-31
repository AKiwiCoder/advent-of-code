package advent.utilities;

import advent.common.Point;

public interface PointUtilities {
    static int calculateManhattenDistance(Point lhs, Point rhs) {
        return Math.abs(lhs.getX() - rhs.getX()) + Math.abs(lhs.getY() - rhs.getY());
    }

    static Point north(Point point) {
        return new Point(point.getX(), point.getY() - 1);
    }

    static Point south(Point point) {
        return new Point(point.getX(), point.getY() + 1);
    }

    static Point west(Point point) {
        return new Point(point.getX() - 1, point.getY());
    }

    static Point east(Point point) {
        return new Point(point.getX() + 1, point.getY());
    }
}
