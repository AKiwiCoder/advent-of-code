package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day04Scratchcards(filename: String) extends DailyProblem[Int, Int] {
  case class Card(num: Int, winningNumbers: List[Int], numbersOnCard: List[Int]) {
    def countMatches() : Int = {
      winningNumbers.intersect(numbersOnCard).length
    }

    def score(): Int = {
      val matches = winningNumbers.intersect(numbersOnCard)
      if (matches.isEmpty)
        0
      else
        matches.tail.foldLeft(1)((acc, _) => acc * 2)
    }
  }

  private def parse(line: String): Card = {
    val colon = line.split(":").toList
    val bar = colon(1).split("\\|").toList

    val cardNumber = colon(0).trim.split(" +")(1).toInt
    val winningNumbers = bar(0).trim.split(" +").map(_.trim.toInt).toList
    val numberOnCard = bar(1).split(" +").filter(_.nonEmpty).map(_.trim.toInt).toList
    Card(cardNumber, winningNumbers, numberOnCard)
  }

  private val input = FileUtilities.readFile(filename).map(line => parse(line))

  @tailrec
  private def multiply(index : Int, value: Map[Int, Int]) : Map[Int, Int] = {
    if (index > input.length) {
      value
    } else {
      val matches = input(index-1).countMatches()
      val newValue = if (matches == 0) {
        value
      } else {
        ((index + 1) until (index + 1 + matches)).foldLeft(value)((acc, idx) => acc + (idx -> (value(idx) + value(index))))
      }
      multiply(index + 1, newValue)
    }
  }

  private val initialMap = input.map(_.num -> 1).toMap

  override val part1Answer: Int = input.map(_.score()).sum
  override val part2Answer: Int = multiply(1, initialMap).values.sum
}


