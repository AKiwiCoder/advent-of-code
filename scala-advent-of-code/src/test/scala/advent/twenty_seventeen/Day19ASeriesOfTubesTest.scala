package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day19ASeriesOfTubesTest  extends FlatSpec with Matchers {
  "2017 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19ASeriesOfTubes("/twenty_seventeen/Day19-ASeriesOfTubes-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day19ASeriesOfTubes("/twenty_seventeen/Day19-ASeriesOfTubes-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
