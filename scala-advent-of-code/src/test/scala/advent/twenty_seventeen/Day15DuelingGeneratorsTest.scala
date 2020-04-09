package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day15DuelingGeneratorsTest  extends FlatSpec with Matchers {
  "2017 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15DuelingGenerators("/twenty_seventeen/Day15-DuelingGenerators-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day15DuelingGenerators("/twenty_seventeen/Day15-DuelingGenerators-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
