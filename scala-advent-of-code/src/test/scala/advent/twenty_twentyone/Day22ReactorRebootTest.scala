package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day22ReactorRebootTest extends FlatSpec with Matchers {
  "2020 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22ReactorReboot("/twenty_twentyone/Day22-ReactorReboot-input.txt")

    dp.part1Answer should be(611176)
    dp.part2Answer should be(1201259791805392L)
  }

  "2020 Day 22 - Example #1" should "calculate answers" in {
    val dp = new Day22ReactorReboot("/twenty_twentyone/Day22-ReactorReboot-example#1.txt")

    dp.part1Answer should be(39)
    dp.part2Answer should be(39)
  }

  "2020 Day 22 - Example #2" should "calculate answers" in {
    val dp = new Day22ReactorReboot("/twenty_twentyone/Day22-ReactorReboot-example#2.txt")

    dp.part1Answer should be(590784)
    dp.part2Answer should be(39769202357779L)
  }

  "2020 Day 22 - Example #3" should "calculate answers" in {
    val dp = new Day22ReactorReboot("/twenty_twentyone/Day22-ReactorReboot-example#3.txt")

    dp.part1Answer should be(474140)
    dp.part2Answer should be(2758514936282235L)
  }
}


