package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day13AMazeOfTwistyLittleCubiclesTest  extends FlatSpec with Matchers {
  "2016 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13AMazeOfTwistyLittleCubicles("/twenty_sixteen/Day13-AMazeOfTwistyLittleCubicles-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day13AMazeOfTwistyLittleCubicles("/twenty_sixteen/Day13-AMazeOfTwistyLittleCubicles-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
