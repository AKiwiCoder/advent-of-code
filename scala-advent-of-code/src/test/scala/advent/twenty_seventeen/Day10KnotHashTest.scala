package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day10KnotHashTest  extends FlatSpec with Matchers {
  "2017 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10KnotHash("/twenty_seventeen/Day10-KnotHash-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day10KnotHash("/twenty_seventeen/Day10-KnotHash-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
