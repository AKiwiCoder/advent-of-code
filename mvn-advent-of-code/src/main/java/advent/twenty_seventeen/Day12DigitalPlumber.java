package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Day12DigitalPlumber implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day12DigitalPlumber(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        DefaultDirectedGraph<Object, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        Set<Integer> programs = new HashSet<>();
        for (String line : lines) {
            String[] bits = line.split("<->");
            Integer programId = Integer.parseInt(bits[0].trim());
            programs.add(programId);
            graph.addVertex(programId);
            bits = bits[1].split(",");
            for (String bit : bits) {
                Integer otherProgram = Integer.parseInt(bit.trim());
                programs.add(otherProgram);
                graph.addVertex(otherProgram);
                graph.addEdge(programId, otherProgram);
                graph.addEdge(otherProgram, programId);
            }
        }

        int count = countConnections(graph, Integer.valueOf(0)).size();

        this.part1Answer = count;

        int groups = 0;
        Set<Integer> all = new HashSet<>(programs);
        while (!all.isEmpty()) {
            Integer next = all.iterator().next();
            Set<Object> in = countConnections(graph,next);
            all.removeAll(in);
            groups++;
        }

        this.part2Answer = groups;
    }

    private Set<Object> countConnections(DefaultDirectedGraph<Object, DefaultEdge> graph, Integer start) {
        Stack<DefaultEdge> toCheck = new Stack<>();
        for (DefaultEdge edge : graph.edgesOf(start)) {
            toCheck.push(edge);
        }

        Set<Object> visited = new HashSet<>();

        while (!toCheck.isEmpty()) {
            DefaultEdge edge = toCheck.pop();
            Object src = graph.getEdgeSource(edge);
            Object dst = graph.getEdgeTarget(edge);

            if (!visited.contains(src)) {
                visited.add(src);
                for (DefaultEdge e : graph.edgesOf(src)) {
                    toCheck.push(e);
                }
            }
            if (!visited.contains(dst)) {
                visited.add(dst);
                for (DefaultEdge e : graph.edgesOf(dst)) {
                    toCheck.push(e);
                }
            }
        }

        return visited;
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
