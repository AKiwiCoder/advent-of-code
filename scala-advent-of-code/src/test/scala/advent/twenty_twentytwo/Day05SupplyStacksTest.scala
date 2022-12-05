package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day05SupplyStacksTest extends FlatSpec with Matchers {
  "2020 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05SupplyStacks("/twenty_twentytwo/Day05-SupplyStacks-input.txt")

    dp.part1Answer should be("FZCMJCRHZ")
    dp.part2Answer should be("")
  }

  "2020 Day 05 - Example #1" should "calculate answers" in {
    val dp = new Day05SupplyStacks("/twenty_twentytwo/Day05-SupplyStacks-example#1.txt")

    dp.part1Answer should be("CMZ")
    dp.part2Answer should be("MCD")
  }
}


