package advent.utilities;

import advent.common.Pair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static advent.utilities.StringUtilities.bytesToHex;

public class MessageDigestUtilities {
    private static final String ZEROS = "0000000000000000000000000000000000000000000000000000000";

    private MessageDigest digest = MessageDigest.getInstance("MD5");

    public MessageDigestUtilities() throws NoSuchAlgorithmException {
    }

    public String md5hashString(String text) {
        return bytesToHex(digest.digest(text.getBytes()));
    }

    public static boolean doesItStartWithXZeros(String hash, String zeros) {
        return hash.startsWith(zeros);
    }

    public Pair<String,Integer> findNextHashWithXZeros(String key, int start, int zeroCount) {
        int index = start;
        String zeros = ZEROS.substring(0, zeroCount);
        String hash = md5hashString(String.format("%s%d", key, ++index));
        while (!doesItStartWithXZeros(hash, zeros)) {
            hash = md5hashString(String.format("%s%d", key, ++index));
        }
        return new Pair<>(hash,index);
    }
}
