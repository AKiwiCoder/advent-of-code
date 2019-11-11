package advent.twenty_seventeen

import advent.common.DailyProblem
import java.lang.Integer.sum
import java.util.stream.Collector
import java.util.stream.Collectors

class Day01InverseCaptcha : DailyProblem<Int, Int> {
    override var part1Answer: Int = -1
    override var part2Answer: Int = -1

    constructor (filename: String) {
        val input = Day01InverseCaptcha::class.java.getResource(filename).openStream().bufferedReader().lines()
            .map { line -> Pair(calculatePart1(line), calculatePart2(line)) }.collect(Collectors.toList())

        part1Answer = input.map { (one, two) -> one }.sum()
        part2Answer = input.map { (one, two) -> two }.sum()
    }

    private fun calculatePart1(line: String): Int {
        var result = 0
        for (i in line.indices) {
            val next = if (i >= line.length - 1) 0 else i + 1
            if (line[i] == line[next]) {
                result += line[i] - '0'
            }
        }
        return result
    }

    private fun calculatePart2(line: String): Int {
        var result = 0
        for (i in line.indices) {
            val next = (i + line.length / 2) % line.length
            if (line[i] == line[next]) {
                result += line[i] - '0'
            }
        }
        return result
    }
}