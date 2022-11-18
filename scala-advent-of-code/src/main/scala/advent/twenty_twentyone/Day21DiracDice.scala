package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day21DiracDice(filename: String) extends DailyProblem[Int, Long] {

  private val PATTERN = "Player ([0-9]) starting position: ([0-9])".r

  private val input = FileUtilities.readFile(filename).map {
    case PATTERN(player, position) => (player.toInt, position.toInt)
  }

  @tailrec
  private def limit(value: Int, lim: Int): Int = {
    if (value > lim) limit(value - lim, lim) else value
  }

  private def play(): Int = {
    @tailrec
    def run(playerOnePosition: Int, playerTwoPosition: Int, playerOneScore: Int, playerTwoScore: Int, moveCount: Int, dice: Int, winningScore: Int): Int = {
      val roll1 = limit(dice + 1, 100)
      val roll2 = limit(roll1 + 1, 100)
      val roll3 = limit(roll2 + 1, 100)
      val roll4 = limit(roll3 + 1, 100)
      val roll5 = limit(roll4 + 1, 100)
      val roll6 = limit(roll5 + 1, 100)

      val newPlayerOnePosition = limit(playerOnePosition + roll1 + roll2 + roll3, 10)
      val newPlayerOneScore = playerOneScore + newPlayerOnePosition

      if (newPlayerOneScore >= winningScore) {
        playerTwoScore * (moveCount + 3)
      } else {
        val newPlayerTwoPosition = limit(playerTwoPosition + roll4 + roll5 + roll6, 10)
        val newPlayerTwoScore = playerTwoScore + newPlayerTwoPosition

        if (newPlayerTwoScore >= winningScore) {
          playerOneScore * (moveCount + 6)
        } else {
          run(newPlayerOnePosition, newPlayerTwoPosition, newPlayerOneScore, newPlayerTwoScore, moveCount + 6, (roll6 % 100), winningScore)
        }
      }
    }

    run(input(0)._2, input(1)._2, 0, 0, 0, 100, 1000)
  }

  private val ROLLS = (for (d1 <- (1 to 3); d2 <- (1 to 3); d3 <- (1 to 3)) yield (d1 + d2 + d3)).groupBy(identity).view.mapValues(_.size).toMap

  private def playQuantum(): (Long, Long) = {
    def calculateTimesEachPathTaken(tick : Int, playerOnePos : Int, playerTwoPos : Int, playerOneScore : Int, playerTwoScore : Int, playerOne : Boolean, count : Long) : (Long,Long) = {
      if (playerOneScore >= 21) {
        (count, 0L)
      } else if (playerTwoScore >= 21) {
        (0L, count)
      } else {
        ROLLS.foldLeft((0L, 0L))((wins, entry) => {
          val (dieSum, dieCount) = entry
          val (pos1, pos2, score1, score2) = if (playerOne) {
            val p = limit(playerOnePos + dieSum, 10)
            (p, playerTwoPos, playerOneScore + p, playerTwoScore)
          } else {
            val p = limit(playerTwoPos + dieSum, 10)
            (playerOnePos, p, playerOneScore, playerTwoScore + p)
          }
          val (p1win, p2win) = calculateTimesEachPathTaken(tick + 1, pos1, pos2, score1, score2, !playerOne, count * dieCount)
          (wins._1 + p1win, wins._2 + p2win)
        })
      }
    }

    calculateTimesEachPathTaken(0, input(0)._2, input(1)._2, 0, 0, true, 1)
  }

  val results = playQuantum()

  override val part1Answer: Int = play()
  override val part2Answer: Long = Math.max(results._1, results._2)
}


