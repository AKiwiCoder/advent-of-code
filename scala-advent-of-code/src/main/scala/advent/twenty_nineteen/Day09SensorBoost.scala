package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day09SensorBoost(filename: String) extends DailyProblem[Long, Long] {
  private val program: Map[Long, Long] = FileUtilities.readFile(filename)(0).split(",").zipWithIndex.map(t => t._2.toLong -> t._1.toLong).toMap.withDefaultValue(0L)

  private def get(index: Long, mode: Long, relativeBase: Long, memory: Map[Long, Long]): Long = {
    if (mode == 0) {
      memory(memory(index))
    } else if (mode == 1) {
      memory(index)
    } else if (mode == 2) {
      memory(relativeBase + memory(index))
    } else {
      throw new IllegalArgumentException("Unknown Mode " + mode)
    }
  }

  private def getAddr(index: Long, mode: Long, relativeBase: Long, memory: Map[Long, Long]): Long = {
    if (mode == 0) {
      memory(memory(index))
    } else if (mode == 1) {
      memory(index)
    } else if (mode == 2) {
      relativeBase + memory(index)
    } else {
      throw new IllegalArgumentException("Unknown Mode " + mode)
    }
  }

  @tailrec
  private def execute(memory: Map[Long, Long], pc: Long, relativeBase: Long, input: List[Long], output: List[Long]): (Map[Long, Long], Long, List[Long]) = {
    if (memory(pc) == 99) {
      // Finished All Done
      (memory, -1, output)
    } else {
      val opcode: Long = memory(pc)

      val operation = opcode % 100
      val mode1 = if (opcode > 99) opcode / 100 % 10 else 0L
      val mode2 = if (opcode > 999) opcode / 1000 % 10 else 0L
      val mode3 = if (opcode > 9999) opcode / 10000 % 10 else 1L

      val (value: (Long, Long), newOutput: List[Long], newRelativeBase: Long) = operation match {
        case 1 => (getAddr(pc + 3, mode3, relativeBase, memory) -> (get(pc + 1, mode1, relativeBase, memory) + get(pc + 2, mode2, relativeBase, memory)), output, relativeBase)
        case 2 => (getAddr(pc + 3, mode3, relativeBase, memory) -> (get(pc + 1, mode1, relativeBase, memory) * get(pc + 2, mode2, relativeBase, memory)), output, relativeBase)
        case 3 => (getAddr(pc + 1, if (mode1 == 2) 2 else 1, relativeBase, memory) -> input.head, output, relativeBase)
        case 4 => (Long.MaxValue -> -1L, get(pc + 1, mode1, relativeBase, memory) :: output, relativeBase)
        case 5 => (Long.MaxValue -> -1L, output, relativeBase)
        case 6 => (Long.MaxValue -> -1L, output, relativeBase)
        case 7 => (getAddr(pc + 3, mode3, relativeBase, memory) -> (if (get(pc + 1, mode1, relativeBase, memory) < get(pc + 2, mode2, relativeBase, memory)) 1L else 0L), output, relativeBase)
        case 8 => (getAddr(pc + 3, mode3, relativeBase, memory) -> (if (get(pc + 1, mode1, relativeBase, memory) == get(pc + 2, mode2, relativeBase, memory)) 1L else 0L), output, relativeBase)
        case 9 => (Long.MaxValue -> -1L, output, relativeBase + get(pc + 1, mode1, relativeBase, memory))
        case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
      }

      val newPc: Long = operation match {
        case 1 => pc + 4
        case 2 => pc + 4
        case 3 => pc + 2
        case 4 => pc + 2
        case 5 => if (get(pc + 1, mode1, relativeBase, memory) != 0) get(pc + 2, mode2, relativeBase, memory) else pc + 3
        case 6 => if (get(pc + 1, mode1, relativeBase, memory) == 0) get(pc + 2, mode2, relativeBase, memory) else pc + 3
        case 7 => pc + 4
        case 8 => pc + 4
        case 9 => pc + 2
        case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
      }

      execute(memory + value, newPc, newRelativeBase, if (operation == 3) input.tail else input, newOutput)
    }
  }

  val part1 = execute(program, 0L, 0L, List[Long](1), List[Long]())
  val part2 = execute(program, 0L, 0L, List[Long](2), List[Long]())

  override val part1Answer: Long = part1._3.head
  override val part2Answer: Long = part2._3.head
}


