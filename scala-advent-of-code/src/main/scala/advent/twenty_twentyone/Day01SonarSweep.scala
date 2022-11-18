package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01SonarSweep(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename, _.toInt)

  override val part1Answer: Int = input.zip(input.tail).count(a => a._1 < a._2)
  override val part2Answer: Int = input.zip(input.tail.zip(input.tail.tail)).map(a => a._1 + a._2._1 + a._2._2).foldLeft((-1,0))((acc, current) => if (acc._2  < current) (acc._1 + 1, current) else (acc._1, current))._1
}


