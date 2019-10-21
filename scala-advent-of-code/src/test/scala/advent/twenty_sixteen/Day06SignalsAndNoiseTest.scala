package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day06SignalsAndNoiseTest  extends FlatSpec with Matchers {
  "2016 Day 6 - Input" should "calculate answers" in {
    val dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-input.txt")

    dp.part1Answer should be("gebzfnbt")
    dp.part2Answer should be("fykjtwyn")
  }

  "2016 Day 6 - Example #1" should "calculate answers" in {
    val dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-example#1.txt")

    dp.part1Answer should be("easter")
    dp.part2Answer should be("advent")
  }

}
