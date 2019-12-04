package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day04SecureContainerTest extends FlatSpec with Matchers {
  "2019 Day 4 - Input" should "calculate answers" in {
    val dp = new Day04SecureContainer(165432, 707912)
    dp.part1Answer should be(1716)
    dp.part2Answer should be(1163)
  }

  "2019 Day 4 - Example #1" should "calculate answers" in {
    val dp = new Day04SecureContainer(111111, 111111)
    dp.part1Answer should be(1)
    dp.part2Answer should be(0)
  }

  "2019 Day 4 - Example #2" should "calculate answers" in {
    val dp = new Day04SecureContainer(223450, 223450)
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2019 Day 4 - Example #3" should "calculate answers" in {
    val dp = new Day04SecureContainer(123789, 123789)
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2019 Day 4 - Example #4" should "calculate answers" in {
    val dp = new Day04SecureContainer(112233, 112233)
    dp.part1Answer should be(1)
    dp.part2Answer should be(1)
  }

  "2019 Day 4 - Example #5" should "calculate answers" in {
    val dp = new Day04SecureContainer(123444, 123444)
    dp.part1Answer should be(1)
    dp.part2Answer should be(0)
  }

  "2019 Day 4 - Example #6" should "calculate answers" in {
    val dp = new Day04SecureContainer(111122, 111122)
    dp.part1Answer should be(1)
    dp.part2Answer should be(1)
  }
}
