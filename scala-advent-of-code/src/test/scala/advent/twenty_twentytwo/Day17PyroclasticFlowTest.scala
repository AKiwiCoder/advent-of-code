package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day17PyroclasticFlowTest extends FlatSpec with Matchers {
  "2020 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17PyroclasticFlow("/twenty_twentytwo/Day17-PyroclasticFlow-input.txt")

    dp.part1Answer should be(3055)
//    dp.part2Answer should be(1507692307690L)
  }

  "2020 Day 17 - Example #1" should "calculate answers" in {
    val dp = new Day17PyroclasticFlow("/twenty_twentytwo/Day17-PyroclasticFlow-example#1.txt")

    dp.part1Answer should be(3068)
//    dp.part2Answer should be(1514285714288L)
  }
}


