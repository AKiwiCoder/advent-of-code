package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day10SyntaxScoringTest extends FlatSpec with Matchers {
  "2020 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10SyntaxScoring("/twenty_twentyone/Day10-SyntaxScoring-input.txt")

    dp.part1Answer should be(388713)
    dp.part2Answer should be(3539961434L)
  }

  "2020 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10SyntaxScoring("/twenty_twentyone/Day10-SyntaxScoring-example#1.txt")

    dp.part1Answer should be(26397)
    dp.part2Answer should be(288957L)
  }
}


