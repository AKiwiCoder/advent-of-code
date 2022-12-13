package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day13DistressSignalTest extends FlatSpec with Matchers {
  "2020 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13DistressSignal("/twenty_twentytwo/Day13-DistressSignal-input.txt")

    dp.part1Answer should be(6478)
    dp.part2Answer should be(21922)
  }

  "2020 Day 13 - Example #1" should "calculate answers" in {
    val dp = new Day13DistressSignal("/twenty_twentytwo/Day13-DistressSignal-example#1.txt")

    dp.part1Answer should be(13)
    dp.part2Answer should be(140)
  }
}


