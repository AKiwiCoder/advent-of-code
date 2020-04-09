package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day20FirewallRulesTest  extends FlatSpec with Matchers {
  "2016 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20FirewallRules("/twenty_sixteen/Day20-FirewallRules-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day20FirewallRules("/twenty_sixteen/Day20-FirewallRules-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
