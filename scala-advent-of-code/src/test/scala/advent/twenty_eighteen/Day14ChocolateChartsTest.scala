package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day14ChocolateChartsTest  extends FlatSpec with Matchers {
  "2018 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14ChocolateCharts("/twenty_eighteen/Day14-ChocolateCharts-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day14ChocolateCharts("/twenty_eighteen/Day14-ChocolateCharts-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
