package advent.twenty_fifteen;

import advent.common.DailyProblem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04TheIdealStockingStuffer implements DailyProblem<Integer, Integer> {

    private final int part1Answer;
    private final int part2Answer;

    private MessageDigest digest = MessageDigest.getInstance("MD5");

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String md5hashString(String text) {
        return bytesToHex(digest.digest(text.getBytes()));
    }

    private boolean doesItStartWithXZeros(String hash, String zeros) {
        return hash.startsWith(zeros);
    }

    public Day04TheIdealStockingStuffer(String key) throws NoSuchAlgorithmException {
        int part1Count = 9999;
        while (!doesItStartWithXZeros(md5hashString(String.format("%s%d", key, ++part1Count)), "00000")) ;

        int part2Count = 9999;
        while (!doesItStartWithXZeros(md5hashString(String.format("%s%d", key, ++part2Count)), "000000")) ;

        this.part1Answer = part1Count;
        this.part2Answer = part2Count;
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
