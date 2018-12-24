package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

public class MarbleMania implements DailyProblem<Long, Long> {
    private final long normalScore;
    private final long hundredScore;

    static class Node {
        long marble;
        Node clockwise;
        Node counter;
    }

    public MarbleMania(String name) {
        String line = FileUtilities.readLines(name, Parsers::ToString).get(0);
        String[] bits = line.trim().split("\\s");

        int players = Integer.parseInt(bits[0]);
        long score = Long.parseLong(bits[6]);

        this.normalScore = calculate(players, score);
        this.hundredScore = calculate(players, score * 100);
    }

    public static Node insert(Node current, long marble) {
        Node before = current.clockwise;
        Node after = before.clockwise;

        Node newNode = new Node();
        newNode.marble = marble;

        newNode.clockwise = before.clockwise;
        newNode.counter = after.counter;

        before.clockwise = newNode;
        after.counter = newNode;

        return newNode;
    }

    public static Node remove(Node current) {
        Node t = current.clockwise;
        current.counter.clockwise = current.clockwise;
        current.clockwise.counter = current.counter;
        return t;
    }

    private static long calculate(int players, long maxMarblePoint) {
        long[] marblesScored = new long[players];

        Node board = new Node();
        board.marble = 0;
        board.clockwise = board;
        board.counter = board;

        int currentMarble = 1;

        Node currentMarbleNode = board;
        int currentPlayer = 0;
        while (currentMarble <= maxMarblePoint) {
            long m = currentMarble++;//marbles.pop();

            if (m % 23 != 0) {
                currentMarbleNode = insert(currentMarbleNode, m);
            } else {
                marblesScored[currentPlayer] += m;

                Node seventh = currentMarbleNode;
                for (long i = 0; i != 7; i++) {
                    seventh = seventh.counter;
                }

                currentMarbleNode = seventh.clockwise;

                remove(seventh);

                marblesScored[currentPlayer] += seventh.marble;
            }

            currentPlayer++;
            currentPlayer = currentPlayer % players;
        }

        long score = -1;
        for (int p = 0; p != players; p++) {
            long th = marblesScored[p];
            if (th > score) {
                score = th;
            }
        }
        return score;
    }

    @Override
    public Long getPart1Answer() {
        return normalScore;
    }

    @Override
    public Long getPart2Answer() {
        return hundredScore;
    }
}
