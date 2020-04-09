package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day14DiskDefragmentationTest  extends FlatSpec with Matchers {
  "2017 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14DiskDefragmentation("/twenty_seventeen/Day14-DiskDefragmentation-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day14DiskDefragmentation("/twenty_seventeen/Day14-DiskDefragmentation-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
