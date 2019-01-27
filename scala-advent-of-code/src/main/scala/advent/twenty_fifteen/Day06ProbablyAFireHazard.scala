package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

abstract class Day06Command(top: Int, left: Int, bottom: Int, right: Int)

case class Day06TurnOnCommand(top: Int, left: Int, bottom: Int, right: Int) extends Day06Command(top, left, bottom, right)

case class Day06ToggleCommand(top: Int, left: Int, bottom: Int, right: Int) extends Day06Command(top, left, bottom, right)

case class Day06TurnOffCommand(top: Int, left: Int, bottom: Int, right: Int) extends Day06Command(top, left, bottom, right)


class Day06ProbablyAFireHazard(filename: String) extends DailyProblem[Int, Int] {
  private val turnOnPattern = "turn on ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".r
  private val togglePattern = "toggle ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".r
  private val turnOffPattern = "turn off ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".r

  private def parser(line: String): Day06Command =
    line match {
      case turnOnPattern(t, l, b, r) => Day06TurnOnCommand(t.toInt, l.toInt, b.toInt, r.toInt)
      case togglePattern(t, l, b, r) => Day06ToggleCommand(t.toInt, l.toInt, b.toInt, r.toInt)
      case turnOffPattern(t, l, b, r) => Day06TurnOffCommand(t.toInt, l.toInt, b.toInt, r.toInt)
      case _ => throw new IllegalStateException(s"Cannot match '$line'")
    }

  private def executeCommands(commands: List[Day06Command], lights: Array[Array[Int]], on: Int => Int, toggle: Int => Int, off: Int => Int): Int = {
    def process(top: Int, left: Int, bottom: Int, right: Int, operation: Int => Int) = {
      for (col <- left to right)
        for (row <- top to bottom)
          lights(row)(col) = operation(lights(row)(col))
    }

    commands.foreach {
      case Day06TurnOnCommand(t, l, b, r) => process(t, l, b, r, on)
      case Day06ToggleCommand(t, l, b, r) => process(t, l, b, r, toggle)
      case Day06TurnOffCommand(t, l, b, r) => process(t, l, b, r, off)
    }

    lights.foldLeft(0)((acc, row) => acc + row.sum)
  }

  private val commands = FileUtilities.readFile(filename, parser)

  override val part1Answer = executeCommands(commands, Array.ofDim[Int](1000, 1000), _ => 1, a => if (a == 0) 1 else 0, _ => 0)
  override val part2Answer = executeCommands(commands, Array.ofDim[Int](1000, 1000), a => a + 1, a => a + 2, a => Math.max(0, a - 1))
}