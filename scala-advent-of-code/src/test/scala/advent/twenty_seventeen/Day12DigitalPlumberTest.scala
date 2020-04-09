package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day12DigitalPlumberTest  extends FlatSpec with Matchers {
  "2017 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12DigitalPlumber("/twenty_seventeen/Day12-DigitalPlumber-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day12DigitalPlumber("/twenty_seventeen/Day12-DigitalPlumber-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
