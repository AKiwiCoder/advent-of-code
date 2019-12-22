package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class State(offset: BigInt, multiplier: BigInt)

abstract class ShuffleAction {
  def part1(index: Long, length: Long): Long

  def part2(state: State, length: BigInt): State
}

case class DealNewStackAction() extends ShuffleAction {
  override def part1(index: Long, length: Long): Long = {
    (length - index - 1) % length
  }

  override def part2(state: State, length: BigInt): State = {
    val newMultiplier = (state.multiplier * -1).mod(length)
    val newOffset = (state.offset + newMultiplier) % length
    State(newOffset, newMultiplier)
  }
}

case class CutAction(number: Long) extends ShuffleAction {
  override def part1(index: Long, length: Long): Long = {
    val t = (index - number) % length
    if (t < 0) t + length else t
  }

  override def part2(state: State, length: BigInt): State = {
    val newOffset = (state.offset + number * state.multiplier).mod(length)
    State(newOffset, state.multiplier)
  }
}

case class DealWithIncrementAction(number: Long) extends ShuffleAction {
  override def part1(index: Long, length: Long): Long = {
    (index * number) % length
  }

  override def part2(state: State, length: BigInt): State = {
    val newMultiplier = (state.multiplier * BigInt(number).modInverse(length)).mod(length)
    State(state.offset, newMultiplier)
  }
}


class Day22SlamShuffle(filename: String, p1DeckLength: Long, part1Index: Long, p2DeckLength: Long = 0, part2Repeats: Long = 0, part2Index: Long = 0) extends DailyProblem[Long, Long] {
  private val dealNewPattern = "deal into new stack".r
  private val cutPattern = "cut ([-]*[0-9]+)".r
  private val dealNPattern = "deal with increment ([0-9]+)".r

  private def parser(line: String): ShuffleAction = {
    line match {
      case dealNewPattern() => DealNewStackAction()
      case cutPattern(number) => CutAction(number.toLong)
      case dealNPattern(number) => DealWithIncrementAction(number.toLong)
    }
  }

  private val input = FileUtilities.readFile(filename, parser)

  def shuffleCards(deckLength: Long, shuffles: Long, index: Long): Long = {
    // Calculate the offset + multiplier per iteration
    val result = input.foldLeft(State(0, 1))((result, action) => action.part2(result, deckLength))

    // Calculate the increment for the number of iterations
    val increment = result.multiplier.modPow(shuffles, deckLength)

    // Calculate the offset for the number of iterations
    val offset = (result.offset * (1 - increment) * (1 - result.multiplier).modInverse(deckLength)) % deckLength

    // Calculate the position for the index we want
    ((offset + index * increment) % deckLength).toLong
  }

  override val part1Answer: Long = input.foldLeft(part1Index)((idx, action) => action.part1(idx, p1DeckLength))
  override val part2Answer: Long = if (p2DeckLength > 0) shuffleCards(p2DeckLength, part2Repeats, part2Index) else 0
}


