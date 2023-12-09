package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day09MirageMaintenance(filename : String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).map(_.split(" +").toList.map(_.toInt))

  def diff(src: List[Int]): List[Int] = {
    src.zip(src.tail).map(p => p._2 - p._1)
  }

  @tailrec
  final def iterate(current: List[Int], acc: List[List[Int]]): List[List[Int]] = {
    if (!current.exists(c => c != 0)) {
      current :: acc
    } else {
      iterate(diff(current), current :: acc)
    }
  }

  def process(line : List[Int], op1 : List[Int] => Int, op2 : (Int,Int) => Int) : Int = {
    val processed = iterate (line, List ())
    processed.map (op1).fold(0)(op2)
  }

  override val part1Answer: Int = input.map(line => process(line, a => a.last, (a,b) => a + b)).sum
  override val part2Answer: Int = input.map(line => process(line, a => a.head, (a,b) => b - a)).sum
}


