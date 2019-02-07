package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day13KnightsOfTheDinnerTableTest extends FlatSpec with Matchers {
  "2015 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13KnightsOfTheDinnerTable("/twenty_fifteen/Day13-KnightsOfTheDinnerTable-input.txt")

    dp.part1Answer should be(664)
    dp.part2Answer should be(640)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day13KnightsOfTheDinnerTable("/twenty_fifteen/Day13-KnightsOfTheDinnerTable-example#1.txt")

    dp.part1Answer should be(330)
    dp.part2Answer should be(286)
  }
}
