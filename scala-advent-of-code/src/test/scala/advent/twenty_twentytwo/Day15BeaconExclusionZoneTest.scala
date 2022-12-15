package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day15BeaconExclusionZoneTest extends FlatSpec with Matchers {
  "2020 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15BeaconExclusionZone("/twenty_twentytwo/Day15-BeaconExclusionZone-input.txt", 2000000, 0, 4000000, 3139100, 3139150)

    dp.part1Answer should be(4873353)
    dp.part2Answer should be(11600823139120L)
  }

  "2020 Day 15 - Example #1" should "calculate answers" in {
    val dp = new Day15BeaconExclusionZone("/twenty_twentytwo/Day15-BeaconExclusionZone-example#1.txt", 10, 0, 20, 0, 20)

    dp.part1Answer should be(26)
    dp.part2Answer should be(56000011L)
  }
}


