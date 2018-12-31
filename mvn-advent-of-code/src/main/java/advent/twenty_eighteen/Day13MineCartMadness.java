package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.Cart;
import advent.twenty_eighteen.support.EDirection;
import advent.twenty_eighteen.support.ETurn;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Day13MineCartMadness implements DailyProblem<String, String> {
    private static Comparator<Cart> compareCarts = (o1, o2) -> {
        if (o1.getRow() == o2.getRow()) {
            return Integer.compare(o1.getCol(), o2.getCol());
        }
        return Integer.compare(o1.getRow(), o2.getRow());
    };

    private final String part1Answer;
    private final String part2Answer;

    public Day13MineCartMadness(String name) {
        List<String> lines = FileUtilities.readLines(name, Parsers::ToStringNoTrim);

        char[][] map = new char[lines.size()][];
        for (int i = 0; i != lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        List<Cart> carts = new LinkedList<>();

        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                if (map[row][col] == '^') {
                    carts.add(new Cart(row, col, EDirection.North, ETurn.Right));
                    map[row][col] = '|';
                }
                if (map[row][col] == '>') {
                    carts.add(new Cart(row, col, EDirection.East, ETurn.Right));
                    map[row][col] = '-';
                }
                if (map[row][col] == '<') {
                    carts.add(new Cart(row, col, EDirection.West, ETurn.Right));
                    map[row][col] = '-';
                }
                if (map[row][col] == 'v') {
                    carts.add(new Cart(row, col, EDirection.South, ETurn.Right));
                    map[row][col] = '|';
                }
            }
        }

        Cart crashes[] = new Cart[2];
        findCrash(map, new LinkedList<>(carts), crashes);
        this.part1Answer = +crashes[0].getCol() + "," + crashes[0].getRow();
        this.part2Answer = crashes[1].getCol() + "," + crashes[1].getRow();
    }

    private static void findCrash(char[][] map, List<Cart> carts, Cart[] crashes) {
        List<Cart> dead = new LinkedList<>();
        while (true) {
            carts.sort(compareCarts);

            for (Cart cart : carts) {
                switch (cart.getDirection()) {
                    case North:
                        cart.setRow(cart.getRow() - 1);
                        break;
                    case South:
                        cart.setRow(cart.getRow() + 1);
                        break;
                    case East:
                        cart.setCol(cart.getCol() + 1);
                        break;
                    case West:
                        cart.setCol(cart.getCol() - 1);
                        break;
                }

                char m = map[cart.getRow()][cart.getCol()];
                switch (m) {
                    case '/':
                        switch (cart.getDirection()) {
                            case North:
                                cart.setDirection(EDirection.East);
                                break;
                            case South:
                                cart.setDirection(EDirection.West);
                                break;
                            case East:
                                cart.setDirection(EDirection.North);
                                break;
                            case West:
                                cart.setDirection(EDirection.South);
                                break;
                        }
                        break;
                    case '\\':
                        switch (cart.getDirection()) {
                            case North:
                                cart.setDirection(EDirection.West);
                                break;
                            case South:
                                cart.setDirection(EDirection.East);
                                break;
                            case East:
                                cart.setDirection(EDirection.South);
                                break;
                            case West:
                                cart.setDirection(EDirection.North);
                                break;
                        }
                        break;
                    case '-':
                        switch (cart.getDirection()) {
                            case North:
                            case South:
                                throw new IllegalStateException("Cart off track " + cart + " '" + m + "'");
                            case East:
                            case West:
                                break;
                        }
                        break;
                    case '|':
                        switch (cart.getDirection()) {
                            case North:
                            case South:
                                break;
                            case East:
                            case West:
                                throw new IllegalStateException("Cart off track " + cart + " '" + m + "'");
                        }
                        break;
                    case '+':
                        switch (cart.getLastTurned()) {
                            case Left:
                                cart.setLastTurned(ETurn.Straight);
                                break;
                            case Straight:
                                cart.setLastTurned(ETurn.Right);
                                cart.setDirection(EDirection.turn(cart.getDirection(), ETurn.Right));
                                break;
                            case Right:
                                cart.setLastTurned(ETurn.Left);
                                cart.setDirection(EDirection.turn(cart.getDirection(), ETurn.Left));
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
                if (c.getRow() == cart.getRow() && c.getCol() == cart.getCol()) {
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
