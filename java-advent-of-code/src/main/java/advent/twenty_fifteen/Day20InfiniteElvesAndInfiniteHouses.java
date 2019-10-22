package advent.twenty_fifteen;

import advent.common.DailyProblem;

public class Day20InfiniteElvesAndInfiniteHouses implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day20InfiniteElvesAndInfiniteHouses(int target) {
        int MAX_HOUSE = 1000000;

        this.part1Answer = calculatePartOne(target, MAX_HOUSE);
        this.part2Answer = calculatePartTwo(target, MAX_HOUSE);
    }


    private static int calculatePartOne(int target, int houseCount) {
        int[] houses = new int[houseCount];
        for (int elf = 1; elf != houses.length; elf++) {
            int hIndex = elf;
            while (hIndex < houses.length) {
                houses[hIndex] += elf * 10;
                hIndex += elf;
            }
        }
        return findHouseIndex(target, houses);
    }

    private static int calculatePartTwo(int target, int houseCount) {
        int[] houses = new int[houseCount];
        int[] elfDrops = new int[houseCount];
        for (int elf = 1; elf != houses.length; elf++) {
            int hIndex = elf;
            while (hIndex < houses.length && elfDrops[elf] < 50) {
                houses[hIndex] += elf * 11;
                hIndex += elf;
                elfDrops[elf]++;
            }
        }
        return findHouseIndex(target, houses);
    }

    private static int findHouseIndex(int target, int[] houses) {
        for (int i = 1; i != houses.length; i++) {
            if (houses[i] > target) {
                return i;
            }
        }
        return -1;
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
