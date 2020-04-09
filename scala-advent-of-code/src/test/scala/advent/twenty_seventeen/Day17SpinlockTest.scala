package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day17SpinlockTest  extends FlatSpec with Matchers {
  "2017 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17Spinlock("/twenty_seventeen/Day17-Spinlock-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day17Spinlock("/twenty_seventeen/Day17-Spinlock-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
