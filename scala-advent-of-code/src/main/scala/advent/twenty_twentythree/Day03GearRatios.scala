package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day03GearRatios(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(line => line.toList)

  private def check(x: Int, y: Int): Boolean = {
    if (x < 0 || y < 0) false
    else if (x >= input.head.length || y >= input.length) false
    else {
      if (input(y)(x) == '.' || input(y)(x).isDigit) {
        false
      }
      else
        true
    }
  }

  @tailrec
  private def scanPartOne(x: Int, y: Int, acc: String, isPossiblePart: Boolean, parts: List[String]): List[String] = {
    if (y >= input.length) {
      parts
    } else {
      if (x >= input(y).length) {
        if (isPossiblePart && acc.length > 0) {
          scanPartOne(0, y + 1, "", false, acc :: parts)
        } else {
          scanPartOne(0, y + 1, "", false, parts)
        }
      } else {
        val c = input(y)(x)
        if (c == '.') {
          if (isPossiblePart && acc.length > 0) {
            scanPartOne(x + 1, y, "", false, acc :: parts)
          } else {
            scanPartOne(x + 1, y, "", false, parts)
          }
        } else if (c.isDigit) {
          scanPartOne(x + 1, y, acc + c, isPossiblePart || check(x, y + 1) || check(x, y - 1) || check(x + 1, y + 1) || check(x, y + 1) || check(x - 1, y + 1) || check(x + 1, y - 1) || check(x, y - 1) || check(x - 1, y - 1), parts)
        } else {
          if (acc.length > 0) {
            scanPartOne(x + 1, y, "", true, acc :: parts)
          } else {
            scanPartOne(x + 1, y, "", true, parts)
          }
        }
      }
    }
  }

  def scanLeft(y: Int, x: Int, acc: String): String = {
    if (x < 0) {
      acc
    } else {
      if (input(y)(x).isDigit)
        scanLeft(y, x - 1, input(y)(x) + acc)
      else
        acc
    }
  }

  def scanRight(y: Int, x: Int, acc: String): String = {
    println(" ScanRight " + y + " " + x + " " + acc)
    if (x >= input(0).length) {
      acc
    } else {
      if (input(y)(x).isDigit)
        scanRight(y, x + 1, acc + input(y)(x))
      else
        acc
    }
  }

  def number(y: Int, x: Int): String = {
    if (y < 0 || x < 0 || x >= input.head.length || y >= input.length) {
      ""
    } else {
      if (input(y)(x).isDigit) {
        scanLeft(y, x - 1, "") + input(y)(x) + scanRight(y, x + 1, "")
      } else {
        ""
      }
    }
  }

  @tailrec
  private def scanPartTwo(x: Int, y: Int, gears: List[Int]): List[Int] = {
    if (y >= input.length) {
      gears
    } else {
      if (x >= input(y).length) {
        scanPartTwo(0, y + 1, gears)
      } else {
        val c = input(y)(x)
        if (c == '*') {
          println("Found Star " + y + " " + x)
          val numAbove = number(y-1, x)
          val numBelow = number(y+1, x)
          val numLeft = number(y, x-1)
          val numRight = number(y, x+1)

          val numTopLeft = if (numAbove.isEmpty) number(y - 1, x - 1) else ""
          val numTopRight = if (numAbove.isEmpty) number(y - 1, x + 1) else ""
          val numBottomLeft = if (numBelow.isEmpty) number(y + 1, x - 1) else ""
          val numBottomRight = if (numBelow.isEmpty) number(y + 1, x + 1) else ""

          val validParts = List(numAbove, numBelow, numLeft, numRight, numTopLeft, numTopRight, numBottomLeft, numBottomRight).filter(l => l.nonEmpty)

          if (validParts.length == 2) {
            println("Valid " + validParts)
            val num = validParts.head.toInt * validParts(1).toInt
            scanPartTwo(x + 1, y, num :: gears)
          } else {
            scanPartTwo(x + 1, y, gears)
          }
        } else {
          scanPartTwo(x + 1, y, gears)
        }
      }
    }
  }


  println(scanPartTwo(0, 0, List()))

  override val part1Answer: Int = scanPartOne(0, 0, "", false, List()).map(_.toInt).sum
  override val part2Answer: Int = scanPartTwo(0, 0, List()).sum
}


