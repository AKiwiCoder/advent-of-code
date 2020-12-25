package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day25ComboBreaker(filename: String) extends DailyProblem[Long, Int] {

  private val input = FileUtilities.readFile(filename, _.toInt)

  private val cardPublicKey = input(0)
  private val doorPublicKey = input(1)

  def part1(): Long = {
    @tailrec
    def determineLoopSize(target: Int, i: Int, count: Int): Int = {
      if (i == target) {
        count
      } else {
        determineLoopSize(target, (i * 7) % 20201227, count + 1)
      }
    }

    @tailrec
    def calculateKey(key: Int, current: Long, count: Int): Long = {
      if (count == 0) {
        current
      } else {
        calculateKey(key, (current * key) % 20201227, count - 1)
      }
    }

    val cardLoopSize = determineLoopSize(cardPublicKey, 1, 0)
    val doorLoopSize = determineLoopSize(doorPublicKey, 1, 0)

    val cardKey = calculateKey(cardPublicKey, 1, doorLoopSize)
    val doorKey = calculateKey(doorPublicKey, 1, cardLoopSize)

    cardKey
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Int = 0
}