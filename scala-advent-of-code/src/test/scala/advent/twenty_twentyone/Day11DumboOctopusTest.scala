package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day11DumboOctopusTest extends FlatSpec with Matchers {
  "2020 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11DumboOctopus("/twenty_twentyone/Day11-DumboOctopus-input.txt", 100)

    dp.part1Answer should be(1649)
    dp.part2Answer should be(256)
  }

  "2020 Day 11 - Example #1" should "calculate answers" in {
    val dp = new Day11DumboOctopus("/twenty_twentyone/Day11-DumboOctopus-example#1.txt", 100)

    dp.part1Answer should be(1656)
    dp.part2Answer should be(195)
  }

  "2020 Day 11 - Example #2" should "calculate answers" in {
    val dp = new Day11DumboOctopus("/twenty_twentyone/Day11-DumboOctopus-example#2.txt", 2)

    dp.part1Answer should be(9)
    dp.part2Answer should be(6)
  }
}


