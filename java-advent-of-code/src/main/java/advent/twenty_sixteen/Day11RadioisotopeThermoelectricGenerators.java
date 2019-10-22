package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.support.RadioisotopeComponent;
import advent.twenty_sixteen.support.RadioisotopeComponentGenerator;
import advent.twenty_sixteen.support.RadioisotopeComponentMicrochip;
import advent.utilities.FileUtilities;
import com.google.common.collect.Sets;

import java.util.*;

public class Day11RadioisotopeThermoelectricGenerators implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private RadioisotopeComponentGenerator[] GENERATORS;
    private RadioisotopeComponentMicrochip[] MICROCHIPS;

    private int componentCount;

    public Day11RadioisotopeThermoelectricGenerators(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        int[] state1 = generateInitialState(lines);
        this.part1Answer = findQuickestMoves(state1);

        lines.add("The first floor contains a elerium generator, a elerium-compatible microchip, a dilithium generator and a dilithium-compatible microchip");
        int[] state2 = generateInitialState(lines);
        this.part2Answer = findQuickestMoves(state2);
    }

    private int[] generateInitialState(List<String> lines) {
        Map<RadioisotopeComponent, Integer> startingLocations = new HashMap<>();
        List<RadioisotopeComponentGenerator> listGenerators = new ArrayList<>();
        List<RadioisotopeComponentMicrochip> listMicrochips = new ArrayList<>();

        for (String line : lines) {
            int floor;
            if (line.contains("first")) {
                floor = 0;
            } else if (line.contains("second")) {
                floor = 1;
            } else if (line.contains("third")) {
                floor = 2;
            } else if (line.contains("fourth")) {
                floor = 3;
            } else {
                floor = -1;
            }

            String[] bits = line.split(" ");
            for (int i = 0; i != bits.length - 1; i++) {
                if (bits[i + 1].startsWith("generator")) {
                    RadioisotopeComponentGenerator g = new RadioisotopeComponentGenerator(bits[i]);
                    startingLocations.put(g, floor);
                    listGenerators.add(g);
                }
                if (bits[i + 1].startsWith("microchip")) {
                    RadioisotopeComponentMicrochip m = new RadioisotopeComponentMicrochip(bits[i].split("-")[0]);
                    startingLocations.put(m, floor);
                    listMicrochips.add(m);
                }
            }
        }

        Comparator<? super RadioisotopeComponent> sorter = (Comparator<RadioisotopeComponent>) (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getMaterial(), o2.getMaterial());
        Collections.sort(listGenerators, sorter);
        Collections.sort(listMicrochips, sorter);

        GENERATORS = listGenerators.toArray(new RadioisotopeComponentGenerator[0]);
        MICROCHIPS = listMicrochips.toArray(new RadioisotopeComponentMicrochip[0]);

        this.componentCount = listGenerators.size();

        int state[] = new int[listGenerators.size() * 2 + 1];

        state[0] = 0;
        for (int i = 0; i != componentCount; i++) {
            state[1 + (2 * i)] = startingLocations.get(listGenerators.get(i));
            state[1 + (2 * i) + 1] = startingLocations.get(listMicrochips.get(i));
        }

        return state;
    }

    static class State {
        int steps;
        int[] locations;
    }

    private int sum(int[] locations) {
        int r = 0;
        for (int i = 0; i != locations.length; i++) {
            r += locations[i];
        }
        return r;
    }

    private int findQuickestMoves(int[] state) {
        Comparator<State> sorter = new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                if (o1.steps == o2.steps) {
                    return Integer.compare(sum(o2.locations), sum(o1.locations));
                }
                return Integer.compare(o1.steps, o2.steps);
            }
        };

        PriorityQueue<State> queue = new PriorityQueue<>(sorter);

        State initial = new State();
        initial.steps = 0;
        initial.locations = state;

        queue.add(initial);

        Map<String, Integer> seen = new HashMap<>();

        int targetSum = 3 + componentCount * 3 * 2;

        int best = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            State current = queue.remove();

            String key = Arrays.toString(current.locations);
            if (current.steps >= seen.getOrDefault(key, Integer.MAX_VALUE)) {
                continue;
            }

            if (current.steps >= best) {
                continue;
            }

            seen.put(key, current.steps);

            if (sum(current.locations) == targetSum) {
                best = current.steps;
                continue;
            }

            generateMoves(queue, current);
        }
        return best;
    }

    private static final int[] DIRECTIONS = new int[]{+1, -1};

    private void generateMoves(Queue<State> queue, State current) {
        int floor = current.locations[0];

        Set<Integer> onFloor = new HashSet<>();

        for (int i = 1; i != current.locations.length; i++) {
            if (current.locations[i] == floor) {
                onFloor.add(i);
            }
        }

        if (onFloor.size() == 0) {
            throw new IllegalStateException("Nothing on floor " + floor);
        }

        for (int direction : DIRECTIONS) {
            int newFloor = floor + direction;
            if (newFloor >= 0 && newFloor <= 3) {
                if (onFloor.size() > 1) {
                    for (Set<Integer> ids : Sets.combinations(onFloor, 2)) {
                        int[] newLocations = new int[current.locations.length];
                        System.arraycopy(current.locations, 0, newLocations, 0, current.locations.length);

                        newLocations[0] = newFloor;
                        for (int id : ids) {
                            newLocations[id] = newFloor;
                        }

                        addIfValidMove(queue, current, floor, newFloor, newLocations);
                    }
                }
                for (int id : onFloor) {
                    int[] newLocations = new int[current.locations.length];
                    System.arraycopy(current.locations, 0, newLocations, 0, current.locations.length);

                    newLocations[0] = newFloor;
                    newLocations[id] = newFloor;

                    addIfValidMove(queue, current, floor, newFloor, newLocations);
                }
            }
        }
    }

    private void addIfValidMove(Queue<State> queue, State current, int floor, int newFloor, int[] newLocations) {
        if (isValid(floor, newLocations) && isValid(newFloor, newLocations)) {
            State state = new State();
            state.steps = current.steps + 1;
            state.locations = newLocations;
            queue.add(state);
        }
    }

    private boolean isValid(int floor, int[] locations) {
        int generatorCount = 0;
        int unmatchedChips = 0;
        int total = 0;
        for (int i = 1; i != locations.length; i++) {
            if (locations[i] == floor) {
                total++;

                if (i % 2 == 1) {
                    generatorCount++;
                } else {
                    if (locations[i - 1] != floor) {
                        unmatchedChips++;
                    }
                }
            }
        }

        if (generatorCount > 0 && unmatchedChips == 0) {
            return true;
        }

        if (generatorCount == 0 && unmatchedChips > 0) {
            return true;
        }

        if (total == 0) {
            return true;
        }

        return false;
    }

    public void printFloors(int[] locations) {
        for (int floor = 3; floor >= 0; floor--) {
            System.out.print("F" + floor + " ");
            if (locations[0] == floor) {
                System.out.print(".E.");
            } else {
                System.out.print("...");
            }
            for (int c = 0; c != componentCount; c++) {
                if (locations[2 * c + 1] == floor) {
                    System.out.print("." + GENERATORS[c] + ".");
                } else {
                    System.out.print(".....");
                }
                if (locations[2 * c + 2] == floor) {
                    System.out.print("." + MICROCHIPS[c] + ".");
                } else {
                    System.out.print(".....");
                }
            }
            System.out.println();
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
