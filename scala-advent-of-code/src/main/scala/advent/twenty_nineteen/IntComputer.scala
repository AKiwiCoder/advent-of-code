package advent.twenty_nineteen

import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class IntComputerState(memory : Map[Long, Long], pc : Long, relativeBase : Long, input : List[Long], output: List[Long]) {
  def isFinished() : Boolean = {
    pc < 0
  }
}

object IntComputer {
  def loadProgram(filename: String) : Map[Long,Long] = {
    FileUtilities.readFile(filename)(0).split(",").zipWithIndex.map(t => t._2.toLong -> t._1.toLong).toMap.withDefaultValue(0L)
  }

  private def getReadValue(index: Long, mode: Long, relativeBase: Long, memory: Map[Long, Long]): Long = {
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

  private def getWriteIndex(index: Long, mode: Long, relativeBase: Long, memory: Map[Long, Long]): Long = {
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
  def execute(memory: Map[Long, Long], pc: Long, relativeBase: Long, input: List[Long], output: List[Long]): IntComputerState = {
    if (memory(pc) == 99) {
      // Finished All Done
      IntComputerState(memory, -1, -1, input, output)
    } else {
      val opcode: Long = memory(pc)
      val operation = opcode % 100
      val mode1 = if (opcode > 99) opcode / 100 % 10 else 0L
      val mode2 = if (opcode > 999) opcode / 1000 % 10 else 0L
      val mode3 = if (opcode > 9999) opcode / 10000 % 10 else 1L

      if (operation == 3 && input.size == 0) {
        // Pending Input
        IntComputerState(memory, pc, relativeBase, input, output)
      } else {
        val (value: (Long, Long), newOutput: List[Long], newRelativeBase: Long) = operation match {
          case 1 => (getWriteIndex(pc + 3, mode3, relativeBase, memory) -> (getReadValue(pc + 1, mode1, relativeBase, memory) + getReadValue(pc + 2, mode2, relativeBase, memory)), output, relativeBase)
          case 2 => (getWriteIndex(pc + 3, mode3, relativeBase, memory) -> (getReadValue(pc + 1, mode1, relativeBase, memory) * getReadValue(pc + 2, mode2, relativeBase, memory)), output, relativeBase)
          case 3 => (getWriteIndex(pc + 1, if (mode1 == 2) 2 else 1, relativeBase, memory) -> input.head, output, relativeBase)
          case 4 => (Long.MaxValue -> -1L, output ::: getReadValue(pc + 1, mode1, relativeBase, memory) :: Nil, relativeBase)
          case 5 => (Long.MaxValue -> -1L, output, relativeBase)
          case 6 => (Long.MaxValue -> -1L, output, relativeBase)
          case 7 => (getWriteIndex(pc + 3, mode3, relativeBase, memory) -> (if (getReadValue(pc + 1, mode1, relativeBase, memory) < getReadValue(pc + 2, mode2, relativeBase, memory)) 1L else 0L), output, relativeBase)
          case 8 => (getWriteIndex(pc + 3, mode3, relativeBase, memory) -> (if (getReadValue(pc + 1, mode1, relativeBase, memory) == getReadValue(pc + 2, mode2, relativeBase, memory)) 1L else 0L), output, relativeBase)
          case 9 => (Long.MaxValue -> -1L, output, relativeBase + getReadValue(pc + 1, mode1, relativeBase, memory))
          case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
        }

        val newPc: Long = operation match {
          case 1 => pc + 4
          case 2 => pc + 4
          case 3 => pc + 2
          case 4 => pc + 2
          case 5 => if (getReadValue(pc + 1, mode1, relativeBase, memory) != 0) getReadValue(pc + 2, mode2, relativeBase, memory) else pc + 3
          case 6 => if (getReadValue(pc + 1, mode1, relativeBase, memory) == 0) getReadValue(pc + 2, mode2, relativeBase, memory) else pc + 3
          case 7 => pc + 4
          case 8 => pc + 4
          case 9 => pc + 2
          case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
        }

        execute(memory + value, newPc, newRelativeBase, if (operation == 3) input.tail else input, newOutput)
      }
    }
  }

  def execute(state : IntComputerState): IntComputerState = {
    execute(state.memory, state.pc, state.relativeBase, state.input, state.output)
  }
}
