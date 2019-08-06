package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day20InfiniteElvesAndInfiniteHouses(targetPresents : Int) extends DailyProblem[Int, Int] {

  def elfVisitPattern1(elf : Int, houseCount : Int) : List[Int] = {
    (elf to houseCount by elf).toList
  }

  def elfVisitPattern2(elf : Int, houseCount : Int) : List[Int] = {
    (elf to houseCount by elf).toList.take(50)
  }

  def populateHouse(houseCount : Int, pattern : (Int,Int) => List[Int], gifts: (Int) => Int) : Int = {
    val houses = new Array[Int](houseCount+1)
    var result = Integer.MAX_VALUE
    for (elf <- 1 to houseCount) {
      for (hIndex <- pattern(elf , houseCount)) {
        houses(hIndex) += gifts(elf)
        if (houses(hIndex) >= targetPresents)
          result = Math.min(hIndex, result)
      }
    }

    result
  }

  override val part1Answer: Int = populateHouse(1000000, elfVisitPattern1, e => e * 10)
  override val part2Answer: Int = populateHouse(1000000, elfVisitPattern2, e => e * 11)
}
