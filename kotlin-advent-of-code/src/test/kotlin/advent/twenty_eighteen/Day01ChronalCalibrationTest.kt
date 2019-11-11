package advent.twenty_eighteen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01ChronalCalibrationTest {
        @Test
        fun checkReal() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-input.txt")

            assertEquals(430, cc.part1Answer)
            assertEquals(462, cc.part2Answer)
        }

        @Test
        fun checkExample1() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-example#1.txt")

            assertEquals(3, cc.part1Answer)
            assertEquals(2, cc.part2Answer)
        }

        @Test
        fun checkExample2() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-example#2.txt")

            assertEquals(0, cc.part1Answer)
            assertEquals(0, cc.part2Answer)
        }

        @Test
        fun checkExample3() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-example#3.txt")

            assertEquals(4, cc.part1Answer)
            assertEquals(10, cc.part2Answer)
        }

        @Test
        fun checkExample4() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-example#4.txt")

            assertEquals(4, cc.part1Answer)
            assertEquals(5, cc.part2Answer)
        }

        @Test
        fun checkExample5() {
            val cc = Day01ChronalCalibration("/twenty_eighteen/Day01-ChronalCalibration-example#5.txt")

            assertEquals(1, cc.part1Answer)
            assertEquals(14, cc.part2Answer)
        }
}