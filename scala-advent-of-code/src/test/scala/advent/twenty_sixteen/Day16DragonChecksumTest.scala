package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day16DragonChecksumTest  extends FlatSpec with Matchers {
  "2016 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16DragonChecksum("/twenty_sixteen/Day16-DragonChecksum-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day16DragonChecksum("/twenty_sixteen/Day16-DragonChecksum-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
