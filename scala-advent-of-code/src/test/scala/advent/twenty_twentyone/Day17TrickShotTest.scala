package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day17TrickShotTest extends FlatSpec with Matchers {
  "2020 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17TrickShot("/twenty_twentyone/Day17-TrickShot-input.txt")

    dp.part1Answer should be(5460)
    dp.part2Answer should be(3618)
  }

  "2020 Day 17 - Example #1" should "calculate answers" in {
    val dp = new Day17TrickShot("/twenty_twentyone/Day17-TrickShot-example#1.txt")

    dp.part1Answer should be(45)
    dp.part2Answer should be(112)
  }
}


