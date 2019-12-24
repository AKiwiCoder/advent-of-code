package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec

class Day24PlanetOfDiscord(filename: String, part2Steps: Int) extends DailyProblem[Int, Int] {

  case class PointWithLevel(level: Int, y: Int, x: Int)

  private val inputMapPart1 = FileUtilities.readFile(filename).zipWithIndex.flatMap(pair => pair._1.zipWithIndex.map(e => (Point(pair._2, e._2)) -> e._1)).toMap
  private val inputMapPart2 = FileUtilities.readFile(filename).zipWithIndex.flatMap(pair => pair._1.zipWithIndex.map(e => (PointWithLevel(0, pair._2, e._2)) -> e._1)).toMap

  def dumpMap(map: Map[Point, AnyVal]): String = {
    var result = ""
    for (y <- 0 until 5) {
      for (x <- 0 until 5) {
        val c = map(Point(y, x))
        result += c
      }
      result += "\n"
    }
    println(result);
    result
  }

  def dumpMap(map: Map[PointWithLevel, AnyVal], level: Int): String = {
    var result = ""
    for (y <- 0 until 5) {
      for (x <- 0 until 5) {
        if (y == 2 && x == 2)
          result += '?'
        else
          result += map(PointWithLevel(level, y, x))
      }
      result += "\n"
    }
    result
  }


  def calculateBioDiversity(map: Map[Point, Char]) = {
    val limits = map.keySet.foldLeft((Int.MaxValue, Int.MaxValue, Int.MinValue, Int.MinValue))((a, p) => (Math.min(a._1, p.y), Math.min(a._2, p.x), Math.max(a._3, p.y), Math.max(a._4, p.x)))
    var squareValue = 1
    var result = 0
    for (y <- limits._1 to limits._3) {
      for (x <- limits._2 to limits._4) {
        if (map(Point(y, x)) == '#') {
          result += squareValue
        }
        squareValue *= 2
      }
    }
    result
  }

  def countSquares(current: Map[Point, Char], middle: Point, filter: Char): Int = {
    (if (current.getOrElse(Point(middle.y + 1, middle.x), '.') == filter) 1 else 0) +
      (if (current.getOrElse(Point(middle.y - 1, middle.x), '.') == filter) 1 else 0) +
      (if (current.getOrElse(Point(middle.y, middle.x - 1), '.') == filter) 1 else 0) +
      (if (current.getOrElse(Point(middle.y, middle.x + 1), '.') == filter) 1 else 0)
  }

  @tailrec
  private def calculatePart1(current: Map[Point, Char], seen: Set[Int]): Int = {
    val bio = calculateBioDiversity(current)
    if (seen.contains(bio)) {
      bio
    } else {
      val next = (0 until 5).flatMap(y => (0 until 5).map(x => {
        val here = Point(y, x)
        val currentSquare = current(here)
        val bugCount = countSquares(current, here, '#')

        val newSquare = if (currentSquare == '#' && bugCount != 1)
          '.'
        else if (currentSquare == '.' && (bugCount == 1 || bugCount == 2))
          '#'
        else
          currentSquare

        (here -> newSquare)
      })).toMap
      calculatePart1(next, seen + bio)
    }
  }


  def onlyUsingThisLevel(mY: Int, mX: Int): Boolean = (mY == 1 || mY == 3) && (mX == 1 || mX == 3)

  def onCorner(mY: Int, mX: Int): Boolean = (mY == 0 || mY == 4) && (mX == 0 || mX == 4)

  def onEdge(mY: Int, mX: Int): Boolean = ((mY == 0 || mY == 4) && (mX > 0 && mX < 4)) || ((mY > 0 && mY < 4) && (mX == 0 || mX == 4))

  def countSquaresRecursive(current: Map[PointWithLevel, Char], middle: PointWithLevel, filter: Char): Int = {
    val mL = middle.level
    val mY = middle.y
    val mX = middle.x

    if (onlyUsingThisLevel(mY, mX)) {
      // The ones in this level
      (if (current.getOrElse(PointWithLevel(mL, mY + 1, mX), '.') == filter) 1 else 0) +
        (if (current.getOrElse(PointWithLevel(mL, mY - 1, mX), '.') == filter) 1 else 0) +
        (if (current.getOrElse(PointWithLevel(mL, mY, mX - 1), '.') == filter) 1 else 0) +
        (if (current.getOrElse(PointWithLevel(mL, mY, mX + 1), '.') == filter) 1 else 0)
    } else if (onCorner(mY, mX)) {
      // Is on the corner of this square
      if (mY == 0 && mX == 0)
        (if (current.getOrElse(PointWithLevel(mL + 1, 1, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 2, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 0, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 0), '.') == filter) 1 else 0)
      else if (mY == 0 && mX == 4)
        (if (current.getOrElse(PointWithLevel(mL + 1, 1, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 2, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 0, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 4), '.') == filter) 1 else 0)
      else if (mY == 4 && mX == 0)
        (if (current.getOrElse(PointWithLevel(mL, 3, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 4, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 3, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 2, 1), '.') == filter) 1 else 0)
      else // if (mY == 4 && mX == 4)
        (if (current.getOrElse(PointWithLevel(mL, 3, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 4, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 3, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL + 1, 2, 3), '.') == filter) 1 else 0)
    } else if (onEdge(mY, mX)) {
      // Is on the edge of this square
      if (mY == 0) // TOP
        (if (current.getOrElse(PointWithLevel(mL + 1, 1, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, mX), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 0, mX - 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 0, mX + 1), '.') == filter) 1 else 0)
      else if (mY == 4) // BOTTOM
        (if (current.getOrElse(PointWithLevel(mL + 1, 3, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 3, mX), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 4, mX - 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 4, mX + 1), '.') == filter) 1 else 0)
      else if (mX == 0) // LEFT
        (if (current.getOrElse(PointWithLevel(mL + 1, 2, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY - 1, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY + 1, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY, 1), '.') == filter) 1 else 0)
      else //if (mX == 4) // RIGHT
        (if (current.getOrElse(PointWithLevel(mL + 1, 2, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY - 1, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY + 1, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, mY, 3), '.') == filter) 1 else 0)
    } else {
      // Uses the lower layer
      if (mY == 2 && mX == 1) // ? on RIGHT
        (if (current.getOrElse(PointWithLevel(mL - 1, 0, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 1, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 2, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 3, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 3, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 2, 0), '.') == filter) 1 else 0)
      else if (mY == 2 && mX == 3) // ? on LEFT
        (if (current.getOrElse(PointWithLevel(mL - 1, 0, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 1, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 2, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 3, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 3, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 2, 4), '.') == filter) 1 else 0)
      else if (mY == 1 && mX == 2) // ? on BOTTOM
        (if (current.getOrElse(PointWithLevel(mL - 1, 0, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 0, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 0, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 0, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 0, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 1, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 0, 2), '.') == filter) 1 else 0)
      else // if (mY == 3 && mX == 2) // ? on TOP
        (if (current.getOrElse(PointWithLevel(mL - 1, 4, 0), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 2), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL - 1, 4, 4), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 3, 1), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 3, 3), '.') == filter) 1 else 0) +
          (if (current.getOrElse(PointWithLevel(mL, 4, 2), '.') == filter) 1 else 0)
    }
  }

  @tailrec
  private def calculatePart2(current: Map[PointWithLevel, Char], remaining: Int): Int = {
    if (remaining == 0) {
      current.count(p => p._2 == '#')
    } else {
      val levelLimit = current.map(e => e._1.level)
      val next = for (lvl <- levelLimit.min - 1 to levelLimit.max + 1; y <- 0 until 5; x <- 0 until 5 if !(y == 2 && x == 2))
        yield {
          val here = PointWithLevel(lvl, y, x)
          val currentSquare = current.getOrElse(here, '.')
          val bugCount = countSquaresRecursive(current, here, '#')

          val newSquare = if (currentSquare == '#' && bugCount != 1)
            '.'
          else if (currentSquare == '.' && (bugCount == 1 || bugCount == 2))
            '#'
          else
            currentSquare

          (PointWithLevel(lvl, y, x) -> newSquare)
        }

      calculatePart2(next.toMap, remaining - 1)
    }
  }

  override val part1Answer: Int = calculatePart1(inputMapPart1, Set())
  override val part2Answer: Int = calculatePart2(inputMapPart2, part2Steps)
}


