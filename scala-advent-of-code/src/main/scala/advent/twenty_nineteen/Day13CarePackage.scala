package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.Point

class Day13CarePackage(filename: String) extends DailyProblem[Int, Int] {

  private val program = IntComputer.loadProgram(filename)

  def copyState(original: IntComputerState, input: List[Long]): IntComputerState = original.copy(input = input, output = List())

  private def doPart1 = {
    val state = IntComputerState(program, 0, 0, List(), List())

    val output = IntComputer.execute(state).output

    val commands = output.grouped(3)

    val grid = commands.map(entry => (Point(entry(0).toInt, entry(1).toInt) -> entry(2).toInt)).toMap
    grid.values.count(e => e == 2)
  }


  private def doPart2 : Int = {
    var state = IntComputerState(program + (0L -> 2L), 0, 0, List(), List())
    var score = 0
    var grid = Map[Point, Int]()

    while (!state.isFinished()) {
      state = IntComputer.execute(state)
      val commands = state.output.grouped(3)

      grid = commands.foldLeft(grid)((acc, entry) => (acc + (Point(entry(1).toInt, entry(0).toInt) -> entry(2).toInt)))

      val ballPos = grid.filter(entry => entry._2 == 4)
      val batPos = grid.filter(entry => entry._2 == 3)

      val ballX = if (ballPos.nonEmpty) ballPos.head._1.x else 0
      val batX = if (batPos.nonEmpty) batPos.head._1.x else 0

      score = grid.getOrElse(Point(0, -1), 0)

      state = copyState(state, List(Integer.compare(ballX, batX)))
    }

    score
  }

  override val part1Answer: Int = doPart1
  override val part2Answer: Int = doPart2
}


