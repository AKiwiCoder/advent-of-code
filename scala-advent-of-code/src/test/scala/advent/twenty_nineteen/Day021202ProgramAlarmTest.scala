package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day021202ProgramAlarmTest extends FlatSpec with Matchers {
  "2019 Day 2 - Input" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-input.txt",  (12, 2),  Option(19690720))
    dp.part1Answer(0) should be(6087827)
    dp.part2Answer should be(5379)
  }

  "2019 Day 2 - Example #1" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-example#1.txt", (9,10), Option.empty)
    dp.part1Answer(0) should be(3500)
  }

  "2019 Day 2 - Example #2" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-example#2.txt", (0,0), Option.empty)
    dp.part1Answer(0) should be(2)
  }

  "2019 Day 2 - Example #3" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-example#3.txt",(3,0), Option.empty)
    dp.part1Answer(3) should be(6)
  }

  "2019 Day 2 - Example #4" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-example#4.txt", (4,4), Option.empty)
    dp.part1Answer(5) should be(9801)
  }

  "2019 Day 2 - Example #5" should "calculate answers" in {
    val dp = new Day021202ProgramAlarm("/twenty_nineteen/Day02-1202ProgramAlarm-example#5.txt", (1,1), Option.empty)
    dp.part1Answer(0) should be(30)
  }

}
