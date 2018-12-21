package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.*;
import java.util.stream.Collectors;

public class TheSumOfItsParts implements DailyProblem<String, Integer> {
    private static final String JOB_NAMES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final DefaultDirectedGraph<Character, DefaultEdge> graph;
    private final List<Character> ordered;
    private final int completedInSeconds;

    public TheSumOfItsParts(String name, int numberOfElfWorkers, int baseJobLength) {
        List<String> lines = FileUtilities.readLines(name, Parsers::ToString);

        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        lines.forEach(line -> {
            //Step X must be finished before step P can begin.
            String[] bits = line.split("\\s");
            Character before = bits[1].charAt(0);
            Character after = bits[7].charAt(0);

            graph.addVertex(before);
            graph.addVertex(after);

            graph.addEdge(before, after);
        });

        TopologicalOrderIterator<Character, DefaultEdge> iter = new TopologicalOrderIterator<>(graph, Comparator.naturalOrder());

        this.ordered = new LinkedList<>();
        while (iter.hasNext()) {
            Character v = iter.next();
            ordered.add(v);
        }

        Map<Character, Integer> lengths = new HashMap<>();
        for (int i = 0; i != JOB_NAMES.length(); i++) {
            lengths.put(JOB_NAMES.charAt(i), (JOB_NAMES.charAt(i) - 'A') + 1 + baseJobLength);
        }

        int seconds = 0;
        Set<Character> done = new HashSet<>();
        Set<Character> inProgress = new HashSet<>();
        Map<Character, Integer> ticks = new HashMap<>();
        Character elves[] = new Character[numberOfElfWorkers];
        while (done.size() != ordered.size()) {
            for (int elfNumber = 0; elfNumber != elves.length; elfNumber++) {
                if (elves[elfNumber] != null) {
                    int tick = ticks.get(elves[elfNumber]);
                    tick++;

                    if (tick == lengths.get(elves[elfNumber])) {
                        done.add(elves[elfNumber]);
                        inProgress.remove(elves[elfNumber]);
                        elves[elfNumber] = null;
                    } else {
                        ticks.put(elves[elfNumber], tick);
                    }
                }
            }

            for (int elfNumber = 0; elfNumber != elves.length; elfNumber++) {
                if (elves[elfNumber] == null) {
                    Character nextJobName = findNextJob(ordered, inProgress, graph, done);
                    if (nextJobName != null) {
                        elves[elfNumber] = nextJobName;
                        inProgress.add(nextJobName);
                        ticks.put(nextJobName, 0);
                    }
                }
            }
            seconds++;
        }
        this.completedInSeconds = seconds - 1;
    }

    private static Character findNextJob(List<Character> jobs, Set<Character> inProgress, DefaultDirectedGraph<Character, DefaultEdge> directedGraph, Set<Character> done) {
        for (Character job : jobs) {
            if (inProgress.contains(job) || done.contains(job)) {
                continue;
            }
            if (canStart(job, directedGraph, done)) {
                return job;
            }
        }
        return null;
    }

    private static boolean canStart(Character job, DefaultDirectedGraph<Character, DefaultEdge> directedGraph, Set<Character> done) {
        boolean canRun = true;
        for (DefaultEdge e : directedGraph.incomingEdgesOf(job)) {
            if (!done.contains(directedGraph.getEdgeSource(e))) {
                canRun = false;
            }
        }
        return canRun;
    }

    @Override
    public String getPart1Answer() {
        return ordered.stream().map(String::valueOf).collect(Collectors.joining());
    }

    @Override
    public Integer getPart2Answer() {
        return completedInSeconds;
    }
}
