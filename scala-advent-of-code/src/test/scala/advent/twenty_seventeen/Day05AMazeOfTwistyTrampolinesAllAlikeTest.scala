package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day05AMazeOfTwistyTrampolinesAllAlikeTest  extends FlatSpec with Matchers {
  "2017 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05AMazeOfTwistyTrampolinesAllAlike("/twenty_seventeen/Day05-AMazeOfTwistyTrampolinesAllAlike-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day05AMazeOfTwistyTrampolinesAllAlike("/twenty_seventeen/Day05-AMazeOfTwistyTrampolinesAllAlike-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
