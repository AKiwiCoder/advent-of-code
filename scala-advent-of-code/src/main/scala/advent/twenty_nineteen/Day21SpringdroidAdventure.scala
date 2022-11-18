package advent.twenty_nineteen

import advent.common.DailyProblem

class Day21SpringdroidAdventure(filename: String) extends DailyProblem[Long, Long] {

  private val program = IntComputer.loadProgram(filename)

  var state = IntComputerState.newState(program)

  // If we have a hole somewhere in the next three squares and we can land
  // safely then jump
  // (!A || !B || !C) & D
  val part1 =
  """NOT A T
    |OR T J
    |NOT B T
    |OR T J
    |NOT C T
    |OR T J
    |NOT D T
    |NOT T T
    |AND T J
    |WALK
    |""".stripMargin

  // If we have a hole somewhere in the next three squares and we can land
  // safely then jump
  // With the additional constraint we need to jump again if H is clear or
  // we can take an step onto E
  // Part 1 && (H || E)
  val part2 =
    """NOT A T
      |OR T J
      |NOT B T
      |OR T J
      |NOT C T
      |OR T J
      |NOT D T
      |NOT T T
      |AND T J
      |NOT H T
      |NOT T T
      |OR E T
      |AND T J
      |RUN
      |""".stripMargin


  private val ranPart1 = IntComputer.execute(IntComputerState.copyState(state, part1.toList.map(_.toLong)))
  private val ranPart2 = IntComputer.execute(IntComputerState.copyState(state, part2.toList.map(_.toLong)))

  override val part1Answer: Long = ranPart1.output.last
  override val part2Answer: Long = ranPart2.output.last
}


