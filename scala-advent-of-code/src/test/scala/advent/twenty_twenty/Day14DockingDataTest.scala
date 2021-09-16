package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day14DockingDataTest extends FlatSpec with Matchers {
  "2020 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14DockingData("/twenty_twenty/Day14-DockingData-input.txt")

    dp.part1Answer should be(15172047086292l)
    dp.part2Answer should be(4197941339968l)
  }

  "2020 Day 14 - Example #1" should "calculate answers" in {
    val dp = new Day14DockingData("/twenty_twenty/Day14-DockingData-example#1.txt", true)

    dp.part1Answer should be(165)
    dp.part2Answer should be(0)
  }

  "2020 Day 14 - Example #2" should "calculate answers" in {
    val dp = new Day14DockingData("/twenty_twenty/Day14-DockingData-example#2.txt")

    dp.part1Answer should be(51)
    dp.part2Answer should be(208)
  }
}


