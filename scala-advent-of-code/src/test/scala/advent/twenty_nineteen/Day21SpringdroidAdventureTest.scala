package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day21SpringdroidAdventureTest extends FlatSpec with Matchers {
  "2019 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21SpringdroidAdventure("/twenty_nineteen/Day21-SpringdroidAdventure-input.txt")

    dp.part1Answer should be(19352720)
    dp.part2Answer should be(1143652885)
  }
}


