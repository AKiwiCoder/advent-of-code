package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day05BinaryBoardingTest extends FlatSpec with Matchers {
  "2020 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05BinaryBoarding("/twenty_twenty/Day05-BinaryBoarding-input.txt")

    dp.part1Answer should be(915)
    dp.part2Answer should be(699)
  }

  "2020 Day 05 - Example #1" should "calculate answers" in {
    val dp = new Day05BinaryBoarding("/twenty_twenty/Day05-BinaryBoarding-example#1.txt")

    dp.part1Answer should be(820)
    dp.part2Answer should be(120)
  }
}


