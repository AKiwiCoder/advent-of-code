package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day02BathroomSecurityTest extends FlatSpec with Matchers {
  "2016 Day 2 - Input" should "calculate answers" in {
    val dp = new Day02BathroomSecurity("/twenty_sixteen/Day02-BathroomSecurity-input.txt")

    dp.part1Answer should be("14894")
    dp.part2Answer should be("26B96")
  }

  "2016 Day 2 - Example #1" should "calculate answers" in {
    val dp = new Day02BathroomSecurity("/twenty_sixteen/Day02-BathroomSecurity-example#1.txt")

    dp.part1Answer should be("1985")
    dp.part2Answer should be("5DB3")
  }
}
