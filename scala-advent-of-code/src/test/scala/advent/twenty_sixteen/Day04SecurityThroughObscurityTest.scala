package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day04SecurityThroughObscurityTest  extends FlatSpec with Matchers {
  "2016 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
