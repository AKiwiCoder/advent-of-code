package advent.twenty_fifteen


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01NotQuiteLispTest {
    @Test
    fun checkReal() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-input.txt")

        assertEquals(74, dp.part1Answer)
        assertEquals(1795, dp.part2Answer)
    }

    @Test
    fun checkExample1() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#1.txt")

        assertEquals(0, dp.part1Answer)
        assertEquals(-1, dp.part2Answer)
    }

    @Test
    fun checkExample2() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#2.txt")

        assertEquals(0, dp.part1Answer)
        assertEquals(-1, dp.part2Answer)
    }

    @Test
    fun checkExample3() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#3.txt")

        assertEquals(3, dp.part1Answer)
        assertEquals(-1, dp.part2Answer)
    }

    @Test
    fun checkExample4() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#4.txt")

        assertEquals(3, dp.part1Answer)
        assertEquals(-1, dp.part2Answer)
    }

    @Test
    fun checkExample5() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#5.txt")

        assertEquals(3, dp.part1Answer)
        assertEquals(1, dp.part2Answer)
    }

    @Test
    fun checkExample6() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#6.txt")

        assertEquals(-1, dp.part1Answer)
        assertEquals(3, dp.part2Answer)
    }

    @Test
    fun checkExample7() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#7.txt")

        assertEquals(-1, dp.part1Answer)
        assertEquals(1, dp.part2Answer)
    }

    @Test
    fun checkExample8() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#8.txt")

        assertEquals(-3, dp.part1Answer)
        assertEquals(1, dp.part2Answer)
    }

    @Test
    fun checkExample9() {
        val dp = Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#9.txt")

        assertEquals(-3, dp.part1Answer)
        assertEquals(1, dp.part2Answer)
    }
}