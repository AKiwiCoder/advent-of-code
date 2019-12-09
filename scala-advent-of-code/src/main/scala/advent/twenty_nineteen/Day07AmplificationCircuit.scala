package advent.twenty_nineteen

import advent.common.DailyProblem

import scala.annotation.tailrec

class Day07AmplificationCircuit(filename: String) extends DailyProblem[Long, Long] {
  private val program = IntComputer.loadProgram(filename)

  def execute(state: IntComputerState): IntComputerState = if (state.isFinished()) state else IntComputer.execute(state)

  def createState(input: List[Long]): IntComputerState = IntComputerState(program, 0, 0, input, List())

  def copyState(original: IntComputerState, input: List[Long]): IntComputerState = original.copy(input = input, output = List())

  private def doPart1(): Long = {
    List(0, 1, 2, 3, 4).permutations.map(phase => {
      val stateA = execute(createState(List(phase(0), 0)))
      val stateB = execute(createState(List(phase(1), stateA.output.head)))
      val stateC = execute(createState(List(phase(2), stateB.output.head)))
      val stateD = execute(createState(List(phase(3), stateC.output.head)))
      val stateE = execute(createState(List(phase(4), stateD.output.head)))
      stateE.output.head
    }).max
  }

  private def doPart2(): Long = {
    @tailrec
    def executeLoop(stateA: IntComputerState, stateB: IntComputerState, stateC: IntComputerState, stateD: IntComputerState, stateE: IntComputerState): Long = {
      val workingA = execute(stateA)
      val workingB = execute(stateB)
      val workingC = execute(stateC)
      val workingD = execute(stateD)
      val workingE = execute(stateE)

      if (workingE.isFinished()) {
        workingE.output.head
      } else {
        executeLoop(copyState(workingA, workingE.output),
          copyState(workingB, workingA.output),
          copyState(workingC, workingB.output),
          copyState(workingD, workingC.output),
          copyState(workingE, workingD.output))
      }
    }

    List(5, 6, 7, 8, 9).permutations.map(phase => {
      executeLoop(createState(List(phase(0), 0)),
        createState(List(phase(1))),
        createState(List(phase(2))),
        createState(List(phase(3))),
        createState(List(phase(4))))
    }).max
  }

  override val part1Answer: Long = doPart1()
  override val part2Answer: Long = doPart2()
}


