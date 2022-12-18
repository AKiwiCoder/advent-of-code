package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day18BoilingBouldersTest extends FlatSpec with Matchers {
  "2020 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18BoilingBoulders("/twenty_twentytwo/Day18-BoilingBoulders-input.txt")

    dp.part1Answer should be(4332)
    dp.part2Answer should be(2524)
  }

  "2020 Day 18 - Example #1" should "calculate answers" in {
    val dp = new Day18BoilingBoulders("/twenty_twentytwo/Day18-BoilingBoulders-example#1.txt")

    dp.part1Answer should be(64)
    dp.part2Answer should be(58)
  }
}


