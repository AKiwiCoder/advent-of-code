package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day021202ProgramAlarm(filename: String, part1 : (Int, Int), part2Target: Option[Int]) extends DailyProblem[Map[Int,Int], Int] {
  private val input = FileUtilities.readFile(filename)(0).split(",").zipWithIndex.map(t => t._2 -> t._1.toInt).toMap

  @tailrec
  private def execute(memory: Map[Int, Int], pc: Int): Map[Int, Int] = {
    if (memory(pc) == 99) {
      memory
    } else {
      val value = memory(pc) match {
        case 1 => memory(memory(pc + 1)) + memory(memory(pc + 2))
        case 2 => memory(memory(pc + 1)) * memory(memory(pc + 2))
        case _ => throw new IllegalArgumentException("Unknown OP Code " + memory + " " + pc)
      }
      val destination = memory(pc + 3)
      execute(memory + (destination -> value), pc + 4)
    }
  }

  private def execute(memory: Map[Int, Int], noun : Int, verb: Int): Map[Int, Int] = {
    execute(memory + (1 -> noun) + (2->verb), 0)
  }

  def execute(target : Int): Int = {
    for (noun <- 0 to 99)
      for (verb <- 0 to 99) {
        if (execute(input, noun, verb)(0) == target) {
          return 100  * noun + verb
        }
      }
    0
  }

  override val part1Answer: Map[Int,Int] = execute(input, part1._1, part1._2)
  override val part2Answer: Int = if (part2Target.isDefined) execute(part2Target.get) else 0
}
