package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day17NoSuchThingAsTooMuchTest extends FlatSpec with Matchers {
  "2015 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17NoSuchThingAsTooMuch("/twenty_fifteen/Day17-NoSuchThingAsTooMuch-input.txt", 150)

    dp.part1Answer should be(1304)
    dp.part2Answer should be(18)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day17NoSuchThingAsTooMuch("/twenty_fifteen/Day17-NoSuchThingAsTooMuch-example#1.txt", 25)

    dp.part1Answer should be(4)
    dp.part2Answer should be(3)
  }
}
