package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day13PointOfIncidence(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  @tailrec
  private def split(pending : List[String], acc : List[String], soFar : List[List[String]]) : List[List[String]] = {
    if (pending.isEmpty) {
      if (acc.isEmpty) {
        soFar.reverse
      } else {
        (acc.reverse :: soFar).reverse
      }
    } else {
      val next = pending.head
      if (next.trim.isEmpty) {
        split(pending.tail, List(), acc.reverse :: soFar)
      } else {
        split(pending.tail, next :: acc, soFar)
      }
    }
  }

  @tailrec
  private  def diffCount(a: List[Char], b: List[Char], acc: Int): Int = {
    if (a.isEmpty && b.isEmpty) {
      acc
    } else {
      diffCount(a.tail, b.tail, acc + (if (a.head == b.head) 0 else 1))
    }
  }


  private def findReflection(puzzle: List[String], allowableSmudges: Int): (Int, Int) = {
    def doesReflectVertical(value: List[String], col: Int): Boolean = {
      val doesMatch = value.map(row => (row.take(col).reverse, row.drop(col))).map(e => e._1.zip(e._2))
      doesMatch.map(row => row.count(m => m._1 != m._2)).sum == allowableSmudges
    }

    def doesReflectHorizontal(value: List[String], row: Int): Boolean = {
      val doesMatch = value.take(row).reverse.zip(value.drop(row))
      doesMatch.map(m => diffCount(m._1.toList, m._2.toList, 0)).sum == allowableSmudges
    }

    val verticalValue = (1 until puzzle.head.length).find(col => doesReflectVertical(puzzle, col))
    val horizontalValue = (1 until puzzle.length).find(row => doesReflectHorizontal(puzzle, row))
    (verticalValue.getOrElse(0), horizontalValue.getOrElse(0))
  }

  private val puzzles = split(input, List(), List())


  private def score(e: (Int, Int)): Int = {
    e._1 + e._2 * 100
  }

  override val part1Answer: Int = puzzles.map(p => findReflection(p, 0)).map(score).sum
  override val part2Answer: Int = puzzles.map(p => findReflection(p, 1)).map(score).sum
}


