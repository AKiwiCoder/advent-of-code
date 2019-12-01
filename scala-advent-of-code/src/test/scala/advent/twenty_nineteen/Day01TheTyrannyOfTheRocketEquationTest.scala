package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day01TheTyrannyOfTheRocketEquationTest extends FlatSpec with Matchers {
  "2019 Day 1 - Input" should "calculate answers" in {
    val dp = new Day01TheTyrannyOfTheRocketEquation("/twenty_nineteen/Day01-TheTyrannyOfTheRocketEquation-input.txt")

    dp.part1Answer should be(3233481)
    dp.part2Answer should be(4847351)
  }

  "2019 Day 1 - Example #1" should "calculate answers" in {
    val dp = new Day01TheTyrannyOfTheRocketEquation("/twenty_nineteen/Day01-TheTyrannyOfTheRocketEquation-example#1.txt")

    dp.part1Answer should be(34241)
    dp.part2Answer should be(51316)
  }

  "2019 Day 2 - Example #1" should "calculate answers" in {
    val dp = new Day01TheTyrannyOfTheRocketEquation("/twenty_nineteen/Day01-TheTyrannyOfTheRocketEquation-example#2.txt")

    dp.part1Answer should be(34239)
    dp.part2Answer should be(51314)
  }
}
