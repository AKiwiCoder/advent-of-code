package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day11SpacePolice(filename: String) extends DailyProblem[Int, String] {
  private val program = IntComputer.loadProgram(filename)

  def copyState(original: IntComputerState, input: List[Long]): IntComputerState = original.copy(input = input, output = List())

  def walkTheGrid(initial: Long): Map[Point, Long] = {
    @tailrec
    def walkTheGrid(originalState: IntComputerState, location: Point, facing: Facing, grid: Map[Point, Long]): Map[Point, Long] = {
      val newState = IntComputer.execute(originalState)
      if (newState.isFinished()) {
        grid
      } else {
        val newGrid = grid + (location -> newState.output(0))

        val newFacing = newState.output(1) match {
          case 0 => LocationHelper.turn(facing, TurnLeft())
          case 1 => LocationHelper.turn(facing, TurnRight())
          case _ => throw new IllegalArgumentException("Unknown output  " + newState.output)
        }

        val newLocation = LocationHelper.step(location, newFacing)

        walkTheGrid(copyState(newState, List[Long](grid(newLocation))), newLocation, newFacing, newGrid)
      }
    }

    val grid: Map[Point, Long] = Map().withDefaultValue(0)
    walkTheGrid(IntComputerState(program, 0, 0, List(initial), List()), Point(0, 0), FacingNorth(), grid + (Point(0, 0) -> initial))
  }

  def paintTheGrid(grid: Map[Point, Long]): String = {
    val limits = grid.filter(entry => entry._2 == 1).keySet.foldLeft((Int.MaxValue, Int.MaxValue,Int.MinValue,Int.MinValue))((a, p) => (Math.min(a._1, p.y), Math.min(a._2, p.x), Math.max(a._3, p.y), Math.max(a._4, p.x) ))

    val result = for (y <- limits._1 to limits._3; x  <- limits._2 to limits._4)
        yield if (grid(Point(y, x)) == 1) '#' else '.'

    result.toList.grouped(limits._4 - limits._2 + 1).map(row => row.mkString("")).mkString("\n")
  }

  override val part1Answer: Int = walkTheGrid(0).keySet.size
  override val part2Answer: String = paintTheGrid(walkTheGrid(1))
}


