package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day16ProboscideaVolcaniumTest extends FlatSpec with Matchers {
  "2020 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16ProboscideaVolcanium("/twenty_twentytwo/Day16-ProboscideaVolcanium-input.txt")

    dp.part1Answer should be(1871)
    dp.part2Answer should be(2416)
  }

  "2020 Day 16 - Example #1" should "calculate answers" in {
    val dp = new Day16ProboscideaVolcanium("/twenty_twentytwo/Day16-ProboscideaVolcanium-example#1.txt")

    dp.part1Answer should be(1651)
    dp.part2Answer should be(1707)
  }
}


