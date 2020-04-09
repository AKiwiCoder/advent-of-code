package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day13PacketScannersTest  extends FlatSpec with Matchers {
  "2017 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13PacketScanners("/twenty_seventeen/Day13-PacketScanners-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day13PacketScanners("/twenty_seventeen/Day13-PacketScanners-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
