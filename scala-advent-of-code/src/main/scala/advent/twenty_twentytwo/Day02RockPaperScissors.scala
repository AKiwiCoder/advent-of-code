package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day02RockPaperScissors(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(r => r.trim().split(" ")).map(a => (a(0), a(1)))

  private def scoreRound(them: String, us: String): Int = {
    val choice = us match {
      case "R" => 1
      case "P" => 2
      case "S" => 3
    }
    val outcome = them match {
      case "R" => us match {
        case "R" => 3
        case "P" => 6
        case "S" => 0
      }
      case "P" => us match {
        case "R" => 0
        case "P" => 3
        case "S" => 6
      }
      case "S" => us match {
        case "R" => 6
        case "P" => 0
        case "S" => 3
      }
    }
    choice + outcome
  }

  private val their_choice = Map("A" -> "R", "B" -> "P", "C" -> "S")

  private val decrypt_part1 = Map("X" -> "R", "Y" -> "P", "Z" -> "S")

  private def decrypt_part2(them: String, us: String): String = {
    them match {
      case "R" => us match {
        case "X" => "S"
        case "Y" => "R"
        case "Z" => "P"
      }
      case "P" => us match {
        case "X" => "R"
        case "Y" => "P"
        case "Z" => "S"
      }
      case "S" => us match {
        case "X" => "P"
        case "Y" => "S"
        case "Z" => "R"
      }
    }
  }

  override val part1Answer: Int = input.map(hands => (their_choice(hands._1), decrypt_part1(hands._2))).map(game => scoreRound(game._1, game._2)).sum
  override val part2Answer: Int = input.map(hands => (their_choice(hands._1), decrypt_part2(their_choice(hands._1), hands._2))).map(game => scoreRound(game._1, game._2)).sum
}


