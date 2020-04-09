package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day22ModeMazeTest  extends FlatSpec with Matchers {
  "2018 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22ModeMaze("/twenty_eighteen/Day22-ModeMaze-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day22ModeMaze("/twenty_eighteen/Day22-ModeMaze-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
