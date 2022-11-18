package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day02DiveTest extends FlatSpec with Matchers {
  "2020 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02Dive("/twenty_twentyone/Day02-Dive-input.txt")

    dp.part1Answer should be(2070300)
    dp.part2Answer should be(2078985210)
  }

  "2020 Day 02 - Example #1" should "calculate answers" in {
    val dp = new Day02Dive("/twenty_twentyone/Day02-Dive-example#1.txt")

    dp.part1Answer should be(150)
    dp.part2Answer should be(900)
  }
}


