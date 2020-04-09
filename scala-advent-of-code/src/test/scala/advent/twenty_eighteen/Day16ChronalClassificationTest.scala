package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day16ChronalClassificationTest  extends FlatSpec with Matchers {
  "2018 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16ChronalClassification("/twenty_eighteen/Day16-ChronalClassification-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day16ChronalClassification("/twenty_eighteen/Day16-ChronalClassification-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
