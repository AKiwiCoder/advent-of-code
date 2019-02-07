package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day15ScienceForHungryPeopleTest  extends FlatSpec with Matchers {
  "2015 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15ScienceForHungryPeople("/twenty_fifteen/Day15-ScienceForHungryPeople-input.txt")

    dp.part1Answer should be(222870)
    dp.part2Answer should be(117936)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day15ScienceForHungryPeople("/twenty_fifteen/Day15-ScienceForHungryPeople-example#1.txt")

    dp.part1Answer should be(62842880)
    dp.part2Answer should be(57600000)
  }
}
