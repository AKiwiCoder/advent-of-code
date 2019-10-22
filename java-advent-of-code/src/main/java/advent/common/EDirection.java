package advent.common;

public enum EDirection {
    North, South, East, West;

    public static EDirection turn(EDirection orig, ETurn turn) {
        switch (turn) {
            case Left:
                switch (orig) {
                    case North:
                        return West;
                    case South:
                        return East;
                    case East:
                        return North;
                    case West:
                        return South;
                }
                break;
            case Straight:
                return orig;
            case Around:
                switch (orig) {
                    case North:
                        return South;
                    case South:
                        return North;
                    case East:
                        return West;
                    case West:
                        return East;
                }
                break;
            case Right:
                switch (orig) {
                    case North:
                        return East;
                    case South:
                        return West;
                    case East:
                        return South;
                    case West:
                        return North;
                }
                break;
        }
        throw new IllegalArgumentException(orig + " " + turn + " huh?");
    }
}
