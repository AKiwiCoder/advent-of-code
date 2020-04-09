package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day03SquaresWithThreeSidesTest  extends FlatSpec with Matchers {
  "2016 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03SquaresWithThreeSides("/twenty_sixteen/Day03-SquaresWithThreeSides-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day03SquaresWithThreeSides("/twenty_sixteen/Day03-SquaresWithThreeSides-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
