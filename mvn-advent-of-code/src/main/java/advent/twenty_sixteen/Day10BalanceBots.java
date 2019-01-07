package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day10BalanceBots implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private void addBotValue(Map<Integer, LinkedList<Integer>> botsHolding, Integer bot, Integer value) {
        LinkedList<Integer> holdings = botsHolding.getOrDefault(bot, new LinkedList<>());
        holdings.add(value);
        Collections.sort(holdings);
        botsHolding.put(bot, holdings);
    }

    private void removeBotValue(Map<Integer, LinkedList<Integer>> botsHolding, Integer bot, Integer value) {
        LinkedList<Integer> holdings = botsHolding.get(bot);
        holdings.remove(value);
        Collections.sort(holdings);
        botsHolding.put(bot, holdings);
    }

    public Day10BalanceBots(String filename, int targetLow, int targetHigh) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        Map<Integer, LinkedList<Integer>> botsHolding = new HashMap<>();

        for (String line : lines) {
            if (line.startsWith("value")) {
                String[] bits = line.split("\\s+");

                int value = Integer.parseInt(bits[1]);
                int bot = Integer.parseInt(bits[5]);
                addBotValue(botsHolding, bot, value);
            }
        }
        
        int count = 0;
        Integer foundBotNumber = -1;
        LinkedList<String> inputs = new LinkedList<>(lines);
        while (!inputs.isEmpty()) {
            String line = inputs.pop();
            if (line.startsWith("bot")) {
                String[] bits = line.split("\\s+");

                Integer fromBot = Integer.parseInt(bits[1]);
                Integer lowBot = Integer.parseInt(bits[6]);
                if (bits[5].equals("output")) {
                    lowBot += 1000000;
                }
                Integer highBot = Integer.parseInt(bits[11]);
                if (bits[10].equals("output")) {
                    highBot += 1000000;
                }

                LinkedList<Integer> holdings = botsHolding.getOrDefault(fromBot, new LinkedList<>());
                LinkedList<Integer> lowHoldings = botsHolding.getOrDefault(lowBot, new LinkedList<>());
                LinkedList<Integer> highHoldings = botsHolding.getOrDefault(highBot, new LinkedList<>());

                if (holdings.size() == 2 && lowHoldings.size() < 2 && highHoldings.size() < 2) {
                    Integer low = holdings.getFirst();
                    Integer high = holdings.getLast();

                    if (low == targetLow && high == targetHigh) {
                        foundBotNumber = fromBot;
                    }

                    removeBotValue(botsHolding, fromBot, low);
                    removeBotValue(botsHolding, fromBot, high);
                    addBotValue(botsHolding, lowBot, low);
                    addBotValue(botsHolding, highBot, high);
                } else {
                    inputs.addLast(line);
                }
            }
            count++;
            if (count == 10000) {System.exit(1);}
        }

        this.part1Answer = foundBotNumber;
        this.part2Answer = botsHolding.get(1000000).get(0) * botsHolding.get(1000001).get(0) * botsHolding.get(1000002).get(0);
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
