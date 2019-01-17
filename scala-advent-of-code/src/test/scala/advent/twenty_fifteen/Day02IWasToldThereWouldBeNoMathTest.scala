package advent.twenty_fifteen

import org.scalatest.{FlatSpec, FunSuite, Matchers}

class Day02IWasToldThereWouldBeNoMathTest  extends FlatSpec with Matchers {
  "2015 Day 2 - Input" should "calculate answers" in {
    val dp = new Day02IWasToldThereWouldBeNoMath("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-input.txt")
    dp.part1Answer should be(1606483)
    dp.part2Answer should be(3842356)
  }

  "2015 Day 2 - Example #1" should "calculate answers" in {
    val dp = new Day02IWasToldThereWouldBeNoMath("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-example#1.txt")
    dp.part1Answer should be(101)
    dp.part2Answer should be(48)
  }
}
