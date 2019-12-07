package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day07AmplificationCircuitTest extends FlatSpec with Matchers {
  "2019 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-input.txt")

    dp.part1Answer should be(13848)
    dp.part2Answer should be(12932154)
  }

  "2019 Day 07 - Example #1" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-example#1.txt")

    dp.part1Answer should be(43210)
    dp.part2Answer should be(98765)
  }

  "2019 Day 07 - Example #2" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-example#2.txt")

    dp.part1Answer should be(-26)
    dp.part2Answer should be(139629729)
  }


  "2019 Day 07 - Example #3" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-example#3.txt")

    dp.part1Answer should be(54321)
    dp.part2Answer should be(-1234)
  }


  "2019 Day 07 - Example #4" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-example#4.txt")

    dp.part1Answer should be(65210)
    dp.part2Answer should be(76543)
  }

  "2019 Day 07 - Example #5" should "calculate answers" in {
    val dp = new Day07AmplificationCircuit("/twenty_nineteen/Day07-AmplificationCircuit-example#5.txt")

    dp.part1Answer should be(20)
    dp.part2Answer should be(18216)
  }
}


