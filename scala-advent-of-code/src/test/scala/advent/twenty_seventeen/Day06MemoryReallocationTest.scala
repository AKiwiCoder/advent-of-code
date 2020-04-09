package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day06MemoryReallocationTest  extends FlatSpec with Matchers {
  "2017 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06MemoryReallocation("/twenty_seventeen/Day06-MemoryReallocation-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day06MemoryReallocation("/twenty_seventeen/Day06-MemoryReallocation-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
