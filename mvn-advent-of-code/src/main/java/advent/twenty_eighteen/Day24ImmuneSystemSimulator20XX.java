package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day24ImmuneSystemSimulator20XX implements DailyProblem<Integer, Integer> {
    private static final Comparator<Group> TARGET_SELECTION_SORT = (o1, o2) -> {
        int c = Integer.compare(o2.effectivePower(), o1.effectivePower());
        if (c == 0) {
            return Integer.compare(o2.initiative, o1.initiative);
        }
        return c;
    };

    private final LinkedList<Group> attackers;
    private final LinkedList<Group> defenders;

    enum EDamage {
        cold, fire, slashing, bludgeoning, radiation
    }

    static class Group {
        int id;
        boolean defender;
        int count;
        int hp;
        final int initiative;
        final int attack;
        final EDamage damage;
        final List<EDamage> weakness;
        final List<EDamage> immunities;

        public Group(boolean defender, int id, int count, int hp, int initiative, int attack, EDamage damage, List<EDamage> weakness, List<EDamage> immunities) {
            this.defender = defender;
            this.id = id;
            this.count = count;
            this.hp = hp;
            this.initiative = initiative;
            this.attack = attack;
            this.damage = damage;
            this.weakness = weakness;
            this.immunities = immunities;
        }

        public int effectivePower() {
            return count * attack;
        }

        @Override
        public String toString() {
            return "Group{" +
                    " name=" + desc() +
                    ", count=" + count +
                    ", hp=" + hp +
                    ", initiative=" + initiative +
                    ", attack=" + attack +
                    ", damage=" + damage +
                    ", weakness=" + weakness +
                    ", immunities=" + immunities +
                    ", effectivePower= " + effectivePower() +
                    '}';
        }

        public String desc() {
            if (defender) {
                return "Immune Group " + id;
            }
            return "Infection Group " + id;
        }

        public Group cloneWithAttackBoost(int attackBoost) {
            return new Group(defender, id, count, hp, initiative, attack + attackBoost, damage, weakness, immunities);
        }
    }

    public Day24ImmuneSystemSimulator20XX(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::ToString);

        this.defenders = new LinkedList<>();
        this.attackers = new LinkedList<>();

        boolean defense = true;
        int id = 1;
        for (String line : lines) {
            if (line.trim().equals("Immune System:")) {
                defense = true;
                id = 1;
                continue;
            }
            if (line.trim().equals("Infection:")) {
                defense = false;
                id = 1;
                continue;
            }
            if (line.trim().length() == 0) {
                continue;
            }

            int count = Integer.parseInt(line.substring(0, line.indexOf(" ")).trim());
            int hp = Integer.parseInt(line.substring(line.indexOf("with ") + 5, line.indexOf("hit")).trim());
            int initiative = Integer.parseInt(line.substring(line.indexOf("initiative ") + 11).trim());

            String t = line.substring(line.indexOf("that does") + 10);
            int attack = Integer.parseInt(t.substring(0, t.indexOf(' ')));
            EDamage damage = EDamage.valueOf(t.substring(t.indexOf(' '), t.indexOf("damage")).trim());

            List<EDamage> weakness = new ArrayList<>();
            List<EDamage> immunities = new ArrayList<>();

            if (line.indexOf('(') > 0) {
                String traits = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
                for (String trait : traits.split(";")) {
                    if (trait.trim().startsWith("weak to ")) {
                        for (String name : trait.trim().substring(8).split(",")) {
                            weakness.add(EDamage.valueOf(name.trim()));
                        }
                    } else if (trait.trim().startsWith("immune to ")) {
                        for (String name : trait.trim().substring(10).split(",")) {
                            immunities.add(EDamage.valueOf(name.trim()));
                        }
                    }
                }
            }

            if (defense) {
                Group group = new Group(defense, id++, count, hp, initiative, attack, damage, weakness, immunities);
                defenders.add(group);
            } else {
                Group group = new Group(defense, id++, count, hp, initiative, attack, damage, weakness, immunities);
                attackers.add(group);
            }
        }
    }

    private List<Group> clone(List<Group> groups, int attackBoost) {
        List<Group> results = new ArrayList<>(groups.size());
        for (Group g : groups) {
            results.add(g.cloneWithAttackBoost(attackBoost));
        }
        return results;
    }

    public void run(List<Group> attackers, List<Group> defenders) {
        boolean someoneDiedThisRound = true;
        while (attackers.size() > 0 && defenders.size() > 0 && someoneDiedThisRound) {
            List<Group> groups = new LinkedList<>();
            groups.addAll(defenders);
            groups.addAll(attackers);
            groups.sort(TARGET_SELECTION_SORT);

            Map<Group, Group> targets = new LinkedHashMap<>();

            for (Group unit : groups) {
                if (unit.defender) {
                    Group target = findTarget(unit, targets, attackers);
                    if (target != null) {
                        targets.put(unit, target);
                    }
                } else {
                    Group target = findTarget(unit, targets, defenders);
                    if (target != null) {
                        targets.put(unit, target);
                    }
                }
            }

            List<Group> attackOrder = new LinkedList<>(targets.keySet());
            attackOrder.sort((o1, o2) -> Integer.compare(o2.initiative, o1.initiative));

            if (targets.size() == 0) {
                break;
            }

            int totalKills = 0;
            for (Group attacker : attackOrder) {
                Group defender = targets.get(attacker);

                if (!((attackers.contains(defender) || attackers.contains(attacker)) && (defenders.contains(defender) || defenders.contains(attacker)))) {
                    continue;
                }

                if (defender != null) {
                    int damage = amountOfDamageDealt(attacker, defender);

                    int kills = damage / defender.hp;
                    if (kills > defender.count) {
                        kills = defender.count;
                    }
                    totalKills += kills;

                    defender.count = defender.count - kills;
                    if (defender.count <= 0) {
                        defenders.remove(defender);
                        attackers.remove(defender);
                    }
                }
                someoneDiedThisRound = totalKills > 0;
            }
        }
    }

    private static Group findTarget(Group me, Map<Group, Group> targetted, List<Group> possibilities) {
        possibilities.sort((o1, o2) -> {
            int c = Integer.compare(amountOfDamageDealt(me, o2), amountOfDamageDealt(me, o1));
            if (c == 0) {
                int c2 = Integer.compare(o2.effectivePower(), o1.effectivePower());
                if (c2 == 0) {
                    return Integer.compare(o2.initiative, o1.initiative);
                }
                return c2;
            }
            return c;
        });

        for (Group enemy : possibilities) {
            if (targetted.values().contains(enemy)) {
                continue;
            }
            if (amountOfDamageDealt(me, enemy) > 0) {
                return enemy;
            }
        }
        return null;
    }

    private static int amountOfDamageDealt(Group me, Group o2) {
        if (o2 == null) {
            return 0;
        }

        int base = me.effectivePower();
        if (o2.weakness.contains(me.damage)) {
            base = base * 2;
        }
        if (o2.immunities.contains(me.damage)) {
            base = base * 0;
        }

        return base;
    }

    @Override
    public Integer getPart1Answer() {
        List<Group> workingAttackers = clone(attackers, 0);
        List<Group> workingDefenders = clone(defenders, 0);
        run(workingAttackers, workingDefenders);
        int aCount = workingAttackers.stream().map(a -> a.count).mapToInt(Integer::intValue).sum();
        int dCount = workingDefenders.stream().map(d -> d.count).mapToInt(Integer::intValue).sum();
        return Math.max(aCount, dCount);
    }

    @Override
    public Integer getPart2Answer() {
        int result = 0;
        for (int boost = 0; boost != Integer.MAX_VALUE; boost++) {
            List<Group> workingAttackers = clone(attackers, 0);
            List<Group> workingDefenders = clone(defenders, boost);

            run(workingAttackers, workingDefenders);
            if (workingAttackers.size() == 0) {
                return workingDefenders.stream().map(a -> a.count).mapToInt(Integer::intValue).sum();
            }
        }
        return -1;
    }
}
