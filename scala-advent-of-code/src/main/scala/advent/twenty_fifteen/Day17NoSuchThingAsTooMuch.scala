package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Container(id : Int, volume : Int)

class Day17NoSuchThingAsTooMuch(filename: String, target : Int) extends DailyProblem[Int, Int] {
  private val lines = FileUtilities.readFile(filename)
  private val containers = (0 until lines.size).map(id => Container(id, lines(id).toInt)).toSet

  private val answers = containers.subsets.toList.filter(e => e.toList.map(x => x.volume).sum == target)
  private val minimum = answers.map(e => e.size).min

  override val part1Answer: Int = answers.size
  override val part2Answer: Int = answers.count(p => p.size == minimum)
}
