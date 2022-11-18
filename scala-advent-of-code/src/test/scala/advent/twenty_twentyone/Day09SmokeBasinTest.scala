package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day09SmokeBasinTest extends FlatSpec with Matchers {
  "2020 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09SmokeBasin("/twenty_twentyone/Day09-SmokeBasin-input.txt")

    dp.part1Answer should be(423)
    dp.part2Answer should be(1198704)
  }

  "2020 Day 09 - Example #1" should "calculate answers" in {
    val dp = new Day09SmokeBasin("/twenty_twentyone/Day09-SmokeBasin-example#1.txt")

    dp.part1Answer should be(15)
    dp.part2Answer should be(1134)
  }
}


