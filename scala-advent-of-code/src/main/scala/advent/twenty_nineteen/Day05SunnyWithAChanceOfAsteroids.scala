package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05SunnyWithAChanceOfAsteroids(filename: String) extends DailyProblem[Int, Int] {
  private val program = IntComputer.loadProgram(filename)

  override val part1Answer: Int = IntComputer.execute(IntComputerState(program, 0, 0, List(1), List())).output.last.toInt
  override val part2Answer: Int = IntComputer.execute(IntComputerState(program, 0, 0, List(5), List())).output.last.toInt
}


