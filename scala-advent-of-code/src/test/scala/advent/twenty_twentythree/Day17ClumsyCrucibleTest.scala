package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day17ClumsyCrucibleTest extends FlatSpec with Matchers {
  "2023 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17ClumsyCrucible("/twenty_twentythree/Day17-ClumsyCrucible-input.txt")

    dp.part1Answer should be(907)
    dp.part2Answer should be(1057)
  }

  "2023 Day 17 - Example #1" should "calculate answers" in {
    val dp = new Day17ClumsyCrucible("/twenty_twentythree/Day17-ClumsyCrucible-example#1.txt")

    dp.part1Answer should be(102)
    dp.part2Answer should be(94)
  }

  "2023 Day 17 - Example #2" should "calculate answers" in {
    val dp = new Day17ClumsyCrucible("/twenty_twentythree/Day17-ClumsyCrucible-example#2.txt")

    dp.part1Answer should be(59)
    dp.part2Answer should be(71)
  }
}


