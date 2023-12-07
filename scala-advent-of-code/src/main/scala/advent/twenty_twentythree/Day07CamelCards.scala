package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import java.awt.{Color, JobAttributes}
import java.awt.JobAttributes.MultipleDocumentHandlingType
import scala.annotation.tailrec

case class Hand(cards: String, hand: Int, bid: Int)

class Day07CamelCards(filename: String) extends DailyProblem[Int, Int] {

  private def parse(line: String, jokerWild: Boolean): Hand = {
    val bits = line.split(" ")
    val cards = bits.head
    val bid = bits(1).toInt

    val cardMap = cards.groupBy(identity).view.mapValues(_.length).toMap

    val counts =
      if (jokerWild) {
        val cardWithoutJoker = cardMap.filterNot(entry => entry._1 == 'J')
        val countsWithoutJoker = cardWithoutJoker.values.toList.sorted.reverse
        if (countsWithoutJoker.isEmpty)
          List(5) // Cards were all jokers
        else
          (countsWithoutJoker.head + cardMap.getOrElse('J', 0)) :: countsWithoutJoker.tail
      } else {
        cardMap.values.toList.sorted.reverse
      }

    val hand = if (counts.head == 5) {
      ("FiveOfAKind", 6)
    } else if (counts.head == 4) {
      ("FourOfAKind", 5)
    } else if (counts.head == 3 && counts(1) == 2) {
      ("FullHouse", 4)
    } else if (counts.head == 3) {
      ("ThreeOfAKind", 3)
    } else if (counts.head == 2 && counts(1) == 2) {
      ("TwoPair", 2)
    } else if (counts.head == 2) {
      ("OnePair", 1)
    } else {
      ("HighCard", 0)
    }

    Hand(cards, hand._2, bid)
  }

  private val input = FileUtilities.readFile(filename)

  private def cardValPart1(c: Char): Int = {
    c match {
      case 'A' => 14
      case 'K' => 13
      case 'Q' => 12
      case 'J' => 11
      case 'T' => 10
      case '9' => 9
      case '8' => 8
      case '7' => 7
      case '6' => 6
      case '5' => 5
      case '4' => 4
      case '3' => 3
      case '2' => 2
      case '1' => 1
    }
  }

  private def cardValPart2(c: Char): Int = {
    c match {
      case 'A' => 14
      case 'K' => 13
      case 'Q' => 12
      case 'T' => 10
      case '9' => 9
      case '8' => 8
      case '7' => 7
      case '6' => 6
      case '5' => 5
      case '4' => 4
      case '3' => 3
      case '2' => 2
      case '1' => 1
      case 'J' => 0
    }
  }


  private def compareCard(lhs: Char, rhs: Char, cardVal: Char => Int): Option[Boolean] = {
    if (lhs == rhs) {
      None
    } else {
      Some(cardVal(lhs) < cardVal(rhs))
    }
  }

  private def compareCards(lhs: String, rhs: String, cardVal: Char => Int): Boolean = {
    val none: Option[Boolean] = None
    (0 until 5).foldLeft(none)((opt, idx) => {
      opt match {
        case Some(_) => opt
        case None => compareCard(lhs(idx), rhs(idx), cardVal)
      }
    }).getOrElse(false)
  }

  private def compareHands(lhs: Hand, rhs: Hand, cardVal: Char => Int): Boolean = {
    if (lhs.hand < rhs.hand) {
      true
    } else if (lhs.hand == rhs.hand) {
      compareCards(lhs.cards, rhs.cards, cardVal)
    } else {
      false
    }
  }

  def part1(lhs: Hand, rhs: Hand): Boolean = {
    compareHands(lhs, rhs, cardValPart1)
  }

  def part2(lhs: Hand, rhs: Hand): Boolean = {
    compareHands(lhs, rhs, cardValPart2)
  }

  private def calculateWinnings(jokersWild: Boolean): Int = {
    val hands = input.map(line => parse(line, jokersWild))
    val sortedHands = if (jokersWild)
      hands.sortWith(part2)
    else
      hands.sortWith(part1)
    sortedHands.zipWithIndex.map(pair => pair._1.bid * (pair._2 + 1)).sum
  }

  override val part1Answer: Int = calculateWinnings(jokersWild = false)
  override val part2Answer: Int = calculateWinnings(jokersWild = true)
}


