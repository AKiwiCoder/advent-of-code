package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day24ImmuneSystemSimulator20XXTest  extends FlatSpec with Matchers {
  "2018 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24ImmuneSystemSimulator20XX("/twenty_eighteen/Day24-ImmuneSystemSimulator20XX-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day24ImmuneSystemSimulator20XX("/twenty_eighteen/Day24-ImmuneSystemSimulator20XX-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
