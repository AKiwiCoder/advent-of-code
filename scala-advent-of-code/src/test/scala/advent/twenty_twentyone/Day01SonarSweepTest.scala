package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day01SonarSweepTest extends FlatSpec with Matchers {
  "2020 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01SonarSweep("/twenty_twentyone/Day01-SonarSweep-input.txt")

    dp.part1Answer should be(1448)
    dp.part2Answer should be(1471)
  }

  "2020 Day 01 - Example #1" should "calculate answers" in {
    val dp = new Day01SonarSweep("/twenty_twentyone/Day01-SonarSweep-example#1.txt")

    dp.part1Answer should be(7)
    dp.part2Answer should be(5)
  }
}


