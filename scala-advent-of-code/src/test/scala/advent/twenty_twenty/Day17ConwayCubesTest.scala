package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day17ConwayCubesTest extends FlatSpec with Matchers {
  "2020 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17ConwayCubes("/twenty_twenty/Day17-ConwayCubes-input.txt")

    dp.part1Answer should be(263)
    dp.part2Answer should be(1680)
  }

  "2020 Day 17 - Example #1" should "calculate answers" in {
    val dp = new Day17ConwayCubes("/twenty_twenty/Day17-ConwayCubes-example#1.txt")

    dp.part1Answer should be(112)
    dp.part2Answer should be(848)
  }
}


