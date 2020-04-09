package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day17ReservoirResearchTest  extends FlatSpec with Matchers {
  "2018 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17ReservoirResearch("/twenty_eighteen/Day17-ReservoirResearch-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day17ReservoirResearch("/twenty_eighteen/Day17-ReservoirResearch-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
