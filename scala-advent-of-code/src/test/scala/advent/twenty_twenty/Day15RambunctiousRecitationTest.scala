package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day15RambunctiousRecitationTest extends FlatSpec with Matchers {
  "2020 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15RambunctiousRecitation("/twenty_twenty/Day15-RambunctiousRecitation-input.txt")

    dp.part1Answer should be(1428)
    dp.part2Answer should be(3718541)
  }

  "2020 Day 15 - Example #1" should "calculate answers" in {
    val dp = new Day15RambunctiousRecitation("/twenty_twenty/Day15-RambunctiousRecitation-example#1.txt")

    dp.part1Answer should be(436)
    dp.part2Answer should be(175594)
  }
}


