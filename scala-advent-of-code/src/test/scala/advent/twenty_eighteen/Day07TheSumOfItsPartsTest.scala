package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day07TheSumOfItsPartsTest  extends FlatSpec with Matchers {
  "2018 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07TheSumOfItsParts("/twenty_eighteen/Day07-TheSumOfItsParts-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day07TheSumOfItsParts("/twenty_eighteen/Day07-TheSumOfItsParts-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
