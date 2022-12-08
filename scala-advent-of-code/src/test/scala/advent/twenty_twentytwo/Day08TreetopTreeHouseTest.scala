package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day08TreetopTreeHouseTest extends FlatSpec with Matchers {
  "2020 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08TreetopTreeHouse("/twenty_twentytwo/Day08-TreetopTreeHouse-input.txt")

    dp.part1Answer should be(1533)
    dp.part2Answer should be(345744)
  }

  "2020 Day 08 - Example #1" should "calculate answers" in {
    val dp = new Day08TreetopTreeHouse("/twenty_twentytwo/Day08-TreetopTreeHouse-example#1.txt")

    dp.part1Answer should be(21)
    dp.part2Answer should be(8)
  }
}


