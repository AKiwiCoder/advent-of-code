package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day04ReposeRecord implements DailyProblem<Integer, Integer> {
    private final List<String> input;

    private static final Comparator<String> SORTER = (s1, s2) -> {
        String s1Date = s1.substring(1, 17).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        String s2Date = s2.substring(1, 17).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        return Long.compare(Long.parseLong(s1Date), Long.parseLong(s2Date));
    };

    private final int part1Answer;
    private final int part2Answer;

    public Day04ReposeRecord(String filename) {
        this.input = FileUtilities.readLines(filename, Parsers::TO_STRING);
        this.input.sort(SORTER);

        final Integer[] currentGuard = new Integer[1];
        Map<Integer, Map<String, Boolean[]>> schedule = new HashMap<>();
        input.forEach(line -> {
            String date = line.substring(6, 12);
            Integer hour = Integer.parseInt(line.substring(12, 14));
            Integer minute = Integer.parseInt(line.substring(15, 17));
            char action = line.charAt(19);
            if (action == 'G') {
                // new Guard
                String guard = line.substring(26).split(" ")[0];
                currentGuard[0] = Integer.parseInt(guard);
                if (!schedule.containsKey(currentGuard[0])) {
                    schedule.put(currentGuard[0], new HashMap<>());
                }
            } else if (action == 'f') {
                // Guard Asleep
                update(true, schedule.get(currentGuard[0]), date, hour, minute);
            } else {
                // Guard Awake
                update(false, schedule.get(currentGuard[0]), date, hour, minute);
            }
        });


        Map<Integer, Integer> timeAsleep = new HashMap<>();
        schedule.forEach((key, value) -> {
            timeAsleep.put(key, count(value.values()));
        });

        int guard = -1;
        int max = -1;
        for (Map.Entry<Integer, Integer> e : timeAsleep.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                guard = e.getKey();
            }
        }

        Map<Integer, Integer> minutes = new HashMap<>();
        schedule.get(guard).forEach((key, value) -> {
            for (int i = 0; i != 60; i++) {
                if (value[i] != null && value[i]) {
                    if (!minutes.containsKey(i)) {
                        minutes.put(i, 1);
                    } else {
                        minutes.put(i, minutes.get(i) + 1);
                    }
                }
            }
        });

        int m = -1;
        int maxMinute = -1;
        for (Map.Entry<Integer, Integer> m1 : minutes.entrySet()) {
            int key = m1.getKey();
            int value = m1.getValue();
            if (value > m) {
                m = value;
                maxMinute = key;
            }
        }

        this.part1Answer = guard * maxMinute;

        Map<Integer, Map<Integer, Integer>> asleepMinuteCount = new HashMap<>();
        schedule.forEach((guardId, scheduleMap) -> {
            for (Map.Entry<String, Boolean[]> sch : scheduleMap.entrySet()) {
                Boolean[] s = sch.getValue();
                for (int i = 0; i != 60; i++) {
                    if (s[i] != null && s[i]) {
                        if (!asleepMinuteCount.containsKey(i)) {
                            asleepMinuteCount.put(i, new HashMap<>());
                        }
                        Map<Integer, Integer> gMap = asleepMinuteCount.get(i);
                        if (!gMap.containsKey(guardId)) {
                            gMap.put(guardId, 1);
                        } else {
                            gMap.put(guardId, gMap.get(guardId) + 1);
                        }
                    }
                }
            }
        });

        int[] maxGuardId = new int[1];
        int[] maxMinuteNum = new int[1];
        int[] maxMinuteCount = new int[1];
        asleepMinuteCount.forEach((minId, mMap) -> mMap.forEach((gid, mCnt) -> {
            if (mCnt > maxMinuteCount[0]) {
                maxMinuteCount[0] = mCnt;
                maxMinuteNum[0] = minId;
                maxGuardId[0] = gid;
            }
        }));

        this.part2Answer = maxGuardId[0] * maxMinuteNum[0];
    }

    private static int count(Collection<Boolean[]> data) {
        int result = 0;
        for (Boolean[] d : data) {
            for (Boolean b : d) {
                result += (b != null && b) ? 1 : 0;
            }
        }
        return result;
    }

    private static void update(boolean asleep, Map<String, Boolean[]> schedule, String date, int hour, int minute) {
        if (!schedule.containsKey(date)) {
            Boolean[] data = new Boolean[60];
            schedule.put(date, data);
        }

        Boolean[] data = schedule.get(date);
        if (hour == 23) {
            minute = 0;
        }

        for (int i = minute; i != 60; i++) {
            data[i] = asleep;
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
