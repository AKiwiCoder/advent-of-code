package advent.twenty_fifteen;

import advent.common.DailyProblem;


public class Day11CorporatePolicy implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day11CorporatePolicy(String input) {
        String newPassword = incrementPassword(input);
        while (!validPassword(newPassword)) {
            newPassword = incrementPassword(newPassword);
        }
        this.part1Answer = newPassword;

        newPassword = incrementPassword(newPassword);
        while (!validPassword(newPassword)) {
            newPassword = incrementPassword(newPassword);
        }
        this.part2Answer = newPassword;
    }

    /*
        Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
        Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
        Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
    */
    public static boolean validPassword(String password) {
        return threeLetterSequence(password) && !bannedCharacters(password) && twoPairs(password);
    }

    private static boolean threeLetterSequence(String password) {
        for (int i = 2; i != password.length(); i++) {
            int a = password.charAt(i - 2);
            int b = password.charAt(i - 1);
            int c = password.charAt(i);
            if ((b - a) == 1 && (c - b) == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean bannedCharacters(String password) {
        return password.contains("i") || password.contains("o") || password.contains("l");
    }

    private static boolean twoPairs(String password) {
        int pairCount = 0;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i - 1) == password.charAt(i)) {
                pairCount++;
                i++;
            }
        }
        return pairCount >= 2;
    }

    public static String incrementPassword(String initial) {
        String working = initial;
        for (int i = working.length() - 1; i >= 0; i--) {
            boolean carry = false;

            int c = (int) working.charAt(i) + 1;


            if (c > (int) 'z') {
                c = 'a';
                carry = true;
            }

            working = working.substring(0, i) + (char) c + working.substring(i + 1);

            if (!carry) {
                break;
            }
        }
        return working;
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
