package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day06ChronalCoordinatesTest  extends FlatSpec with Matchers {
  "2018 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06ChronalCoordinates("/twenty_eighteen/Day06-ChronalCoordinates-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day06ChronalCoordinates("/twenty_eighteen/Day06-ChronalCoordinates-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
