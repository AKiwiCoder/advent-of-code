package advent.twenty_eighteen;

import advent.common.DailyProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ChocolateCharts implements DailyProblem<String, String> {
    private final int part1Number;
    private final Integer[] part2Array;

    public ChocolateCharts(String part1, String part2) {
        part1Number = Integer.parseInt(part1);

        part2Array = new Integer[part2.length()];
        for (int i = 0; i != part2.length(); i++) {
            part2Array[i] = Integer.parseInt("" + part2.charAt(i));
        }
    }

    private void generateRecipeArray(List<Integer> current, int loopMax, BiFunction<List<Integer>, Boolean, Boolean> shouldFinish) {
        int elves[] = new int[] {0, 1};

        while (current.size() < loopMax) {
            int e0 = current.get(elves[0]);
            int e1 = current.get(elves[1]);

            int sum = e0 + e1;

            if (sum >= 10) {
                current.add(1);
            }
            current.add(sum % 10);

            int size = current.size();

            elves[0] = (elves[0] + e0 + 1) % size;
            elves[1] = (elves[1] + e1 + 1) % size;

            if (shouldFinish.apply(current, sum > 10)) {
                break;
            }
        }
    }

    @Override
    public String getPart1Answer() {
        List<Integer> current = new ArrayList<>(Arrays.asList(3, 7));

        generateRecipeArray(current, part1Number + 10,(list, flag) -> false);

        String result = "";
        for (int i = 0; i != 10; i++) {
            result += current.get(part1Number + i);
        }
        return result;
    }

    @Override
    public String getPart2Answer() {
        List<Integer> current = new ArrayList<>(Arrays.asList(3, 7));

        generateRecipeArray(current, Integer.MAX_VALUE,(list, flag) -> {
            int size = list.size();
            return ((size > 5 && check(current, 0)) || (flag && size > 6 && check(current, 1)));
        });

        if (check(current, 0)) {
            return "" + (current.size() - part2Array.length);
        }
        return "" + (current.size() - part2Array.length - 1);
    }

    private boolean check(List<Integer> current, int offset) {
        int length = current.size();
        for (int i = 0; i != part2Array.length; i++) {
            if (!part2Array[part2Array.length - i - 1].equals(current.get(length - 1 - i - offset)))
                return false;
        }
        return true;
    }
}
