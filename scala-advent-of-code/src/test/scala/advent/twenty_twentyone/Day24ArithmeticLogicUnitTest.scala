package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day24ArithmeticLogicUnitTest extends FlatSpec with Matchers {
  "2020 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24ArithmeticLogicUnit()

    dp.part1Answer should be(79997391969649L)
    dp.part2Answer should be(16931171414113L)
  }
}


