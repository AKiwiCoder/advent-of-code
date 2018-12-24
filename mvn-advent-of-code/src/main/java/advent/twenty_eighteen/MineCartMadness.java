package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MineCartMadness implements DailyProblem<String, String> {
    private static Comparator<Cart> compareCarts = (o1, o2) -> {
        if (o1.row == o2.row) {
            return Integer.compare(o1.col, o2.col);
        }
        return Integer.compare(o1.row, o2.row);
    };

    private final String part1Answer;
    private final String part2Answer;

    enum Direction {
        North, South, East, West;

        public static Direction turn(Direction orig, Turn turn) {
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

    enum Turn {
        Left, Straight, Right
    }

    static class Cart {
        int row;
        int col;
        Direction direction;
        Turn lastTurned;

        @Override
        public String toString() {
            return "Cart{" +
                    "row=" + row +
                    ", col=" + col +
                    ", direction=" + direction +
                    ", lastTurned=" + lastTurned +
                    '}';
        }
    }


    public MineCartMadness(String name) {
        List<String> lines = FileUtilities.readLines(name, Parsers::ToStringNoTrim);

        char[][] map = new char[lines.size()][];
        for (int i = 0; i != lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        List<Cart> carts = new LinkedList<>();

        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                if (map[row][col] == '^') {
                    Cart c = new Cart();
                    c.row = row;
                    c.col = col;
                    c.direction = Direction.North;
                    c.lastTurned = Turn.Right;
                    carts.add(c);
                    map[row][col] = '|';
                }
                if (map[row][col] == '>') {
                    Cart c = new Cart();
                    c.row = row;
                    c.col = col;
                    c.direction = Direction.East;
                    c.lastTurned = Turn.Right;
                    carts.add(c);
                    map[row][col] = '-';
                }
                if (map[row][col] == '<') {
                    Cart c = new Cart();
                    c.row = row;
                    c.col = col;
                    c.direction = Direction.West;
                    c.lastTurned = Turn.Right;
                    carts.add(c);
                    map[row][col] = '-';
                }
                if (map[row][col] == 'v') {
                    Cart c = new Cart();
                    c.row = row;
                    c.col = col;
                    c.direction = Direction.South;
                    c.lastTurned = Turn.Right;
                    carts.add(c);
                    map[row][col] = '|';
                }
            }
        }

        for (int y = 0; y != map.length; y++) {
            for (int x = 0; x != map[y].length; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }

        Cart crashes[] = new Cart[2];
        findCrash(map, new LinkedList<>(carts), crashes);
        this.part1Answer = +crashes[0].col + "," + crashes[0].row;
        this.part2Answer = crashes[1].col + "," + crashes[1].row;
    }

    private static void findCrash(char[][] map, List<Cart> carts, Cart[] crashes) {
        List<Cart> dead = new LinkedList<>();
        while (true) {
            carts.sort(compareCarts);

            for (Cart cart : carts) {
                switch (cart.direction) {
                    case North:
                        cart.row--;
                        break;
                    case South:
                        cart.row++;
                        break;
                    case East:
                        cart.col++;
                        break;
                    case West:
                        cart.col--;
                        break;
                }

                char m = map[cart.row][cart.col];
                switch (m) {
                    case '/':
                        switch (cart.direction) {
                            case North:
                                cart.direction = Direction.East;
                                break;
                            case South:
                                cart.direction = Direction.West;
                                break;
                            case East:
                                cart.direction = Direction.North;
                                break;
                            case West:
                                cart.direction = Direction.South;
                                break;
                        }
                        break;
                    case '\\':
                        switch (cart.direction) {
                            case North:
                                cart.direction = Direction.West;
                                break;
                            case South:
                                cart.direction = Direction.East;
                                break;
                            case East:
                                cart.direction = Direction.South;
                                break;
                            case West:
                                cart.direction = Direction.North;
                                break;
                        }
                        break;
                    case '-':
                        switch (cart.direction) {
                            case North:
                            case South:
                                throw new IllegalStateException("Cart off track " + cart + " '" + m + "'");
                            case East:
                            case West:
                                break;
                        }
                        break;
                    case '|':
                        switch (cart.direction) {
                            case North:
                            case South:
                                break;
                            case East:
                            case West:
                                throw new IllegalStateException("Cart off track " + cart + " '" + m + "'");
                        }
                        break;
                    case '+':
                        switch (cart.lastTurned) {
                            case Left:
                                cart.lastTurned = Turn.Straight;
                                break;
                            case Straight:
                                cart.lastTurned = Turn.Right;
                                cart.direction = Direction.turn(cart.direction, Turn.Right);
                                break;
                            case Right:
                                cart.lastTurned = Turn.Left;
                                cart.direction = Direction.turn(cart.direction, Turn.Left);
                                break;
                        }
                        break;
                    default:
                        throw new IllegalStateException("No match: Cart off track " + cart + " '" + m + "'");
                }

                Cart bang = crashed(cart, dead, carts);
                if (bang != null) {
                    if (crashes[0] == null) {
                        crashes[0] = cart;
                    }
                    dead.add(cart);
                    dead.add(bang);
                }
            }

            for (Cart d : dead) {
                carts.remove(d);
            }

            dead.clear();

            if (carts.size() == 1) {
                crashes[1] = carts.get(0);
                break;
            }
        }
    }

    private static Cart crashed(Cart cart, List<Cart> dead, List<Cart> carts) {
        for (Cart c : carts) {
            if (c != cart) {
                if (c.row == cart.row && c.col == cart.col) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
