package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.MessageDigestUtilities;

import java.security.NoSuchAlgorithmException;

public class Day05HowAboutANiceGameOfChess implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day05HowAboutANiceGameOfChess(String key) throws NoSuchAlgorithmException {
        MessageDigestUtilities mdUtils = new MessageDigestUtilities();

        String password = "";
        char[] password2 = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        int count = 0;
        Pair<String, Integer> current = new Pair<>("", 9999);
        while (password.length() != 8 || !isValidPassword(password2)) {
            current = mdUtils.findNextHashWithXZeros(key, current.getSecond(), 5);

            // Part One
            if (password.length() != 8) {
                password += current.getFirst().charAt(5);
            }

            try {
                int index = Integer.parseInt("" + current.getFirst().charAt(5));
                char character = current.getFirst().charAt(6);
                if (index >= 0 && index < password2.length && password2[index] == ' ') {
                    password2[index] = character;
                }
            } catch (NumberFormatException e) {
            }
        }

        String temp = "";
        for (int i = 0; i != password2.length; i++) {
            temp += password2[i];
        }

        this.part1Answer = password.toLowerCase();
        this.part2Answer = temp.toLowerCase();
    }

    private boolean isValidPassword(char[] password) {
        int count = 0;
        for (int i = 0; i != password.length; i++) {
            if (Character.isLetterOrDigit(password[i])) {
                count++;
            }
        }
        return count == password.length;
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
