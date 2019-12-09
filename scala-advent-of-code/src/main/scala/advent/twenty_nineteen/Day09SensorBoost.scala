package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day09SensorBoost(filename: String) extends DailyProblem[Long, Long] {
  private val program = IntComputer.loadProgram(filename)

  val part1 = IntComputer.execute(program, 0L, 0L, List[Long](1), List[Long]())
  val part2 = IntComputer.execute(program, 0L, 0L, List[Long](2), List[Long]())

  override val part1Answer: Long = part1._3.head
  override val part2Answer: Long = part2._3.head
}


