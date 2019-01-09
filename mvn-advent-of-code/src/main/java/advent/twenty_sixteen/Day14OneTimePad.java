package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.MessageDigestUtilities;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14OneTimePad implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private String stretchedHash(MessageDigestUtilities md5, int index, String key) {
        String hash = md5.md5hashString(String.format("%s%d", key, index));
        for (int i = 0; i != 2016; i++) {
            hash = md5.md5hashString(hash);
        }
        return hash;
    }

    public Day14OneTimePad(String key) throws NoSuchAlgorithmException {
        MessageDigestUtilities md5 = new MessageDigestUtilities();

        Set<Integer> keys1 = findOneTimePad(key, (i) -> md5.md5hashString(String.format("%s%d", key, i)));
        this.part1Answer = keys1.stream().sorted().collect(Collectors.toList()).get(63);

        Set<Integer> keys2 = findOneTimePad(key, (i) -> stretchedHash(md5, i, key));
        this.part2Answer = keys2.stream().sorted().collect(Collectors.toList()).get(63);
    }

    private Set<Integer> findOneTimePad(String key, Function<Integer, String> generator) {
        Set<Integer> keys = new HashSet<>();
        Map<String, Set<Integer>> triplesFound = new HashMap<>();
        int i = 0;
        int endIndex = Integer.MAX_VALUE;
        while (!(keys.size() > 64 && i > endIndex)) {
            String hash = generator.apply(i);

            String triple = locateTriple(hash);
            if (triple != null) {
                Set<Integer> idsSoFar = triplesFound.getOrDefault(triple, new HashSet<>());
                idsSoFar.add(i);
                triplesFound.put(triple, idsSoFar);
            }

            for (String fives : locateFives(hash)) {
                Set<Integer> ids = triplesFound.get(fives.substring(0, 3));
                if (ids != null) {
                    for (Integer id : ids) {
                        if (id >= i - 1000 && id != i) {
                            keys.add(id);
                        }
                    }
                }
            }

            if (keys.size() > 64 && endIndex == Integer.MAX_VALUE) {
                endIndex = i + 1001;
            }

            i++;
        }
        return keys;
    }

    private Set<String> locateFives(String hash) {
        Set<String> results = new HashSet<>();
        for (int i = 0; i != hash.length() - 4; i++) {
            char a = hash.charAt(i);
            char b = hash.charAt(i + 1);
            char c = hash.charAt(i + 2);
            char d = hash.charAt(i + 3);
            char e = hash.charAt(i + 4);
            if (a == b && b == c && c == d && d == e) {
                results.add(hash.substring(i, i + 5));
            }
        }
        return results;
    }

    private String locateTriple(String hash) {
        for (int i = 0; i != hash.length() - 2; i++) {
            char a = hash.charAt(i);
            char b = hash.charAt(i + 1);
            char c = hash.charAt(i + 2);
            if (a == b && b == c) {
                return hash.substring(i, i + 3);
            }
        }
        return null;
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
