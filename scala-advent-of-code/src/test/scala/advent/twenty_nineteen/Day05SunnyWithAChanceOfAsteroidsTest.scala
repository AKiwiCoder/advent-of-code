package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day05SunnyWithAChanceOfAsteroidsTest extends FlatSpec with Matchers {
  "2019 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05SunnyWithAChanceOfAsteroids("/twenty_nineteen/Day05-SunnyWithAChanceOfAsteroids-input.txt")

    dp.part1Answer should be(10987514)
    dp.part2Answer should be(14195011)
  }

  "2019 Day 05 - Example #1" should "calculate answers" in {
    val dp = new Day05SunnyWithAChanceOfAsteroids("/twenty_nineteen/Day05-SunnyWithAChanceOfAsteroids-example#1.txt")

    dp.part1Answer should be(1)
    dp.part2Answer should be(5)
  }
}


