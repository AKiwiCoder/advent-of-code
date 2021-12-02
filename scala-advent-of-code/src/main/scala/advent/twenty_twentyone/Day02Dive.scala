package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day02Dive(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val forward = "forward ([0-9])+".r
  private val up = "up ([0-9])+".r
  private val down = "down ([0-9])+".r
  private val aim = "aim ([0-9])+".r

  private def perform1(depth : Int, distance : Int, command: String): (Int, Int) = {
    command match {
      case forward(steps) => (depth, distance + steps.toInt)
      case up(steps) => (depth - steps.toInt, distance)
      case down(steps) => (depth + steps.toInt, distance)
    }
  }

  private def perform2(depth : Int, distance : Int, aim : Int, command: String): (Int, Int, Int) = {
    command match {
      case forward(steps) => (depth + aim * steps.toInt, distance + steps.toInt, aim)
      case up(steps) => (depth, distance, aim - steps.toInt)
      case down(steps) => (depth, distance, aim + steps.toInt)
    }
  }

  private val (p1depth, p1distance) = input.foldLeft((0, 0))((acc, command) => perform1(acc._1, acc._2, command))
  private val (p2depth, p2distance, _) = input.foldLeft((0, 0, 0))((acc, command) => perform2(acc._1, acc._2, acc._3, command))

  override val part1Answer: Int = p1depth * p1distance
  override val part2Answer: Int = p2depth * p2distance
}


