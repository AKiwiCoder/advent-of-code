package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day20InfiniteElvesAndInfiniteHousesTest  extends FlatSpec with Matchers {
  "2015 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20InfiniteElvesAndInfiniteHouses(29000000)
    dp.part1Answer should be(665280)
    dp.part2Answer should be(705600)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day20InfiniteElvesAndInfiniteHouses(120)
    dp.part1Answer should be(6)
    dp.part2Answer should be(6)
  }
}
