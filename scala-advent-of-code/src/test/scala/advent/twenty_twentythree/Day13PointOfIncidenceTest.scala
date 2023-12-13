package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day13PointOfIncidenceTest extends FlatSpec with Matchers {
  "2023 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13PointOfIncidence("/twenty_twentythree/Day13-PointOfIncidence-input.txt")

    dp.part1Answer should be(30535)
    dp.part2Answer should be(30844)
  }

  "2023 Day 13 - Example #1" should "calculate answers" in {
    val dp = new Day13PointOfIncidence("/twenty_twentythree/Day13-PointOfIncidence-example#1.txt")

    dp.part1Answer should be(405)
    dp.part2Answer should be(400)
  }
}


