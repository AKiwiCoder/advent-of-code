package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day05DoesntHeHaveInternElvesForThisTest extends FlatSpec with Matchers {
  "2015 Day 3 - Input" should "calculate answers" in {
    val dp = new Day05DoesntHeHaveInternElvesForThis("/twenty_fifteen/Day05-DoesntHeHaveInternElvesForThis-input.txt")
    dp.part1Answer should be(258)
    dp.part2Answer should be(53)
  }

  "2015 Day 3 - Example #1" should "calculate answers" in {
    val dp = new Day05DoesntHeHaveInternElvesForThis("/twenty_fifteen/Day05-DoesntHeHaveInternElvesForThis-example#1.txt")
    dp.part1Answer should be(2)
    dp.part2Answer should be(2)
  }

}
