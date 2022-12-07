package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day07NoSpaceLeftOnDeviceTest extends FlatSpec with Matchers {
  "2020 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07NoSpaceLeftOfDevice("/twenty_twentytwo/Day07-NoSpaceLeftOnDevice-input.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2020 Day 07 - Example #1" should "calculate answers" in {
    val dp = new Day07NoSpaceLeftOfDevice("/twenty_twentytwo/Day07-NoSpaceLeftOnDevice-example#1.txt")

    dp.part1Answer should be(95437)
    dp.part2Answer should be(0)
  }
}


