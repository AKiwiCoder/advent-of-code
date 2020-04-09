package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day02InventoryManagementSystemTest  extends FlatSpec with Matchers {
  "2018 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02InventoryManagementSystem("/twenty_eighteen/Day02-InventoryManagementSystem-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day02InventoryManagementSystem("/twenty_eighteen/Day02-InventoryManagementSystem-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
