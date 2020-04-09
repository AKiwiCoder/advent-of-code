package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day12SubterraneanSustainabilityTest  extends FlatSpec with Matchers {
  "2018 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12SubterraneanSustainability("/twenty_eighteen/Day12-SubterraneanSustainability-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day12SubterraneanSustainability("/twenty_eighteen/Day12-SubterraneanSustainability-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
