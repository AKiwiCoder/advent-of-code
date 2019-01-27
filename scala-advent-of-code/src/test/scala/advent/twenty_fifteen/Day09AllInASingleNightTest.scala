package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day09AllInASingleNightTest  extends FlatSpec with Matchers {
  "2015 Day 9 - Input" should "calculate answers" in {
    val dp = new Day09AllInASingleNight("/twenty_fifteen/Day09-AllInASingleNight-input.txt")

    dp.part1Answer should be(207)
    dp.part2Answer should be(804)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day09AllInASingleNight("/twenty_fifteen/Day09-AllInASingleNight-example#1.txt")

    dp.part1Answer should be(605)
    dp.part2Answer should be(982)
  }
}
