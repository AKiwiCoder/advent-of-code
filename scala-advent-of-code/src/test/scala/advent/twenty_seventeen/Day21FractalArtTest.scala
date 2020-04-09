package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day21FractalArtTest  extends FlatSpec with Matchers {
  "2017 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21FractalArt("/twenty_seventeen/Day21-FractalArt-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day21FractalArt("/twenty_seventeen/Day21-FractalArt-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
