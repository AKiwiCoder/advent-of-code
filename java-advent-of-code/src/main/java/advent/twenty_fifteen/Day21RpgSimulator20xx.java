package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import com.google.common.collect.Sets;

import java.util.*;

public class Day21RpgSimulator20xx implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    static class Fighter {
        int hitpoints;
        int damage;
        int armor;

        public Fighter(int hitpoints, int damage, int armor) {
            this.hitpoints = hitpoints;
            this.damage = damage;
            this.armor = armor;
        }
    }

    static class Equipment {
        String name;
        int cost;
        int damage;
        int armor;

        public Equipment(String name, int cost, int damage, int armor) {
            this.name = name;
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static final Set<Equipment> WEAPONS = new HashSet<>(Arrays.asList( //
            new Equipment("Dagger", 8, 4, 0), //
            new Equipment("Shortsword", 10, 5, 0), //
            new Equipment("Warhammer", 25, 6, 0), //
            new Equipment("Longsword", 40, 7, 0), //
            new Equipment("Greataxe", 74, 8, 0)));

    private static final Set<Equipment> ARMORS = new HashSet<>(Arrays.asList( //
            new Equipment("Empty Armor", 0, 0, 0), //
            new Equipment("Leather", 13, 0, 1), //
            new Equipment("Chainmail", 31, 0, 2), //
            new Equipment("Splintmail", 53, 0, 3), //
            new Equipment("Bandedmail", 75, 0, 4), //
            new Equipment("Platemail", 102, 0, 5)));

    private static final Set<Equipment> LEFT_HAND_RINGS = new HashSet<>(Arrays.asList( //
            new Equipment("Empty Left Hand", 0, 0, 0), //
            new Equipment("Damage +1", 25, 1, 0), //
            new Equipment("Damage +2", 50, 2, 0), //
            new Equipment("Damage +3", 100, 3, 0), //
            new Equipment("Defense +1", 20, 0, 1), //
            new Equipment("Defense +2", 40, 0, 2), //
            new Equipment("Defense +3", 80, 0, 3)));

    private static final Set<Equipment> RIGHT_HAND_RINGS = new HashSet<>(Arrays.asList( //
            new Equipment("Empty Right Hand", 0, 0, 0), //
            new Equipment("Damage +1", 25, 1, 0), //
            new Equipment("Damage +2", 50, 2, 0), //
            new Equipment("Damage +3", 100, 3, 0), //
            new Equipment("Defense +1", 20, 0, 1), //
            new Equipment("Defense +2", 40, 0, 2), //
            new Equipment("Defense +3", 80, 0, 3)));

    public Day21RpgSimulator20xx(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        int bossHitpoints = 0;
        int bossDamage = 0;
        int bossArmor = 0;
        for (String line : lines) {
            if (line.startsWith("Hit Points:")) {
                bossHitpoints = Integer.parseInt(line.substring(11).trim());
            }
            if (line.startsWith("Damage:")) {
                bossDamage = Integer.parseInt(line.substring(7).trim());
            }
            if (line.startsWith("Armor:")) {
                bossArmor = Integer.parseInt(line.substring(6).trim());
            }
        }

        Map<List<Equipment>, Integer> winningEquipment = new HashMap<>();
        Map<List<Equipment>, Integer> loosingEquipment = new HashMap<>();
        Set<List<Equipment>> equipmentSets = Sets.cartesianProduct(Arrays.asList(WEAPONS, ARMORS, LEFT_HAND_RINGS, RIGHT_HAND_RINGS));

        for (List<Equipment> equipment : equipmentSets) {
            Fighter me = new Fighter(100, 0, 0);

            Set<String> names = new HashSet<>();
            int cost = 0;
            for (Equipment e : equipment) {
                me.armor += e.armor;
                me.damage += e.damage;
                cost += e.cost;
                names.add(e.name);
            }
            if (names.size() != 4) {
                // Detect duplicate rings
                continue;
            }

            Fighter boss = new Fighter(bossHitpoints, bossDamage, bossArmor);

            Fighter winner = executeFight(me, boss);

            if (winner == me) {
                winningEquipment.put(equipment, cost);
            } else {
                loosingEquipment.put(equipment, cost);
            }
        }

        this.part1Answer = winningEquipment.values().stream().min(Integer::compareTo).get();
        this.part2Answer = loosingEquipment.values().stream().max(Integer::compareTo).get();
    }

    private static Fighter executeFight(Fighter lhs, Fighter rhs) {
        Fighter attacker = lhs;
        Fighter defender = rhs;
        while (true) {
            int damageDealt = Math.max(1, attacker.damage - defender.armor);
            defender.hitpoints -= damageDealt;

            if (defender.hitpoints <= 0) {
                return attacker;
            }

            Fighter temp = attacker;
            attacker = defender;
            defender = temp;
        }
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
