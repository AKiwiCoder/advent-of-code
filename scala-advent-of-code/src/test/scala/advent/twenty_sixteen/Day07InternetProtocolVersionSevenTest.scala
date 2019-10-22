package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day07InternetProtocolVersionSevenTest extends FlatSpec with Matchers {
  "2016 Day 7 - Input" should "calculate answers" in {
    val dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-input.txt")

    dp.part1Answer should be(110)
    dp.part2Answer should be(242)
  }

  "2016 Day 7 - Example #1" should "calculate answers" in {
    val dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(0)
  }

  "2016 Day 7 - Example #2" should "calculate answers" in {
    val dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-example#2.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(3)
  }
}