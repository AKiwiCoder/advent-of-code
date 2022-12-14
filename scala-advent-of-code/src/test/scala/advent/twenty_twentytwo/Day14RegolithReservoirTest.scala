package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day14RegolithReservoirTest extends FlatSpec with Matchers {
  "2020 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14RegolithReservoir("/twenty_twentytwo/Day14-RegolithReservoir-input.txt")

    dp.part1Answer should be(799)
    dp.part2Answer should be(29076)
  }

  "2020 Day 14 - Example #1" should "calculate answers" in {
    val dp = new Day14RegolithReservoir("/twenty_twentytwo/Day14-RegolithReservoir-example#1.txt")

    dp.part1Answer should be(24)
    dp.part2Answer should be(93)
  }
}


