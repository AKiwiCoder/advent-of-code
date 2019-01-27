package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day03PerfectlySphericalHousesInAVacuumTest extends FlatSpec with Matchers {
  "2015 Day 3 - Input" should "calculate answers" in {
    val dp = new Day03PerfectlySpericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-input.txt")

    dp.part1Answer should be(2572)
    dp.part2Answer should be(2631)
  }

  "2015 Day 3 - Example #1" should "calculate answers" in {
    val dp = new Day03PerfectlySpericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(3)
  }

  "2015 Day 3 - Example #2" should "calculate answers" in {
    val dp = new Day03PerfectlySpericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#2.txt")

    dp.part1Answer should be(4)
    dp.part2Answer should be(3)
  }

  "2015 Day 3 - Example #3" should "calculate answers" in {
    val dp = new Day03PerfectlySpericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#3.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(11)
  }
}