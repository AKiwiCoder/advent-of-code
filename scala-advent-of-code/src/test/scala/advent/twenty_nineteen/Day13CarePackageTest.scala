package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day13CarePackageTest extends FlatSpec with Matchers {
  "2019 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13CarePackage("/twenty_nineteen/Day13-CarePackage-input.txt")

    dp.part1Answer should be(363)
    dp.part2Answer should be(17159)
  }
}


