package advent.twenty_fifteen

import advent.common.DailyProblem

class Day01NotQuiteLisp : DailyProblem<Int, Int> {
    override var part1Answer: Int = 0
    override var part2Answer: Int = -1

    constructor (filename: String) {
        val input = Day01NotQuiteLisp::class.java.getResource(filename).readText()

        var floor = 0
        input.forEachIndexed { idx, c ->
            floor += when (c) {
                '(' -> +1
                ')' -> -1
                else -> 0
            }
            if (floor == -1 && part2Answer == -1) {
                part2Answer = idx + 1
            }
        }
        part1Answer = floor
    }
}