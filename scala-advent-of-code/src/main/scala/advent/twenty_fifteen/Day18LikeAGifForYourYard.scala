package advent.twenty_fifteen

import advent.common.DailyProblem

class Day18LikeAGifForYourYard(filename: String, partOneSteps: Int, partTwoSteps: Int) extends DailyProblem[Int, Int] {

  def input: Array[Array[Boolean]] = Array.ofDim(100, 100)

  def turnOnCorners(array: Array[Array[Boolean]]) = {
    array(0)(0) = true
    array(0)(array(0).length - 1) = true
    array(array(0).length - 1)(0) = true
    array(array(0).length - 1)(array(0).length - 1) = true
    array
  }

  def process(steps: Int, previous: Array[Array[Boolean]]): Int = {
    val working = Array.ofDim[Boolean](previous.length, previous(0).length)

    def iterate(working: Array[Array[Boolean]]): Array[Array[Boolean]] = {
      working
    }

    iterate(working)

    working.foldLeft(0)((acc, row) => acc + row.foldLeft(0)((acc, on) => if (on) acc + 1 else acc))
  }

  override val part1Answer: Int = process(partOneSteps, turnOnCorners(input))
  override val part2Answer: Int = process(partTwoSteps, turnOnCorners(input))
}
