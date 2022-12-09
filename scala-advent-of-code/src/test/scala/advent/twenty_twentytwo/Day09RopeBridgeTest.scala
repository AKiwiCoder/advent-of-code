package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day09RopeBridgeTest extends FlatSpec with Matchers {
  "2020 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09RopeBridge("/twenty_twentytwo/Day09-RopeBridge-input.txt")

    dp.part1Answer should be(5877)
    dp.part2Answer should be(2405)
  }

  "2020 Day 09 - Example #1" should "calculate answers" in {
    val dp = new Day09RopeBridge("/twenty_twentytwo/Day09-RopeBridge-example#1.txt")

    dp.part1Answer should be(13)
    dp.part2Answer should be(1)
  }

  "2020 Day 09 - Example #2" should "calculate answers" in {
    val dp = new Day09RopeBridge("/twenty_twentytwo/Day09-RopeBridge-example#2.txt")

    dp.part1Answer should be(87)
    dp.part2Answer should be(36)
  }
}


