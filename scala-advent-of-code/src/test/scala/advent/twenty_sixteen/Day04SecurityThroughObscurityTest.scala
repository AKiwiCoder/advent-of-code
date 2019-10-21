package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day04SecurityThroughObscurityTest  extends FlatSpec with Matchers {
  "2016 Day 4 - Input" should "calculate answers" in {
    val dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-input.txt")

    dp.part1Answer should be(245102)
    dp.part2Answer should be(324)
  }

  "2016 Day 4 - Example #1" should "calculate answers" in {
    val dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-example#1.txt")

    dp.part1Answer should be(1514)
    dp.part2Answer should be(-1)
  }
}
