package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20ParticleSwarm implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    static class Particle {
        long x;
        long y;
        long z;

        long vx;
        long vy;
        long vz;

        long ax;
        long ay;
        long az;

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + z + ")";
        }
    }

    public Day20ParticleSwarm(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        this.part1Answer = runPart1(getParticlesFromInput(lines));
        this.part2Answer = runPart2(getParticlesFromInput(lines));
    }

    private int runPart1(Particle[] particles) {
        int step = 0;
        Map<Integer, Integer> nearest = new HashMap<>();

        while (step != 100000) {
            long minD = Long.MAX_VALUE;
            int pIdx = -1;
            for (int p = 0; p != particles.length; p++) {
                updateParticlePosition(particles, p);

                long d = Math.abs(particles[p].x) + Math.abs(particles[p].y) + Math.abs(particles[p].z);
                if (d < minD) {
                    minD = d;
                    pIdx = p;
                }
            }
            nearest.put(pIdx, nearest.getOrDefault(pIdx, 0) + 1);
            step++;
        }

        int nIdx = -1;
        int maxCount = -1;
        for (Map.Entry<Integer, Integer> entry : nearest.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                nIdx = entry.getKey();
            }
        }
        return nIdx;
    }

    private void updateParticlePosition(Particle[] particles, int p) {
        particles[p].vx += particles[p].ax;
        particles[p].vy += particles[p].ay;
        particles[p].vz += particles[p].az;

        particles[p].x += particles[p].vx;
        particles[p].y += particles[p].vy;
        particles[p].z += particles[p].vz;
    }

    private int runPart2(Particle[] particles) {
        Map<Integer, Integer> nearest = new HashMap<>();

        int step = 0;
        int alive = particles.length;
        int previous = 0;
        while (alive > 0 && step < 10000) {
            previous = alive;
            for (int p = 0; p != particles.length; p++) {
                if (particles[p] == null) {
                    continue;
                }
                updateParticlePosition(particles, p);
            }

            for (int a = 0; a != particles.length - 1; a++) {
                if (particles[a] != null) {
                    boolean aMatched = false;
                    for (int b = a+1; b != particles.length; b++) {
                        if (particles[b] != null) {
                            boolean samePos = particles[a].x == particles[b].x;
                            samePos = samePos && particles[a].y == particles[b].y;
                            samePos = samePos && particles[a].z == particles[b].z;

                            if (samePos) {
                                particles[b] = null;
                                aMatched = true;
                                alive--;
                            }
                        }
                    }
                    if (aMatched) {
                        particles[a] = null;
                        alive--;
                    }
                }
            }

            if (previous == alive) {
                step++;
            } else {
                step = 0;
            }
        }
        return alive;
    }


    private Particle[] getParticlesFromInput(List<String> lines) {
        Particle[] particles = new Particle[lines.size()];
        for (int p = 0; p != particles.length; p++) {
        // p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>
            particles[p] = new Particle();
            String line = lines.get(p);
            int i = line.indexOf("<");
            int j = line.indexOf(">", i);
            String[] bits = line.substring(i+1,j).split(",");
            particles[p].x = Integer.parseInt(bits[0].trim());
            particles[p].y = Integer.parseInt(bits[1].trim());
            particles[p].z = Integer.parseInt(bits[2].trim());
            i = line.indexOf("<", j);
            j = line.indexOf(">", i);
            bits = line.substring(i+1,j).split(",");
            particles[p].vx = Integer.parseInt(bits[0].trim());
            particles[p].vy = Integer.parseInt(bits[1].trim());
            particles[p].vz = Integer.parseInt(bits[2].trim());
            i = line.indexOf("<", j);
            j = line.indexOf(">", i);
            bits = line.substring(i+1,j).split(",");
            particles[p].ax = Integer.parseInt(bits[0].trim());
            particles[p].ay = Integer.parseInt(bits[1].trim());
            particles[p].az = Integer.parseInt(bits[2].trim());
        }
        return particles;
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
