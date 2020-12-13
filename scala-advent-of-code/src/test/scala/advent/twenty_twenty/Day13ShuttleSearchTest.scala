package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day13ShuttleSearchTest extends FlatSpec with Matchers {
  "2020 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13ShuttleSearch("/twenty_twenty/Day13-ShuttleSearch-input.txt", 100000000000000l)

    dp.part1Answer should be(207)
    dp.part2Answer should be(530015546283687l)
  }

  "2020 Day 13 - Example #1" should "calculate answers" in {
    val dp = new Day13ShuttleSearch("/twenty_twenty/Day13-ShuttleSearch-example#1.txt", 1000000)

    dp.part1Answer should be(295)
    dp.part2Answer should be(1068781)
  }
}


