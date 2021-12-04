package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Board(numbers: List[List[Int]], called : List[Int] = List()) {
   def winner() : Boolean = {
     val rowWins = numbers.exists(row => row.count(called.contains(_)) == row.size)
     val colWins = numbers.head.indices.exists(colNum => numbers.count(row => called.contains(row(colNum))) == numbers.size)
     rowWins || colWins
  }

  def calculate(multiplier : Int) : Int = {
    multiplier * numbers.foldLeft(0)((score, row) => row.foldLeft(0)((score, col) => if (called.contains(col)) score else score + col) + score)
  }
}

class Day04GiantSquid(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val calledNumbers = input.head.trim.split(',').map(_.toInt).toList

  @tailrec
  private def boardGen(input: List[String], boards: List[Board]): List[Board] = {
    if (input.isEmpty) {
      boards
    } else {
      boardGen(input.drop(6), Board(input.slice(1, 6).map(line => line.trim.split("\\s+").toList.map(_.toInt))) :: boards)
    }
  }

  private val boards = boardGen(input.tail, List())

  @tailrec
  private def part1(numbers: List[Int], boards: List[Board]): Int = {
    val number = numbers.head
    val newBoards = boards.map(b => Board(b.numbers, number :: b.called))
    val winners = newBoards.filter(_.winner())
    if (winners.nonEmpty) {
      winners.head.calculate(number)
    } else {
      part1(numbers.tail, newBoards)
    }
  }

  @tailrec
  private def part2(numbers: List[Int], boards: List[Board]): Int = {
    val number = numbers.head
    val newBoards = boards.map(b => Board(b.numbers, number :: b.called))
    val nonWinners = newBoards.filter(!_.winner())
    val winners = newBoards.filter(_.winner())
    if (nonWinners.isEmpty) {
      winners.head.calculate(number)
    } else {
      part2(numbers.tail, nonWinners)
    }
  }

  override val part1Answer: Int = part1(calledNumbers, boards)
  override val part2Answer: Int = part2(calledNumbers, boards)
}


