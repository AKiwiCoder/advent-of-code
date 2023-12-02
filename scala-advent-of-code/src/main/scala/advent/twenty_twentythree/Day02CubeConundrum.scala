package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day02CubeConundrum(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private def split_game(game: String): (String, Int) = {
    val bits = game.trim().split(" ")
    bits(1) -> bits(0).toInt
  }

  private def split_sets(value : String) : Set[String] = {
    value.split(";").toSet
  }

  private val games = input.map(line => line.substring(line.indexOf(" "), line.indexOf(":")).trim.toInt -> line.substring(line.indexOf(":")+1).trim()).toMap

  private val games_sets = games.map(entry => entry._1 -> split_sets(entry._2))

  private val games_sets_hands = games_sets.map(entry => entry._1 -> entry._2.map(a => a.split(",").map(b => split_game(b)).toMap))

  private def part_one_invalid_game(game: Set[Map[String, Int]]): Boolean = {
    game.exists(set => {
      val redCount = set.getOrElse("red", 0)
      val greenCount = set.getOrElse("green", 0)
      val blueCount = set.getOrElse("blue", 0)
      redCount > 12 || greenCount > 13 || blueCount > 14
    })
  }

  private def part_two_cube_power(game: Set[Map[String, Int]]) : Int = {
    val row = game.foldLeft((0,0,0))((acc,set) => {
      val newRed = Math.max(set.getOrElse("red", 0), acc._1)
      val newGreen = Math.max(set.getOrElse("green", 0), acc._2)
      val newBlue = Math.max(set.getOrElse("blue", 0), acc._3)
      (newRed, newGreen, newBlue)
    })
    row._1 * row._2 * row._3
  }

  override val part1Answer: Int = games_sets_hands.filter(e => !part_one_invalid_game(e._2)).keys.sum
  override val part2Answer: Int = games_sets_hands.map(e => part_two_cube_power(e._2)).sum
}


