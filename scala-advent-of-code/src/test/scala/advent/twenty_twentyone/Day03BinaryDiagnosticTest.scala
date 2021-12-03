package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day03BinaryDiagnosticTest extends FlatSpec with Matchers {
  "2020 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03BinaryDiagnostic("/twenty_twentyone/Day03-BinaryDiagnostic-input.txt")

    dp.part1Answer should be(3985686)
    dp.part2Answer should be(2555739)
  }

  "2020 Day 03 - Example #1" should "calculate answers" in {
    val dp = new Day03BinaryDiagnostic("/twenty_twentyone/Day03-BinaryDiagnostic-example#1.txt")

    dp.part1Answer should be(198)
    dp.part2Answer should be(230)
  }
}


