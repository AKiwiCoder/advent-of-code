package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day16FlawedFrequencyTransmissionTest extends FlatSpec with Matchers {
  "2019 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-input.txt", 100)

    dp.part1Answer should be("27229269")
    dp.part2Answer should be("26857164")
  }

  "2019 Day 16 - Example #1" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#1.txt", 4)

    dp.part1Answer should be("01029498")
    dp.part2Answer should be("00001616")
  }

  "2019 Day 16 - Example #2" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#2.txt", 100)

    dp.part1Answer should be("24176176")
    dp.part2Answer should be("00000000")
  }

  "2019 Day 16 - Example #3" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#3.txt", 100)

    dp.part1Answer should be("73745418")
    dp.part2Answer should be("00000000")
  }

  "2019 Day 16 - Example #4" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#4.txt", 100)

    dp.part1Answer should be("52432133")
    dp.part2Answer should be("00000000")
  }

  "2019 Day 16 - Example #5" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#5.txt", 100)

    dp.part1Answer should be("24465799")
    dp.part2Answer should be("84462026")
  }

  "2019 Day 16 - Example #6" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#6.txt", 100)

    dp.part1Answer should be("82441489")
    dp.part2Answer should be("78725270")
  }

  "2019 Day 16 - Example #7" should "calculate answers" in {
    val dp = new Day16FlawedFrequencyTransmission("/twenty_nineteen/Day16-FlawedFrequencyTransmission-example#7.txt", 100)

    dp.part1Answer should be("52486276")
    dp.part2Answer should be("53553731")
  }
}


