package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day03RucksackReorganizatonTest extends FlatSpec with Matchers {
  "2020 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03RucksackReorganization("/twenty_twentytwo/Day03-RucksackReorganization-input.txt")

    dp.part1Answer should be(8039)
    dp.part2Answer should be(2510)
  }

  "2020 Day 03 - Example #1" should "calculate answers" in {
    val dp = new Day03RucksackReorganization("/twenty_twentytwo/Day03-RucksackReorganization-example#1.txt")

    dp.part1Answer should be(157)
    dp.part2Answer should be(70)
  }
}


