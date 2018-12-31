package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.Location;
import advent.twenty_eighteen.support.Unit;
import advent.utilities.ArrayUtilities;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;
import java.util.stream.Collectors;

public class Day15BeverageBandits implements DailyProblem<Integer, Integer> {
    private final Integer part1Answer;
    private final Integer part2Answer;
    private final char[][] map;

    public Day15BeverageBandits(String name) {
        List<String> lines = FileUtilities.readLines(name, Parsers::ToStringNoTrim);

        List<Unit> elves = new LinkedList<>();
        List<Unit> goblins = new LinkedList<>();

        this.map = new char[lines.size()][];
        for (int i = 0; i != lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                if (map[row][col] == 'E') {
                    Unit unit = new Unit(new Location(row, col), 'E', 200, 3);
                    elves.add(unit);
                    map[row][col] = '.';
                }
                if (map[row][col] == 'G') {
                    Unit unit = new Unit(new Location(row, col), 'G', 200, 3);
                    goblins.add(unit);
                    map[row][col] = '.';
                }
            }
        }

        this.part1Answer = simulate(map, clone(elves, 3), clone(goblins, 3), false);

        int power = 3;
        Integer result = null;
        while (power <= 200 && result == null) {
            result = simulate(map, clone(elves, power), clone(goblins, 3), true);
            power++;
        }
        this.part2Answer = result;
    }

    private static List<Unit> clone(List<Unit> units, int power) {
        List<Unit> results = new LinkedList<>();
        for (Unit unit : units) {
            results.add(unit.cloneWithNewPower(power));
        }
        return results;
    }

    private Integer simulate(char[][] map, List<Unit> elves, List<Unit> goblins, boolean partTwo) {
        int allElvesSurvived = elves.size();

        int[][] distances = new int[map.length][];
        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                distances[row] = new int[map[row].length];
            }
        }

        int round = 0;

        boolean partial;
        while ((!elves.isEmpty() && !goblins.isEmpty())) {
            partial = false;

            List<Unit> unitsMoveOrder = new LinkedList<>();
            unitsMoveOrder.addAll(elves);
            unitsMoveOrder.addAll(goblins);
            unitsMoveOrder.sort(Unit.UNIT_SORTER);

            for (Unit unit : unitsMoveOrder) {
                if (!(elves.contains(unit) || goblins.contains(unit))) {
                    continue;
                }

                List<Unit> enemies = unit.getType() == 'E' ? goblins : elves;

                if (enemies.size() == 0) {
                    partial = true;
                    break;
                }

                if (!unit.couldAttack(enemies)) {
                    move(distances, unit, elves, goblins);
                }

                attack(unit, enemies);
            }

            if (!partial) {
                round++;
            }
        }

        int elfHp = elves.stream().map(e -> e.hp).collect(Collectors.summingInt(Integer::intValue));
        int goblinHp = goblins.stream().map(e -> e.hp).collect(Collectors.summingInt(Integer::intValue));

        if (partTwo) {
            if (elves.size() == allElvesSurvived) {
                return round * elfHp;
            }
            return null;
        }
        return round * (elfHp + goblinHp);
    }

    private void move(int[][] distances, Unit me, List<Unit> elves, List<Unit> goblins) {
        List<Unit> units = new LinkedList<>();
        units.addAll(elves);
        units.addAll(goblins);

        List<Unit> enemies = me.getType() == 'E' ? goblins : elves;

        calculateDistanceMap(distances, me, units);
        List<Location> attackPoints = calculateAttackPoints(units, enemies);

        Location nearestLocation = calculateNearestLocation(distances, attackPoints);

        if (nearestLocation != null) {
            List<Location> path = calculatePath(distances, nearestLocation, me.loc);
            me.loc = path.get(1);
        }
    }

    private List<Location> calculatePath(int[][] distances, Location start, Location end) {
        LinkedList<Location> path = new LinkedList<>();
        Location current = start;
        List<Location> temp = new LinkedList<>();
        path.add(start);
        while (!current.equals(end)) {
            temp.clear();

            int d = distances[current.getRow()][current.getCol()];
            if (d == Integer.MIN_VALUE) {
                d = Integer.MAX_VALUE;
            }

            if (isValid(distances[current.getRow() - 1][current.getCol()]) && d > distances[current.getRow() - 1][current.getCol()]) {
                temp.add(new Location(current.getRow() - 1, current.getCol()));
            }

            if (isValid(distances[current.getRow() + 1][current.getCol()]) && d > distances[current.getRow() + 1][current.getCol()]) {
                temp.add(new Location(current.getRow() + 1, current.getCol()));
            }

            if (isValid(distances[current.getRow()][current.getCol() - 1]) && d > distances[current.getRow()][current.getCol() - 1]) {
                temp.add(new Location(current.getRow(), current.getCol() - 1));
            }

            if (isValid(distances[current.getRow()][current.getCol() + 1]) && d > distances[current.getRow()][current.getCol() + 1]) {
                temp.add(new Location(current.getRow(), current.getCol() + 1));
            }

            temp.sort(Location.LOCATION_SORTER);
            path.addFirst(temp.get(0));
            current = temp.get(0);
        }
        return path;
    }

    private static boolean isValid(int distance) {
        return !(distance == Integer.MIN_VALUE || distance == -1);
    }

    private void calculateDistanceMap(int[][] distances, Unit me, List<Unit> units) {
        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                if (map[row][col] == '#') {
                    distances[row][col] = Integer.MAX_VALUE;
                }
                distances[row][col] = -1;
            }
        }

        for (Unit e : units) {
            if (e != me) {
                distances[e.loc.getRow()][e.loc.getCol()] = Integer.MIN_VALUE;
            }
        }

        calculate(distances, me.loc.getRow(), me.loc.getCol(), 0);
    }

    private void calculate(int[][] distances, int row, int col, int d) {
        if (row < 0 || col < 0 || map.length <= row || map[row].length <= col)
            return;

        if (map[row][col] == '#') {
            distances[row][col] = Integer.MAX_VALUE;
        } else if (distances[row][col] == -1 || distances[row][col] > d) {
            distances[row][col] = d;

            calculate(distances, row - 1, col, d + 1);
            calculate(distances, row + 1, col, d + 1);
            calculate(distances, row, col - 1, d + 1);
            calculate(distances, row, col + 1, d + 1);
        }
    }

    private List<Location> calculateAttackPoints(List<Unit> units, List<Unit> enemies) {
        Set<Location> locations = new HashSet<>();
        for (Unit e : enemies) {
            if (isClear(e.loc.getRow() - 1, e.loc.getCol(), units)) {
                locations.add(new Location(e.loc.getRow() - 1, e.loc.getCol()));
            }
            if (isClear(e.loc.getRow() + 1, e.loc.getCol(), units)) {
                locations.add(new Location(e.loc.getRow() + 1, e.loc.getCol()));
            }
            if (isClear(e.loc.getRow(), e.loc.getCol() - 1, units)) {
                locations.add(new Location(e.loc.getRow(), e.loc.getCol() - 1));
            }
            if (isClear(e.loc.getRow(), e.loc.getCol() + 1, units)) {
                locations.add(new Location(e.loc.getRow(), e.loc.getCol() + 1));
            }
        }
        return new LinkedList<>(locations);
    }

    private boolean isClear(int row, int col, List<Unit> units) {
        if (map[row][col] == '.') {
            for (Unit u : units) {
                if (u.loc.getRow() == row && u.loc.getCol() == col) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private Location calculateNearestLocation(int[][] distances, List<Location> locations) {
        Map<Integer, List<Location>> locationDistanceMap = new HashMap<>();

        for (Location location : locations) {
            if (distances[location.getRow()][location.getCol()] != -1) {
                if (!locationDistanceMap.containsKey(distances[location.getRow()][location.getCol()])) {
                    locationDistanceMap.put(distances[location.getRow()][location.getCol()], new LinkedList<>());
                }
                locationDistanceMap.get(distances[location.getRow()][location.getCol()]).add(location);
            }
        }

        if (locationDistanceMap.isEmpty())
            return null;

        List<Location> locationList = locationDistanceMap.get(locationDistanceMap.keySet().stream().min(Integer::compareTo).get());
        locationList.sort(Location.LOCATION_SORTER);

        return locationList.get(0);
    }

    private void attack(Unit me, List<Unit> enemies) {
        List<Unit> couldAttack = enemies.stream().filter(e -> me.couldAttack(e)).collect(Collectors.toList());
        if (couldAttack.isEmpty()) {
            return;
        }

        if (couldAttack.size() == 1) {
            doAttack(me, couldAttack.get(0), enemies);
        } else {
            couldAttack.sort((Comparator.comparingInt(o -> o.hp)));

            final int hp = couldAttack.get(0).hp;
            couldAttack = couldAttack.stream().filter(e -> e.hp == hp).collect(Collectors.toList());
            couldAttack.sort(Unit.UNIT_SORTER);

            doAttack(me, couldAttack.get(0), enemies);
        }
    }

    private void doAttack(Unit me, Unit target, List<Unit> enemies) {
        target.setHp(target.getHp() - me.getAttack());
        if (target.hp <= 0) {
            enemies.remove(target);
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
