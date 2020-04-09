package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day23ExperimentalEmergencyTeleportationTest  extends FlatSpec with Matchers {
  "2018 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23ExperimentalEmergencyTeleportation("/twenty_eighteen/Day23-ExperimentalEmergencyTeleportation-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day23ExperimentalEmergencyTeleportation("/twenty_eighteen/Day23-ExperimentalEmergencyTeleportation-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
