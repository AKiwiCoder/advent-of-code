package advent.twenty_eighteen

import advent.common.DailyProblem
import advent.twenty_seventeen.Day01InverseCaptcha
import java.util.function.IntFunction
import java.util.stream.Collectors

class Day01ChronalCalibration : DailyProblem<Int, Int> {
    override var part1Answer: Int = 0
    override var part2Answer: Int = 0

    constructor(filename: String) {
        val changes = Day01InverseCaptcha::class.java.getResource(filename).openStream().bufferedReader().lines()
            .map { line -> Integer.parseInt(line) }.collect(Collectors.toList())

        var frequency = 0
        for (change in changes) {
            frequency += change
        }
        part1Answer = frequency

        frequency = 0
        var frequencies = HashSet<Int>()
        var idx = 0
        while (!frequencies.contains(frequency)) {
            if (idx >= changes.size) {
                idx = 0
            }
            frequencies.add(frequency)
            frequency += changes[idx]
            idx++
        }
        part2Answer = frequency
    }
}