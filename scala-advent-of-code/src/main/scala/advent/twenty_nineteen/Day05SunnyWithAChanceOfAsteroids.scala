package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05SunnyWithAChanceOfAsteroids(filename: String) extends DailyProblem[Int, Int] {
  private val program = FileUtilities.readFile(filename)(0).split(",").zipWithIndex.map(t => t._2 -> t._1.toInt).toMap

  private def get(index: Int, mode: Int, memory: Map[Int, Int]): Int = {
    if (mode == 0) memory(memory(index)) else memory(index)
  }

  @tailrec
  private def execute(memory: Map[Int, Int], pc: Int, input : Int, output: List[Int]): List[Int] = {
    if (memory(pc) == 99) {
      output
    } else {
      val opcode = memory(pc)

      val operation = opcode % 100
      val mode1 = opcode / 100 % 10
      val mode2 = opcode / 1000 % 10

      val (value, newOutput) = operation match {
        case 1 => (get(pc + 3, 1, memory) -> (get(pc + 1, mode1, memory) + get(pc+ 2, mode2, memory)), output)
        case 2 => (get(pc + 3, 1, memory) -> (get(pc + 1, mode1, memory) * get(pc+ 2, mode2, memory)), output)
        case 3 => (get(pc + 1, 1, memory) -> input, output)
        case 4 => (-1 -> -1, get(pc + 1, 0, memory) :: output)
        case 5 => (-1 -> -1, output)
        case 6 => (-1 -> -1, output)
        case 7 => (get(pc + 3, 1, memory) -> (if (get(pc + 1, mode1, memory) < get(pc + 2, mode2, memory)) 1 else 0), output)
        case 8 => (get(pc + 3, 1, memory) -> (if (get(pc + 1, mode1, memory) == get(pc + 2, mode2, memory)) 1 else 0), output)
        case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
      }

      val newPc = operation match {
        case 1 => pc + 4
        case 2 => pc + 4
        case 3 => pc + 2
        case 4 => pc + 2
        case 5 => if (get(pc + 1, mode1, memory) != 0) get(pc+2, mode2, memory) else pc + 3
        case 6 => if (get(pc + 1, mode1, memory) == 0) get(pc+2, mode2, memory) else pc + 3
        case 7 => pc + 4
        case 8 => pc + 4
        case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc)  + " " + pc)
      }

      execute(memory + value, newPc, input, newOutput)
    }
  }

  override val part1Answer: Int = execute(program, 0, 1, List()).head
  override val part2Answer: Int = execute(program, 0, 5, List()).head
}


