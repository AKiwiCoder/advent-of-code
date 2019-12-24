package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day24PlanetOfDiscordTest extends FlatSpec with Matchers {
  "2019 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24PlanetOfDiscord("/twenty_nineteen/Day24-PlanetOfDiscord-input.txt", 200)

    dp.part1Answer should be(28778811)
    dp.part2Answer should be(2097)
  }

  "2019 Day 24 - Example #1" should "calculate answers" in {
    val dp = new Day24PlanetOfDiscord("/twenty_nineteen/Day24-PlanetOfDiscord-example#1.txt", 10)

    dp.part1Answer should be(2129920)
    dp.part2Answer should be(99)
  }
}


