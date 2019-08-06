package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day25LetItSnowTest  extends FlatSpec with Matchers {
  "2015 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25LetItSnow("/twenty_fifteen/Day25-LetItSnow-input.txt")
    dp.part1Answer should be(8997277)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day25LetItSnow("/twenty_fifteen/Day25-LetItSnow-example#1.txt")
    dp.part1Answer should be(33511524)
  }
}
