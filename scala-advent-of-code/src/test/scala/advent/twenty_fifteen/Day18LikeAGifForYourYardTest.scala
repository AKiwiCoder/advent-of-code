package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day18LikeAGifForYourYardTest extends FlatSpec with Matchers {
  "2015 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18LikeAGifForYourYard("/twenty_fifteen/Day18-LikeAGifForYourYard-input.txt", 100)

    dp.part1Answer should be(814)
    dp.part2Answer should be(924)
  }

  "2015 Day 18 - Example #1 (Part 1)" should "calculate answers" in {
    val dp = new Day18LikeAGifForYourYard("/twenty_fifteen/Day18-LikeAGifForYourYard-example#1.txt", 4)

    dp.part1Answer should be(4)
    dp.part2Answer should be(14)
  }

  "2015 Day 18 - Example #1 (Part 2)" should "calculate answers" in {
    val dp = new Day18LikeAGifForYourYard("/twenty_fifteen/Day18-LikeAGifForYourYard-example#1.txt", 5)

    dp.part1Answer should be(4)
    dp.part2Answer should be(17)
  }
}
