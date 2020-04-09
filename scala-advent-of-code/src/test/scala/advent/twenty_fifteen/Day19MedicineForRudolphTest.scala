package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day19MedicineForRudolphTest  extends FlatSpec with Matchers {
  "2015 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-input.txt")
    dp.part1Answer should be(518)
    dp.part2Answer should be(200)
  }

  "2015 Day 19 - Example #1" should "calculate answers" in {
    val dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-example#1.txt")
    dp.part1Answer should be(4)
    dp.part2Answer should be(3)
  }

  "2015 Day 19 - Example #2" should "calculate answers" in {
    val dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-example#2.txt")
    dp.part1Answer should be(7)
    dp.part2Answer should be(6)
  }
}
