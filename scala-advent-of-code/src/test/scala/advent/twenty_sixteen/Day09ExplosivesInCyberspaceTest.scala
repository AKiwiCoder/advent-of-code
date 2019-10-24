package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day09ExplosivesInCyberspaceTest extends FlatSpec with Matchers {
  "2016 Day 9 - Input" should "calculate answers" in {
    val dp = new Day09ExplosivesInCyberspace("/twenty_sixteen/Day09-ExplosivesInCyberspace-input.txt")

    dp.part1Answer should be(110)
    dp.part2Answer should be(242)
  }

  "2016 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day09ExplosivesInCyberspace("/twenty_sixteen/Day09-ExplosivesInCyberspace-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(0)
  }

}
