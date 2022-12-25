package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day25FullOfHotAirTest extends FlatSpec with Matchers {
  "2020 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25FullOfHotAir("/twenty_twentytwo/Day25-FullOfHotAir-input.txt")

    dp.part1Answer should be("122-12==0-01=00-0=02")
    dp.part2Answer should be("")
  }

  "2020 Day 25 - Example #1" should "calculate answers" in {
    val dp = new Day25FullOfHotAir("/twenty_twentytwo/Day25-FullOfHotAir-example#1.txt")

    dp.part1Answer should be("2=-1=0")
    dp.part2Answer should be("")
  }
}


