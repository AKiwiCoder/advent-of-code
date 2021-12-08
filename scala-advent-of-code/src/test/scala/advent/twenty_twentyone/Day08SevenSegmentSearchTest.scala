package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day08SevenSegmentSearchTest extends FlatSpec with Matchers {
  "2020 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08SevenSegmentSearch("/twenty_twentyone/Day08-SevenSegmentSearch-input.txt")

    dp.part1Answer should be(301)
    dp.part2Answer should be(908067)
  }

  "2020 Day 08 - Example #1" should "calculate answers" in {
    val dp = new Day08SevenSegmentSearch("/twenty_twentyone/Day08-SevenSegmentSearch-example#1.txt")

    dp.part1Answer should be(26)
    dp.part2Answer should be(61229)
  }
}


