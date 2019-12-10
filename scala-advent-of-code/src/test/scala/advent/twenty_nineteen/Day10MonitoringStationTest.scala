package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day10MonitoringStationTest extends FlatSpec with Matchers {
  "2019 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-input.txt")

    dp.part1Answer should be(269)
    dp.part2Answer should be(612)
  }

  "2019 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#1.txt")

    dp.part1Answer should be(8)
    dp.part2Answer should be(0)
  }

  "2019 Day 10 - Example #2" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#2.txt")

    dp.part1Answer should be(30)
    dp.part2Answer should be(0)
  }

  "2019 Day 10 - Example #3" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#3.txt")

    dp.part1Answer should be(210)
    dp.part2Answer should be(802)
  }

  "2019 Day 10 - Example #4" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#4.txt")

    dp.part1Answer should be(33)
    dp.part2Answer should be(0)
  }

  "2019 Day 10 - Example #5" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#5.txt")

    dp.part1Answer should be(35)
    dp.part2Answer should be(0)
  }

  "2019 Day 10 - Example #6" should "calculate answers" in {
    val dp = new Day10MonitoringStation("/twenty_nineteen/Day10-MonitoringStation-example#6.txt")

    dp.part1Answer should be(41)
    dp.part2Answer should be(0)
  }
}


