package advent.twenty_eighteen.support;

import java.util.Comparator;
import java.util.List;

public class Unit {
    private final String id;
    private final char type;
    private final int attack;
    public Location loc;
    public int hp;

    public Unit(Location loc, char type, int hp, int attack) {
        this.id = "" + type + loc;
        this.loc = loc;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
    }

    public String getId() {
        return id;
    }

    public char getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public static Comparator<Unit> getUnitSorter() {
        return UNIT_SORTER;
    }

    public static final Comparator<Unit> UNIT_SORTER = (o1, o2) -> Location.LOCATION_SORTER.compare(o1.loc, o2.loc);

    public boolean couldAttack(List<Unit> enemies) {
        for (Unit enemy : enemies) {
            if (couldAttack(enemy))
                return true;
        }
        return false;
    }

    public boolean couldAttack(Unit enemy) {
        if (enemy.loc.getRow() == loc.getRow() && Math.abs(enemy.loc.getCol() - loc.getCol()) == 1) {
            return true;
        } else return enemy.loc.getCol() == loc.getCol() && Math.abs(enemy.loc.getRow() - loc.getRow()) == 1;
    }

    @Override
    public String toString() {
        return "[" + id + " " + type + " " + loc + " " + hp + "]";
    }

    public Unit cloneWithNewPower(int power) {
        return new Unit(loc, type, hp, power);
    }
}
