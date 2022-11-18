package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day07TheTreacheryOfWhalesTest extends FlatSpec with Matchers {
  "2020 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07TheTreacheryOfWhales("/twenty_twentyone/Day07-TheTreacheryOfWhales-input.txt")

    dp.part1Answer should be(336701)
    dp.part2Answer should be(95167302)
  }

  "2020 Day 07 - Example #1" should "calculate answers" in {
    val dp = new Day07TheTreacheryOfWhales("/twenty_twentyone/Day07-TheTreacheryOfWhales-example#1.txt")

    dp.part1Answer should be(37)
    dp.part2Answer should be(168)
  }
}


