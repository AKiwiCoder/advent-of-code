package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.Point

import scala.annotation.tailrec

class Day13CarePackage(filename: String) extends DailyProblem[Int, Int] {
  private val program = IntComputer.loadProgram(filename)

  private def doPart1 = {
    IntComputer.execute(IntComputerState.newState(program)).output.grouped(3).count(entry => entry(2) == 2)
  }

  private def doPart2: Int = {
    @tailrec
    def playBreakout(state: IntComputerState, grid: Map[Point, Int]): Int = {
      if (state.isFinished())
        grid(Point(0, -1))
      else {
        val newState = IntComputer.execute(state)
        val commands = newState.output.grouped(3)
        val newGrid = commands.foldLeft(grid)((acc, entry) => (acc + (Point(entry(1).toInt, entry(0).toInt) -> entry(2).toInt)))

        val ballPos = newGrid.filter(entry => entry._2 == 4)
        val batPos = newGrid.filter(entry => entry._2 == 3)

        val ballX = if (ballPos.nonEmpty) ballPos.head._1.x else 0
        val batX = if (batPos.nonEmpty) batPos.head._1.x else 0

        playBreakout(IntComputerState.copyState(newState, List(Integer.compare(ballX, batX))), newGrid)
      }
    }

    playBreakout(IntComputerState.newState(program + (0L -> 2L)), Map())
  }

  override val part1Answer: Int = doPart1
  override val part2Answer: Int = doPart2
}


