package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day25FourDimensionalAdventureTest  extends FlatSpec with Matchers {
  "2018 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
