package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.*;
import java.util.stream.Collectors;

public class Day07RecursiveCircus implements DailyProblem<String, Integer> {
    private final String part1Answer;
    private final int part2Answer;

    private final Graph<String, DefaultEdge> graph;

    public Day07RecursiveCircus(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        Map<String, Integer> weights = new HashMap<>();

        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (String line : lines) {
            String name = line.substring(0, line.indexOf("(")).trim();
            Integer weight = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim());

            weights.put(name, weight);

            graph.addVertex(name);

            if (line.indexOf("->") > -1) {
                String depends[] = line.substring(line.indexOf("->") + 2).trim().split(",");
                for (String depend : depends) {
                    graph.addVertex(depend.trim());
                    graph.addEdge(name, depend.trim());
                }
            }
        }

        TopologicalOrderIterator<String, DefaultEdge> iter = new TopologicalOrderIterator<>(graph, Comparator.naturalOrder());

        List<String> ordered = new LinkedList<>();
        while (iter.hasNext()) {
            String v = iter.next();
            ordered.add(v);
        }

        this.part1Answer = ordered.get(0);

        Set<String> targetsSet = new HashSet<>();
        Set<String> sourcesSet = new HashSet<>();
        Set<DefaultEdge> edges = graph.edgeSet();
        for (DefaultEdge edge : edges) {
            sourcesSet.add(graph.getEdgeSource(edge));
            targetsSet.add(graph.getEdgeTarget(edge));
        }

        targetsSet.removeAll(sourcesSet);

        Map<String, Integer> amendedWeights = new HashMap<>();

        balanceNode("", graph, ordered.get(0), weights, amendedWeights);

        this.part2Answer = amendedWeights.values().iterator().next();
    }

    private int balanceNode(String padding, Graph<String, DefaultEdge> graph, String node, Map<String, Integer> weights, Map<String, Integer> amendedWeights) {
        Map<String, Integer> branchWeights = new HashMap<>();
        Map<Integer, List<String>> weightBranch = new HashMap<>();

        Set<DefaultEdge> outgoingEdgesOf = graph.outgoingEdgesOf(node);

        if (outgoingEdgesOf.isEmpty()) {
            return weights.get(node);
        } else {
            for (DefaultEdge edge : outgoingEdgesOf) {
                String other = graph.getEdgeTarget(edge);
                Integer branchWeight = balanceNode(padding + "  ", graph, other, weights, amendedWeights);
                branchWeights.put(other, branchWeight);

                List<String> branchList = weightBranch.getOrDefault(branchWeight, new LinkedList<>());
                branchList.add(other);
                weightBranch.put(branchWeight, branchList);
            }
        }

        Set<Integer> bw = new HashSet<>(branchWeights.values());
        if (bw.size() == 2) {
            Map<Integer, Integer> branchWeightCounts = new HashMap<>();
            for (int b : branchWeights.values()) {
                branchWeightCounts.put(b, branchWeightCounts.getOrDefault(b, 0) + 1);
            }

            String wrongName = "";
            for (int weight : branchWeightCounts.keySet()) {
                if (branchWeightCounts.get(weight) == 1) {
                    if (weightBranch.get(weight).size() > 1) {
                        throw new IllegalStateException("Too many wrong weight branches " + branchWeightCounts);
                    }
                    wrongName = weightBranch.get(weight).get(0);
                    break;
                }
            }

            branchWeightCounts.remove(branchWeights.get(wrongName));
            int correctWeight = branchWeightCounts.keySet().iterator().next();

            amendedWeights.put(wrongName, (weights.get(wrongName) + correctWeight - branchWeights.get(wrongName)));
            weights.put(wrongName, (weights.get(wrongName) + correctWeight - branchWeights.get(wrongName)));
            branchWeights.put(wrongName, correctWeight);
        }
        return branchWeights.values().stream().mapToInt(Integer::intValue).sum() + weights.get(node);
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
