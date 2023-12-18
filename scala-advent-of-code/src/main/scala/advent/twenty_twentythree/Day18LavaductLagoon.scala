package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day18LavaductLagoon(filename: String) extends DailyProblem[Long, Long] {
  case class Instruction(dir: Char, steps: Int, colour: String)

  val pattern = "([RDLU]) ([0-9]+) \\((#[a-f0-9]+)\\)".r

  private val inputOne = FileUtilities.readFile(filename).map {
    case pattern(d, s, c) => Instruction(d(0), s.toInt, c)
  }

  private val inputTwo = FileUtilities.readFile(filename).map {
    case pattern(d, s, c) => {
      val dir = c(6) match {
        case '0' => 'R'
        case '1' => 'D'
        case '2' => 'L'
        case '3' => 'U'
      }
      val steps = Integer.parseInt(c.substring(1, 6), 16)
      Instruction(dir, steps, c)
    }
  }

  case class Point(y : Long, x : Long)

  def solve(instructions : List[Instruction]) : Long = {
    val (_, perimeter, area) = instructions.foldLeft((Point(0,0), 0L, 0L))((acc, instruction) => {
      val (current, perimeter, area) = acc

      val (nextPos, newArea) = instruction.dir match {
        case 'U' => (Point(current.y-instruction.steps, current.x), area)
        case 'D' => (Point(current.y+instruction.steps, current.x), area)
        case 'L' => (Point(current.y, current.x - instruction.steps), area - current.y * instruction.steps)
        case 'R' => (Point(current.y, current.x+instruction.steps), area + current.y * instruction.steps)
      }

      (nextPos, perimeter + instruction.steps, newArea)
    })

    Math.abs(area) + perimeter / 2 + 1
  }

  override val part1Answer: Long = solve(inputOne)
  override val part2Answer: Long = solve(inputTwo)
}


