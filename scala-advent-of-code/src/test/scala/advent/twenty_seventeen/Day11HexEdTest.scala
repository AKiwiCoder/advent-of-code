package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day11HexEdTest  extends FlatSpec with Matchers {
  "2017 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
