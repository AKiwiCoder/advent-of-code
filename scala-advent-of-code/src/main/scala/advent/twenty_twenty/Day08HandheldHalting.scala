package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class State(pc: Int, register: Int)

class Instruction() {
  def execute(state: State): State = ???
  def isSwappable() : Boolean = ???
}

case class AccumulateInstruction(arg: Int) extends Instruction {
  override def execute(state: State): State = {
    State(state.pc + 1, state.register + arg)
  }

  override def isSwappable() : Boolean = false
}

case class JumpInstruction(arg: Int) extends Instruction {
  override def execute(state: State): State = {
    State(state.pc + arg, state.register)
  }

  override def isSwappable() : Boolean = true
}

case class NoOperationInstruction(arg: Int) extends Instruction {
  override def execute(state: State): State = {
    State(state.pc + 1, state.register)
  }

  override def isSwappable() : Boolean = true
}

class Day08HandheldHalting(filename: String) extends DailyProblem[Int, Int] {

  private val accPattern = "acc ([+-][0-9]+)".r
  private val jmpPattern = "jmp ([+-][0-9]+)".r
  private val nopPattern = "nop ([+-][0-9]+)".r

  private def parse(line: String): Instruction = {
    line match {
      case accPattern(arg) => AccumulateInstruction(arg.toInt)
      case jmpPattern(arg) => JumpInstruction(arg.toInt)
      case nopPattern(arg) => NoOperationInstruction(arg.toInt)
    }
  }

  private val input = FileUtilities.readFile(filename, parse)

  private def part1(): Int = {
    @tailrec
    def execute(state : State, done : Set[Int] ) : Int = {
      if (done.contains(state.pc )) {
        state.register
      } else {
        execute(input(state.pc).execute(state),  done + state.pc)
      }
    }
    execute(State(0, 0), Set())
  }

  private def part2(): Int = {
    @tailrec
    def execute(program : List[Instruction], state : State, done : Set[Int] ) : Int = {
      if (done.contains(state.pc)) {
        Integer.MIN_VALUE
      } else if (state.pc >= program.size) {
        state.register
      } else {
        execute(program, program(state.pc).execute(state),  done + state.pc)
      }
    }

    @tailrec
    def mutateProgram(original : List[Instruction], idx : Int) : Int = {
      if (idx == -1) {
        Integer.MIN_VALUE
      } else {
        val newProgram = original.zipWithIndex.map(entry => if (entry._2 == idx) {
          entry._1 match {
            case JumpInstruction(arg) => NoOperationInstruction(arg)
            case NoOperationInstruction(arg) => JumpInstruction(arg)
          }
        } else {
          entry._1
        })
        val result = execute(newProgram, State(0,0), Set())
        if (result != Integer.MIN_VALUE) {
          result
        } else {
          mutateProgram(original, original.indexWhere(code => code.isSwappable(), idx + 1))
        }
      }
    }

    mutateProgram(input, input.indexWhere(code => code.isSwappable(), 0))
  }


  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


