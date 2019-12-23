package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day23CategorySixTest extends FlatSpec with Matchers {
  "2019 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23CategorySix("/twenty_nineteen/Day23-CategorySix-input.txt")

    dp.part1Answer should be(17283)
    dp.part2Answer should be(11319)
  }
}


