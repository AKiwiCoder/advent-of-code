package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day04CampCleanup(filename : String) extends DailyProblem[Int, Int] {

  private val entry = "([0-9]+)-([0-9]+)".r

  def build(str : String) : Range = {
    str match {
      case entry(f,t) => Range(f.toInt,t.toInt)
    }
  }

  case class Range(start : Int, end: Int)
  private val input = FileUtilities.readFile(filename).map(a => a.split(",")).map(a => (build(a(0)), build(a(1))))

  def contained(a : Range, b: Range) : Boolean = (a.start >= b.start && a.end <= b.end) || (a.start <= b.start && a.end >= b.end)
  def overlap(a : Range, b: Range) : Boolean = (a.start <= b.end) && (a.end >= b.start)

  override val part1Answer: Int = input.count(p => contained(p._1, p._2))
  override val part2Answer: Int = input.count(p => overlap(p._1, p._2))
}

