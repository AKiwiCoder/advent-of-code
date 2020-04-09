package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day09MarbleManiaTest  extends FlatSpec with Matchers {
  "2018 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
