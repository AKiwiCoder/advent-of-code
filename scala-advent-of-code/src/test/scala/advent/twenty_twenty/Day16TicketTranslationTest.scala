package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day16TicketTranslationTest extends FlatSpec with Matchers {
  "2020 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16TicketTranslation("/twenty_twenty/Day16-TicketTranslation-input.txt")

    dp.part1Answer should be(23122)
    dp.part2Answer should be(362974212989l)
  }

  "2020 Day 16 - Example #1" should "calculate answers" in {
    val dp = new Day16TicketTranslation("/twenty_twenty/Day16-TicketTranslation-example#1.txt")

    dp.part1Answer should be(71)
    dp.part2Answer should be(1)
  }
}


