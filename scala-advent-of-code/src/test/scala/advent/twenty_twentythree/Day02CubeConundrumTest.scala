package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day02CubeConundrumTest extends FlatSpec with Matchers {
  "2023 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02CubeConundrum("/twenty_twentythree/Day02-CubeConundrum-input.txt")

    dp.part1Answer should be(2771)
    dp.part2Answer should be(70924)
  }

  "2023 Day 02 - Example #1" should "calculate answers" in {
    val dp = new Day02CubeConundrum("/twenty_twentythree/Day02-CubeConundrum-example#1.txt")

    dp.part1Answer should be(8)
    dp.part2Answer should be(2286)
  }
}


