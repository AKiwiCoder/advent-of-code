package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day03TobogganTrajectoryTest extends FlatSpec with Matchers {
  "2020 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03TobogganTrajectory("/twenty_twenty/Day03-TobogganTrajectory-input.txt")

    dp.part1Answer should be(252)
    dp.part2Answer should be(2608962048l)
  }

  "2020 Day 03 - Example #1" should "calculate answers" in {
    val dp = new Day03TobogganTrajectory("/twenty_twenty/Day03-TobogganTrajectory-example#1.txt")

    dp.part1Answer should be(7)
    dp.part2Answer should be(336)
  }
}


