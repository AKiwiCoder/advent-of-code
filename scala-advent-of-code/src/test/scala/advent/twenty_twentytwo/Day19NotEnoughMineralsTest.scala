package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day19NotEnoughMineralsTest extends FlatSpec with Matchers {
  "2020 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19NotEnoughMinerals("/twenty_twentytwo/Day19-NotEnoughMinerals-input.txt")

    dp.part1Answer should be(994)
    dp.part2Answer should be(15960)
  }

  "2020 Day 19 - Example #1" should "calculate answers" in {
    val dp = new Day19NotEnoughMinerals("/twenty_twentytwo/Day19-NotEnoughMinerals-example#1.txt")

    dp.part1Answer should be(33)
    dp.part2Answer should be(3472)
  }
}


