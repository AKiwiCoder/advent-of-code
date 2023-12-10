package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day10PipeMaze(filename: String, startReplacement : Char) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(line => line.toList)

  private val startStep1 = input.map(_.zipWithIndex).zipWithIndex
  private val startStep2 = startStep1.find(a => a._1.exists(b => b._1 == 'S')).get
  private val startStep3 = startStep2._1.find(a => a._1 == 'S').get

  private val startX = startStep3._2
  private val startY = startStep2._2

  def north(y: Int, x: Int): Char = if (y > 0) input(y - 1)(x) else '.'

  def south(y: Int, x: Int): Char = if (y < input.length - 1) input(y + 1)(x) else '.'

  def west(y: Int, x: Int): Char = if (x > 0) input(y)(x - 1) else '.'

  def east(y: Int, x: Int): Char = if (x < input.head.length - 1) input(y)(x + 1) else '.'

  private def calculateConnectingSquares(x: Int, y: Int, dist: Int): Set[(Int, Int, Int)] = {
    val empty = Set[(Int, Int, Int)]()

    val newSet1: Set[(Int, Int, Int)] = north(y, x) match {
      case '|' | '7' | 'F' => empty + ((y - 1, x, dist + 1))
      case _ => empty
    }

    val newSet2: Set[(Int, Int, Int)] = south(y, x) match {
      case '|' | 'L' | 'J' => newSet1 + ((y + 1, x, dist + 1))
      case _ => newSet1
    }

    val newSet3: Set[(Int, Int, Int)] = east(y, x) match {
      case '-' | 'J' | '7' => newSet2 + ((y, x + 1, dist + 1))
      case _ => newSet2
    }

    val newSet4 = west(y, x) match {
      case '-' | 'L' | 'F' => newSet3 + ((y, x - 1, dist + 1))
      case _ => newSet3
    }
    newSet4
  }

  def isValid(y: Int, x: Int): Boolean = y >= 0 && x >= 0 && y < input.length && x < input.head.length

  @tailrec
  private def search(pending: Set[(Int, Int, Int)], bestSoFar: Map[(Int, Int), Int]): Map[(Int, Int), Int] = {
    if (pending.isEmpty) {
      bestSoFar
    } else {
      val (y, x, dist) = pending.head

      if (isValid(y, x) && dist < bestSoFar.getOrElse((y, x), Integer.MAX_VALUE)) {
        val thisSquare = input(y)(x)
        val newPending = thisSquare match {
          case 'S' => calculateConnectingSquares(x, y, dist)
          case '|' => Set((y - 1, x, dist + 1), (y + 1, x, dist + 1))
          case '-' => Set((y, x - 1, dist + 1), (y, x + 1, dist + 1))
          case 'L' => Set((y - 1, x, dist + 1), (y, x + 1, dist + 1))
          case 'J' => Set((y - 1, x, dist + 1), (y, x - 1, dist + 1))
          case '7' => Set((y + 1, x, dist + 1), (y, x - 1, dist + 1))
          case 'F' => Set((y + 1, x, dist + 1), (y, x + 1, dist + 1))
          case '.' => Set()
        }

        search(pending.tail ++ newPending, bestSoFar + ((y, x) -> dist))
      } else {
        search(pending.tail, bestSoFar)
      }
    }
  }

  private val part1 = search(Set((startY, startX, 0)), Map())

  private val inputAsMap = input.indices.flatMap(y => input.head.indices.map(x => (y, x) -> input(y)(x)).toMap).toMap

  private val onlyLoopPipes = inputAsMap.filter(e => part1.contains(e._1)) + ((startY, startX) -> startReplacement)

  private def performLineCrossingCounting(): Int = {
    def lineCross(y: Int): Int = {
      input(y).foldLeft((0, ' ', 0, 0))((acc, _) => {
        val (crossingCount, prevChar, xPos, internalCount) = acc
         if (onlyLoopPipes.contains((y, xPos))) {
          val pipe = onlyLoopPipes((y, xPos))
          if (pipe == 'J' && prevChar == 'L') {
            (crossingCount, '.', xPos + 1, internalCount)
          } else if (pipe == '7' && prevChar == 'F') {
            (crossingCount, '.', xPos + 1, internalCount)
          } else if (pipe == 'J' && prevChar != 'L') {
            (crossingCount+1, '.', xPos + 1, internalCount)
          } else if (pipe == '7' && prevChar != 'F') {
            (crossingCount+1, '.', xPos + 1, internalCount)
          } else if (pipe == 'F' || pipe == '7' || pipe == 'L' || pipe == 'J') {
            (crossingCount, pipe, xPos + 1, internalCount)
          } else if (pipe == '|') {
            (crossingCount + 1, pipe, xPos + 1, internalCount)
          } else {
            val newPrevChar = if (pipe == '-') prevChar else pipe
            (crossingCount, newPrevChar, xPos + 1, internalCount)
          }
        } else {
          if (crossingCount % 2 == 1) {
            (crossingCount, prevChar, xPos + 1, internalCount + 1)
          } else {
            (crossingCount, prevChar, xPos + 1, internalCount)
          }
        }
      })._4
    }

    input.indices.map(y => lineCross(y)).sum
  }

  override val part1Answer: Int = part1.values.max
  override val part2Answer: Int = performLineCrossingCounting()
}


