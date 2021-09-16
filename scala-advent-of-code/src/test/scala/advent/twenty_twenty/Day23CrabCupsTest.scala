package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day23CrabCupsTest extends FlatSpec with Matchers {
  "2020 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23CrabCups("158937462")

    dp.part1Answer should be("69473825")
    dp.part2Answer should be(96604396189L)
  }

  "2020 Day 23 - Example #1" should "calculate answers" in {
    val dp = new Day23CrabCups("389125467")

    dp.part1Answer should be("67384529")
    dp.part2Answer should be(149245887792L)
  }
}


