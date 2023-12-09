package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day09MirageMaintenanceTest extends FlatSpec with Matchers {
  "2023 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09MirageMaintenance("/twenty_twentythree/Day09-MirageMaintenance-input.txt")

    dp.part1Answer should be(1584748274)
    dp.part2Answer should be(1026)
  }

  "2023 Day 09 - Example #1" should "calculate answers" in {
    val dp = new Day09MirageMaintenance("/twenty_twentythree/Day09-MirageMaintenance-example#1.txt")

    dp.part1Answer should be(114)
    dp.part2Answer should be(2)
  }
}


