package advent.twenty_sixteen

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day01NoTimeForATaxicabTest {
    @Test
    fun checkReal() {
        val dp = Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-input.txt")

        Assertions.assertEquals(301, dp.part1Answer)
        Assertions.assertEquals(130, dp.part2Answer)
    }

    @Test
    fun checkExample1() {
        val dp = Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-example#1.txt")

        Assertions.assertEquals(8, dp.part1Answer)
        Assertions.assertEquals(4, dp.part2Answer)
    }
}