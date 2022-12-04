package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day04CampCleanupTest extends FlatSpec with Matchers {
  "2020 Day 03 - Input" should "calculate answers" in {
    val dp = new Day04CampCleanup("/twenty_twentytwo/Day04-CampCleanup-input.txt")

    dp.part1Answer should be(483)
    dp.part2Answer should be(0)
  }

  "2020 Day 03 - Example #1" should "calculate answers" in {
    val dp = new Day04CampCleanup("/twenty_twentytwo/Day04-CampCleanup-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(4)
  }
}


