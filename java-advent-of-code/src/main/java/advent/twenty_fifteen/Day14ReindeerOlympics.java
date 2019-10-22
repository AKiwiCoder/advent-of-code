package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.twenty_fifteen.support.Reindeer;
import advent.utilities.FileUtilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14ReindeerOlympics implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day14ReindeerOlympics(final String filename, final int time) {
        List<Reindeer> reindeer = FileUtilities.readLines(filename, Reindeer::PARSE);

        Map<Reindeer, Integer> distanceTravelled = positionsAfterTime(time, reindeer);

        this.part1Answer = distanceTravelled.values().stream().max(Comparator.naturalOrder()).get();

        Map<Reindeer, Integer> points = new HashMap<>();
        for (int tick = 1; tick != time; tick++) {
            Map<Reindeer, Integer> distanceTravelledThisTick = positionsAfterTime(tick, reindeer);
            int max = distanceTravelledThisTick.values().stream().max(Comparator.naturalOrder()).get();
            for (Map.Entry<Reindeer, Integer> entry : distanceTravelledThisTick.entrySet()) {
                if (entry.getValue().intValue() == max) {
                    points.put(entry.getKey(), points.getOrDefault(entry.getKey(), 0) + 1);
                }
            }
        }

        this.part2Answer = points.values().stream().max(Comparator.naturalOrder()).get();
    }

    private Map<Reindeer, Integer> positionsAfterTime(int time, List<Reindeer> reindeer) {
        Map<Reindeer, Integer> distanceTravelled = new HashMap<>();

        for (Reindeer r : reindeer) {
            int wholePeriods = time / (r.getFlyPeriod() + r.getRestPeriod());
            int remainingTime = time % (r.getFlyPeriod() + r.getRestPeriod());
            int flyTime = Math.min(remainingTime, r.getFlyPeriod());
            distanceTravelled.put(r, wholePeriods * r.getSpeed() * r.getFlyPeriod() + flyTime * r.getSpeed());
        }

        return distanceTravelled;
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
