package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day05IfYouGiveASeedAFertilizerTest extends FlatSpec with Matchers {
  "2023 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05IfYouGiveASeedAFertilizer("/twenty_twentythree/Day05-IfYouGiveASeedAFertilizer-input.txt")

    dp.part1Answer should be(107430936)
    dp.part2Answer should be(23738616)
  }

  "2023 Day 05 - Example #1" should "calculate answers" in {
    val dp = new Day05IfYouGiveASeedAFertilizer("/twenty_twentythree/Day05-IfYouGiveASeedAFertilizer-example#1.txt")

    dp.part1Answer should be(35)
    dp.part2Answer should be(46)
  }
}


