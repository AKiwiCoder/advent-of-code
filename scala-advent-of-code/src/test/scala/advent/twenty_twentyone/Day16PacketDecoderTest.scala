package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day16PacketDecoderTest extends FlatSpec with Matchers {
  "2020 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-input.txt")

    dp.part1Answer should be(891)
    dp.part2Answer should be(673042777597L)
  }

  "2020 Day 16 - Example #1" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#1.txt")

    dp.part1Answer should be(6)
    dp.part2Answer should be(2021)
  }

  "2020 Day 16 - Example #2" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#2.txt")

    dp.part1Answer should be(14)
    dp.part2Answer should be(3)
  }

  "2020 Day 16 - Example #3" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#3.txt")

    dp.part1Answer should be(16)
    dp.part2Answer should be(15)
  }

  "2020 Day 16 - Example #4" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#4.txt")

    dp.part1Answer should be(12)
    dp.part2Answer should be(46)
  }

  "2020 Day 16 - Example #5" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#5.txt")

    dp.part1Answer should be(23)
    dp.part2Answer should be(46)
  }

  "2020 Day 16 - Example #6" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#6.txt")

    dp.part1Answer should be(31)
    dp.part2Answer should be(54)
  }

  "2020 Day 16 - Example #7" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#7.txt")

    dp.part1Answer should be(14)
    dp.part2Answer should be(3)
  }

  "2020 Day 16 - Example #8" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#8.txt")

    dp.part1Answer should be(8)
    dp.part2Answer should be(54)
  }

  "2020 Day 16 - Example #9" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#9.txt")

    dp.part1Answer should be(15)
    dp.part2Answer should be(7)
  }

  "2020 Day 16 - Example #10" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#10.txt")

    dp.part1Answer should be(11)
    dp.part2Answer should be(9)
  }

  "2020 Day 16 - Example #11" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#11.txt")

    dp.part1Answer should be(13)
    dp.part2Answer should be(1)
  }

  "2020 Day 16 - Example #12" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#12.txt")

    dp.part1Answer should be(19)
    dp.part2Answer should be(0)
  }

  "2020 Day 16 - Example #13" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#13.txt")

    dp.part1Answer should be(16)
    dp.part2Answer should be(0)
  }

  "2020 Day 16 - Example #14" should "calculate answers" in {
    val dp = new Day16PacketDecoder("/twenty_twentyone/Day16-PacketDecoder-example#14.txt")

    dp.part1Answer should be(20)
    dp.part2Answer should be(1)
  }
}


