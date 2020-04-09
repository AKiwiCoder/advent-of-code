package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day03SpiralMemoryTest  extends FlatSpec with Matchers {
  "2017 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03SpiralMemory("/twenty_seventeen/Day03-SpiralMemory-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day03SpiralMemory("/twenty_seventeen/Day03-SpiralMemory-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
