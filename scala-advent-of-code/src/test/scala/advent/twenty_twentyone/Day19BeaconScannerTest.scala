package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day19BeaconScannerTest extends FlatSpec with Matchers {
  "2020 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19BeaconScanner("/twenty_twentyone/Day19-BeaconScanner-input.txt")

    dp.part1Answer should be(467)
    dp.part2Answer should be(12226)
  }

  "2020 Day 19 - Example #1" should "calculate answers" in {
    val dp = new Day19BeaconScanner("/twenty_twentyone/Day19-BeaconScanner-example#1.txt")

    dp.part1Answer should be(79)
    dp.part2Answer should be(3621)
  }
}


