package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.twenty_fifteen.support.Ingredient;
import advent.utilities.FileUtilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15ScienceForHungryPeople implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day15ScienceForHungryPeople(String filename) {
        List<Ingredient> ingredients = FileUtilities.readLines(filename, Ingredient::PARSE);
        List<List<Integer>> combinations = populate(ingredients.size(), 100);

        Map<List<Integer>, Integer> cookieMakeup = new HashMap<>();
        Map<List<Integer>, Integer> cookieCalorie = new HashMap<>();

        combinations.forEach(combination -> {
            int capacity = 0;
            int durability = 0;
            int flavor = 0;
            int texture = 0;
            int calories = 0;

            for (int i = 0; i != ingredients.size(); i++) {
                int count = combination.get(i);
                Ingredient ingredient = ingredients.get(i);

                capacity += ingredient.getCapacity() * count;
                durability += ingredient.getDurability() * count;
                flavor += ingredient.getFlavor() * count;
                texture += ingredient.getTexture() * count;
                calories += ingredient.getCalories() * count;
            }

            capacity = (capacity > 0) ? capacity : 0;
            durability = (durability > 0) ? durability : 0;
            flavor = (flavor > 0) ? flavor : 0;
            texture = (texture > 0) ? texture : 0;

            cookieMakeup.put(combination, capacity * durability * flavor * texture);
            cookieCalorie.put(combination, calories);
        });

        List<List<Integer>> validCookies = cookieCalorie.entrySet().stream().filter(e -> e.getValue() == 500).map(e -> e.getKey()).collect(Collectors.toList());

        this.part1Answer = cookieMakeup.values().stream().max(Integer::compareTo).get();
        this.part2Answer = validCookies.stream().map(key -> cookieMakeup.get(key)).max(Integer::compareTo).get();
    }

    private List<List<Integer>> populate(int columns, int target) {
        List<List<Integer>> result = new LinkedList<>();

        int[] n = new int[columns];
        n[0] = 0;

        boolean overflow = false;
        while (!overflow) {
            int total = 0;
            for (int c = 0; c != columns; c++) {
                total += n[c];
            }
            if (total == target) {
                List<Integer> found = new LinkedList<>();
                for (int c = 0; c != columns; c++) {
                    found.add(n[c]);
                }
                result.add(found);
            }
            overflow = increment(n, 0, target);
        }
        return result;
    }

    private boolean increment(int[] data, int column, int max) {
        if (column >= data.length) {
            return true;
        }

        data[column]++;
        if (data[column] > max) {
            data[column] = 0;
            return increment(data, column + 1, max);
        }
        return false;
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
