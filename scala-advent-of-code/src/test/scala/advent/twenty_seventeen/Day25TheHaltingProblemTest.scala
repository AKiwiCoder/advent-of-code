package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day25TheHaltingProblemTest  extends FlatSpec with Matchers {
  "2017 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25TheHaltingProblem("/twenty_seventeen/Day25-TheHaltingProblem-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day25TheHaltingProblem("/twenty_seventeen/Day25-TheHaltingProblem-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
