package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day15LensLibraryTest extends FlatSpec with Matchers {
  "2023 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15LensLibrary("/twenty_twentythree/Day15-LensLibrary-input.txt")

    dp.part1Answer should be(510273)
    dp.part2Answer should be(212449)
  }

  "2023 Day 15 - Example #1" should "calculate answers" in {
    val dp = new Day15LensLibrary("/twenty_twentythree/Day15-LensLibrary-example#1.txt")

    dp.part1Answer should be(1320)
    dp.part2Answer should be(145)
  }
}


