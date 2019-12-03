package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day03CrossedWiresTest extends FlatSpec with Matchers {
  "2019 Day 3 - Input" should "calculate answers" in {
    val dp = new Day03CrossedWires("/twenty_nineteen/Day03-CrossedWires-input.txt")
    dp.part1Answer should be(308)
    dp.part2Answer should be(12934)
  }

  "2019 Day 3 - Example #1" should "calculate answers" in {
    val dp = new Day03CrossedWires("/twenty_nineteen/Day03-CrossedWires-example#1.txt")
    dp.part1Answer should be(6)
    dp.part2Answer should be(30)
  }

  "2019 Day 3 - Example #2" should "calculate answers" in {
    val dp = new Day03CrossedWires("/twenty_nineteen/Day03-CrossedWires-example#2.txt")
    dp.part1Answer should be(159)
    dp.part2Answer should be(610)
  }

  "2019 Day 3 - Example #3" should "calculate answers" in {
    val dp = new Day03CrossedWires("/twenty_nineteen/Day03-CrossedWires-example#3.txt")
    dp.part1Answer should be(135)
    dp.part2Answer should be(410)
  }
}