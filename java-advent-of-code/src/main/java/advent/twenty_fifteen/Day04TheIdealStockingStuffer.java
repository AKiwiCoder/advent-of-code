package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.MessageDigestUtilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04TheIdealStockingStuffer implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;

    public Day04TheIdealStockingStuffer(String key) throws NoSuchAlgorithmException {
        MessageDigestUtilities mdUtilities = new MessageDigestUtilities();

        this.part1Answer = mdUtilities.findNextHashWithXZeros(key, 9999, 5).getSecond();
        this.part2Answer = mdUtilities.findNextHashWithXZeros(key, 9999, 6).getSecond();
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
