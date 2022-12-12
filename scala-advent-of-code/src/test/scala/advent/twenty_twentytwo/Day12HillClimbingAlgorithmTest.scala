package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day12HillClimbingAlgorithmTest extends FlatSpec with Matchers {
  "2020 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12HillClimbingAlgorithm("/twenty_twentytwo/Day12-HillClimbingAlgorithm-input.txt")

    dp.part1Answer should be(456)
    dp.part2Answer should be(0)
  }

  "2020 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12HillClimbingAlgorithm("/twenty_twentytwo/Day12-HillClimbingAlgorithm-example#1.txt")

    dp.part1Answer should be(31)
    dp.part2Answer should be(29)
  }
}


