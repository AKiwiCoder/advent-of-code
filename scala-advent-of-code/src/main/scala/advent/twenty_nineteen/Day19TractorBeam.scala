package advent.twenty_nineteen

import advent.common.DailyProblem

import scala.annotation.tailrec

class Day19TractorBeam(filename: String) extends DailyProblem[Int, Int] {
  private val program = IntComputer.loadProgram(filename)

  var count = 0
  for (y <- 0 until 50) {
    for (x <- 0 until 50) {
      val state = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))
      if (state.output.head == 1) {
        count = count + 1
      }
    }
  }

  @tailrec
  private def calculatePartOne(y: Int, x: Int, count: Int): Int = {
    if (y == 50) {
      count
    } else {
      val state = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))

      val newCount = if (state.output.head == 1) count + 1 else count

      val newY = if (x == 49) y + 1 else y
      val newX = if (x == 49) 0 else x + 1

      calculatePartOne(newY, newX, newCount)
    }
  }

  private def isInBeam(y: Int, x: Int): Boolean = {
    val state = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))
    state.output.head == 1
  }

  @tailrec
  private def calculatePartTwo(y: Int, x: Int): Int = {
    if (isInBeam(y, x) && isInBeam(y, x + 99) && isInBeam(y + 99, x) && isInBeam(y + 99, x + 99)) {
      (10000 * x + y)
    } else {
      val newY = if (x == 2000) y + 1 else y
      val newX = if (x == 2000) 0 else x + 1
      calculatePartTwo(newY, newX)
    }
  }

  override val part1Answer: Int = calculatePartOne(0, 0, 0)
  override val part2Answer: Int = calculatePartTwo(1000, 1000)
}


