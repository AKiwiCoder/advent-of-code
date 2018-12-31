package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day23ExperimentalEmergencyTeleportation implements DailyProblem<Integer, Long> {
    private final int part1Answer;
    private final long part2Answer;

    static class Nano {
        private final long x;
        private final long y;
        private final long z;
        private final long r;

        public Nano(long x, long y, long z, long r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        public long getZ() {
            return z;
        }

        public long getR() {
            return r;
        }
    }

    public Day23ExperimentalEmergencyTeleportation(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);
        List<Nano> nanos = new ArrayList<>(lines.stream().map(line -> {
            int lb = line.indexOf('<');
            int rb = line.indexOf('>');
            int re = line.indexOf("r=");

            String pos = line.substring(lb + 1, rb);

            String[] bits = pos.split(",");

            return new Nano(Long.parseLong(bits[0].trim()), Long.parseLong(bits[1].trim()), Long.parseLong(bits[2].trim()), Long.parseLong(line.substring(re + 2)));
        }).collect(Collectors.toList()));

        Nano biggest = nanos.stream().max(Comparator.comparingLong(n0 -> n0.r)).get();

        List<Nano> inRange = nanos.stream().filter(n -> distance(biggest, n) <= biggest.r).collect(Collectors.toList());

        this.part1Answer = inRange.size();

        long minX = nanos.stream().min(Comparator.comparingLong(n0 -> n0.x)).get().x;
        long maxX = nanos.stream().max(Comparator.comparingLong(n0 -> n0.x)).get().x;
        long minY = nanos.stream().min(Comparator.comparingLong(n0 -> n0.y)).get().y;
        long maxY = nanos.stream().max(Comparator.comparingLong(n0 -> n0.y)).get().y;
        long minZ = nanos.stream().min(Comparator.comparingLong(n0 -> n0.z)).get().z;
        long maxZ = nanos.stream().max(Comparator.comparingLong(n0 -> n0.z)).get().z;

        List<Long> results = new LinkedList<>();

        long jitter = (maxX - minX) / 10000000;
        for (int tryCount = 0; tryCount != 50; tryCount++) {
            long cx = 0;
            long cy = 0;
            long cz = 0;
            long m = Integer.MIN_VALUE;

            // Do Stuff Here
            long currentx = (maxX - minX) / 2 + jitter;
            long currenty = (maxY - minY) / 2 + jitter;
            long currentz = (maxZ - minZ) / 2 + jitter;
            m = nanosInRange(nanos, currentx, currenty, currentz);

            long dx = (maxX - minX) - 10;
            long dy = (maxY - minY) - 10;
            long dz = (maxZ - minZ) - 10;

            while (dx != 0 && dy != 0 && dz != 0) {
                boolean bigger = false;
                for (int sy = -1; sy != 2; sy++) {
                    for (int sx = -1; sx != 2; sx++) {
                        for (int sz = -1; sz != 2; sz++) {
                            long thisCount = nanosInRange(nanos, currentx + sx * dx, currenty + sy * dy, currentz + sz * dz);
                            if (thisCount > m) {
                                cx = currentx + sx * dx;
                                cy = currenty + sy * dy;
                                cz = currentz + sz * dz;
                                m = thisCount;
                                bigger = true;
                            }
                        }
                    }
                }
                if (bigger) {
                    currentx = cx;
                    currenty = cy;
                    currentz = cz;
                } else {
                    dx = dx / 2;
                    dy = dy / 2;
                    dz = dz / 2;
                }
            }

            Nano origin = new Nano(0, 0, 0, 0);
            Nano point = new Nano(currentx, currenty, currentz, 0);
            results.add(distance(origin, point));
            jitter = -jitter * 2;
        }

        this.part2Answer = results.stream().min(Comparator.naturalOrder()).get();
    }

    private static long nanosInRange(List<Nano> nanos, long x, long y, long z) {
        long count = 0;
        Nano fake = new Nano(x, y, z, 0);
        for (int i = 0; i != nanos.size(); i++) {
            Nano n = nanos.get(i);
            if (distance(n, fake) <= n.r) {
                count++;
            }
        }
        return count;
    }

    private static long distance(Nano lhs, Nano rhs) {
        return Math.abs(lhs.x - rhs.x) + Math.abs(lhs.y - rhs.y) + Math.abs(lhs.z - rhs.z);
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        return part2Answer;
    }
}
