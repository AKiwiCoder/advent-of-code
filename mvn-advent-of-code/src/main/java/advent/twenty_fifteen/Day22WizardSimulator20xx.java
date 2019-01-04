package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day22WizardSimulator20xx implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    static class Spell {
        final int cost;

        Spell(int cost) {
            this.cost = cost;
        }
    }

    static class Boss {
        private final int hitpoints;
        private final int damage;

        public Boss(int hitpoints, int damage) {
            this.hitpoints = hitpoints;
            this.damage = damage;
        }

        public Boss hurt(int loss) {
            return new Boss(hitpoints - loss, damage);
        }

        @Override
        public String toString() {
            return "Boss(" + hitpoints + " " + damage + ")";
        }
    }

    static class Player {
        private final int hitpoints;
        private final int mana;
        private final int spent;

        public Player(int hitpoints, int mana, int spent) {
            this.hitpoints = hitpoints;
            this.mana = mana;
            this.spent = spent;
        }

        public Player addMana(int increase) {
            return new Player(hitpoints, mana + increase, spent);
        }

        public Player hurt(int loss) {
            return new Player(hitpoints - loss, mana, spent);
        }

        public Player cast(int loss) {
            return new Player(hitpoints, mana - loss, spent + loss);
        }

        public Player heal(int gain) {
            return new Player(hitpoints + gain, mana, spent);
        }

        @Override
        public String toString() {
            return "Player(" + hitpoints + " " + mana + " " + spent + ")";
        }
    }

    public Day22WizardSimulator20xx(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        int bossHitpoints = 0;
        int bossDamage = 0;
        for (String line : lines) {
            if (line.startsWith("Hit Points:")) {
                bossHitpoints = Integer.parseInt(line.substring(11).trim());
            }
            if (line.startsWith("Damage:")) {
                bossDamage = Integer.parseInt(line.substring(7).trim());
            }
        }

        Boss boss = new Boss(bossHitpoints, bossDamage);
        Player player = new Player(50, 500, 0);

        this.part1Answer = combat( player, boss, false).stream().min(Comparator.naturalOrder()).get();
        this.part2Answer =  combat( player, boss, true).stream().min(Comparator.naturalOrder()).get();
    }

    static class State {
        boolean bossesTurn;
        Player player;
        Boss boss;
        int shield;
        int poison;
        int recharge;
    }

    private Set<Integer> combat(Player initialPlayer, Boss initialBoss, boolean hardMode) {
        Set<Integer> result = new HashSet<>();

        Stack<State> states = new Stack<>();
        State start = new State();
        start.bossesTurn = false;
        start.player = initialPlayer;
        start.boss = initialBoss;
        states.push(start);

        while (!states.isEmpty()) {
            State current = states.pop();

            if (result.size() > 0) {
                if (current.player.spent > result.stream().min(Comparator.naturalOrder()).get()) {
                    continue;
                }
            }

            Boss boss = current.boss;
            Player player = current.player;
            if (!current.bossesTurn) {
                // Players Turn
                if (hardMode)
                    player = player.hurt(1);

                if (current.shield > 0) {
                    current.shield--;
                }
                if (current.poison > 0) {
                    current.poison--;
                    boss = boss.hurt(3);
                }
                if (current.recharge > 0) {
                    current.recharge--;
                    player = player.addMana(101);
                }

                if (player.hitpoints <= 0) {
                    continue;
                }

                if (boss.hitpoints <= 0) {
                    result.add(player.spent);
                    continue;
                }

                if (player.mana < 53) {
                    // Player Losses
                    continue;
                }

                // Magic Missile costs 53 mana. It instantly does 4 damage.
                if (player.mana >= 53) {
                    State newState = new State();
                    newState.bossesTurn = true;
                    newState.player = player.cast(53);
                    newState.boss = boss.hurt(4);
                    newState.shield = current.shield;
                    newState.poison = current.poison;
                    newState.recharge = current.recharge;
                    states.push(newState);
                }
                // Drain costs 73 mana. It instantly does 2 damage and heals you for 2 hit points.
                if (player.mana >= 73) {
                    State newState = new State();
                    newState.bossesTurn = true;
                    newState.player = player.cast(73).heal(2);
                    newState.boss = boss.hurt(2);
                    newState.shield = current.shield;
                    newState.poison = current.poison;
                    newState.recharge = current.recharge;
                    states.push(newState);
                }
                // Shield costs 113 mana. It starts an effect that lasts for 6 turns. While it is active, your armor is increased by 7.
                if (player.mana >= 113 && current.shield == 0) {
                    State newState = new State();
                    newState.bossesTurn = true;
                    newState.player = player.cast(113);
                    newState.boss = boss;
                    newState.shield = 6;
                    newState.poison = current.poison;
                    newState.recharge = current.recharge;
                    states.push(newState);
                }
                // Poison costs 173 mana. It starts an effect that lasts for 6 turns. At the start of each turn while it is active, it deals the boss 3 damage.
                if (player.mana >= 173 && current.poison == 0) {
                    State newState = new State();
                    newState.bossesTurn = true;
                    newState.player = player.cast(173);
                    newState.boss = boss;
                    newState.shield = current.shield;
                    newState.poison = 6;
                    newState.recharge = current.recharge;
                    states.push(newState);
                }
                // Recharge costs 229 mana. It starts an effect that lasts for 5 turns. At the start of each turn while it is active, it gives you 101 new mana.
                if (player.mana >= 229 && current.recharge == 0) {
                    State newState = new State();
                    newState.bossesTurn = true;
                    newState.player = player.cast(229);
                    newState.boss = boss;
                    newState.shield = current.shield;
                    newState.poison = current.poison;
                    newState.recharge = 5;
                    states.push(newState);
                }
            } else {
                boolean shieldActive = false;
                // Boss Turn
                if (current.shield > 0) {
                    current.shield--;
                    shieldActive = true;
                }
                if (current.poison > 0) {
                    current.poison--;
                    boss = boss.hurt(3);
                }
                if (current.recharge > 0) {
                    current.recharge--;
                    player = player.addMana(101);
                }

                player = player.hurt(Math.max(1, boss.damage - (shieldActive ? 7 : 0)));

                if (boss.hitpoints <= 0) {
                    result.add(player.spent);
                    continue;
                }

                if (player.hitpoints <= 0) {
                    continue;
                }

                State newState = new State();
                newState.bossesTurn = false;
                newState.player = player;
                newState.boss = boss;
                newState.shield = current.shield;
                newState.poison = current.poison;
                newState.recharge = current.recharge;
                states.push(newState);
            }
        }

        return result;
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
s