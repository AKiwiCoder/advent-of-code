package advent.twenty_seventeen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01InverseCaptchaTest {
    @Test
    fun checkReal() {
        val dp = Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-input.txt")

        assertEquals(1253, dp.part1Answer)
        assertEquals(1278, dp.part2Answer)
    }

    @Test
    fun checkExample1() {
        val dp = Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-example#1.txt")

        assertEquals(16, dp.part1Answer)
        assertEquals(10, dp.part2Answer)
    }

    @Test
    fun checkExample2() {
        val dp = Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-example#2.txt")

        assertEquals(3, dp.part1Answer)
        assertEquals(26, dp.part2Answer)
    }
}