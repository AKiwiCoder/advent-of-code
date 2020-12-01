package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day01ReportRepairTest extends FlatSpec with Matchers {
  "2020 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01ReportRepair("/twenty_twenty/Day01-ReportRepair-input.txt")

    dp.part1Answer should be(538464)
    dp.part2Answer should be(278783190)
  }

  "2020 Day 01 - Example #1" should "calculate answers" in {
    val dp = new Day01ReportRepair("/twenty_twenty/Day01-ReportRepair-example#1.txt")

    dp.part1Answer should be(514579)
    dp.part2Answer should be(241861950)
  }
}


