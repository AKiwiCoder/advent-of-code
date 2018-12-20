package advent.utilities;

import advent.common.Point;

public interface PointUtilities {
    static int calculateManhattenDistance(Point lhs, Point rhs) {
        return Math.abs(lhs.getX() - rhs.getX()) + Math.abs(lhs.getY() - rhs.getY());
    }
}
