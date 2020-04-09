package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day18LikeARogueTest  extends FlatSpec with Matchers {
  "2016 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18LikeARogue("/twenty_sixteen/Day18-LikeARogue-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day18LikeARogue("/twenty_sixteen/Day18-LikeARogue-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
