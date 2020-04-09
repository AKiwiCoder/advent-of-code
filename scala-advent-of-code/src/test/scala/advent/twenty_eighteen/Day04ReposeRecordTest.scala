package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day04ReposeRecordTest  extends FlatSpec with Matchers {
  "2018 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04ReposeRecord("/twenty_eighteen/Day04-ReposeRecord-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day04ReposeRecord("/twenty_eighteen/Day04-ReposeRecord-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
