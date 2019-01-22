package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day06ProbablyAFireHazardTest extends FlatSpec with Matchers {
  "2015 Day 6 - Input" should "calculate answers" in {
    val dp = new Day06ProbablyAFireHazard("/twenty_fifteen/Day06-ProbablyAFireHazard-input.txt")
    dp.part1Answer should be(569999)
    dp.part2Answer should be(17836115)
  }

  "2015 Day 6 - Example #1" should "calculate answers" in {
    val dp = new Day06ProbablyAFireHazard("/twenty_fifteen/Day06-ProbablyAFireHazard-example#1.txt")
    dp.part1Answer should be(998996)
    dp.part2Answer should be(1001996)
  }

}
