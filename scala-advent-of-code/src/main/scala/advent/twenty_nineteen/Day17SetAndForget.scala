package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day17SetAndForget(filename: String) extends DailyProblem[Int, Long] {

  private val program = IntComputer.loadProgram(filename)

  private def doPart1(program: Map[Long, Long]): (Int, Array[String]) = {
    val outputs = IntComputer.execute(IntComputerState.newState(program)).output
    val map = outputs.foldLeft("")((output, char) => output + char.toChar).split("\n")

    var alignmentTotal = 0
    for (row <- 1 until map.size - 1) {
      for (col <- 1 until map(row).size - 1) {
        if (map(row)(col) == '#' && map(row - 1)(col) == '#' && map(row + 1)(col) == '#' && map(row)(col - 1) == '#' && map(row)(col + 1) == '#') {
          alignmentTotal += row * col
        }
      }
    }
    (alignmentTotal, map)
  }

  private def doPart2(program: Map[Long, Long], map: Array[String]): Long = {
    val part2 = IntComputerState.newState(program + (0L -> 2L))
    val input = "A,B,A,C,A,B,C,B,C,B\nR,10,R,10,R,6,R,4\nR,10,R,10,L,4\nR,4,L,4,L,10,L,10\nn\n".map(c => c.toLong).toList
    IntComputer.execute(IntComputerState.copyState(part2, input)).output.last
  }

  private val (alignmentValue, map) = doPart1(program)

  override val part1Answer: Int = alignmentValue
  override val part2Answer: Long = doPart2(program, map)
}


