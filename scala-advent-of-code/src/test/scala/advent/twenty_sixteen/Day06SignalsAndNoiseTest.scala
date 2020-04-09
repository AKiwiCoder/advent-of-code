package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day06SignalsAndNoiseTest  extends FlatSpec with Matchers {
  "2016 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
