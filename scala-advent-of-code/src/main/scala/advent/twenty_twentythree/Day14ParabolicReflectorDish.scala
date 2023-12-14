package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day14ParabolicReflectorDish(filename: String) extends DailyProblem[Int, Int] {
  private case class Position(y: Int, x: Int);

  private val input = FileUtilities.readFile(filename)
  private val rocks = input.zipWithIndex.flatMap(e => e._1.zipWithIndex.map(f => Position(e._2, f._2) -> f._1)).toMap.filter(e => e._2 != '.')

  @tailrec
  private def tiltNorth(start: Map[Position, Char]): Map[Position, Char] = {
    val step = input.indices.foldLeft((start, false))((acc, y) => moveRocksNorth(acc, y))
    if (step._2) {
      tiltNorth(step._1)
    } else {
      step._1
    }
  }

  @tailrec
  private def tiltSouth(start: Map[Position, Char]): Map[Position, Char] = {
    val step = input.indices.foldLeft((start, false))((acc, y) => moveRocksSouth(acc, y))
    if (step._2) {
      tiltSouth(step._1)
    } else {
      step._1
    }
  }

  @tailrec
  private def tiltWest(start: Map[Position, Char]): Map[Position, Char] = {
    val step = input.indices.foldLeft((start, false))((acc, y) => moveRocksWest(acc, y))
    if (step._2) {
      tiltWest(step._1)
    } else {
      step._1
    }
  }

  @tailrec
  private def tiltEast(start: Map[Position, Char]): Map[Position, Char] = {
    val step = input.indices.foldLeft((start, false))((acc, y) => moveRocksEast(acc, y))
    if (step._2) {
      tiltEast(step._1)
    } else {
      step._1
    }
  }

  private def moveRocksNorth(acc: (Map[Position, Char], Boolean), y: Int) : (Map[Position, Char], Boolean) = {
    val (oldMap, hasChanged) = acc
    if (y == 0) {
      acc
    } else {
      val newMap = oldMap.map(e => if (e._1.y == y && e._2 == 'O') {
        val below = Position(y - 1, e._1.x)
        if (oldMap.getOrElse(below,'.') == '.') {
          below -> 'O'
        } else {
          e
        }
      } else {
        e
      })
      (newMap, hasChanged || !newMap.equals(oldMap))
    }
  }

  private def moveRocksSouth(acc: (Map[Position, Char], Boolean), y: Int): (Map[Position, Char], Boolean) = {
    val (oldMap, hasChanged) = acc
    if (y == input.length -1 ) {
      acc
    } else {
      val newMap = oldMap.map(e => if (e._1.y == y && e._2 == 'O') {
        val above = Position(y + 1, e._1.x)
        if (oldMap.getOrElse(above, '.') == '.') {
          above -> 'O'
        } else {
          e
        }
      } else {
        e
      })
      (newMap, hasChanged || !newMap.equals(oldMap))
    }
  }

  private def moveRocksWest(acc: (Map[Position, Char], Boolean), x: Int): (Map[Position, Char], Boolean) = {
    val (oldMap, hasChanged) = acc
    if (x == 0) {
      acc
    } else {
      val newMap = oldMap.map(e => if (e._1.x == x && e._2 == 'O') {
        val left = Position(e._1.y, x - 1)
        if (oldMap.getOrElse(left, '.') == '.') {
          left -> 'O'
        } else {
          e
        }
      } else {
        e
      })
      (newMap, hasChanged || !newMap.equals(oldMap))
    }
  }

  private def moveRocksEast(acc: (Map[Position, Char], Boolean), x: Int): (Map[Position, Char], Boolean) = {
    val (oldMap, hasChanged) = acc
    if (x == input.head.length - 1) {
      acc
    } else {
      val newMap = oldMap.map(e => if (e._1.x == x && e._2 == 'O') {
        val right = Position(e._1.y, x + 1)
        if (oldMap.getOrElse(right, '.') == '.') {
          right -> 'O'
        } else {
          e
        }
      } else {
        e
      })
      (newMap, hasChanged || !newMap.equals(oldMap))
    }
  }


  @tailrec
  private def spinCycle(start: Map[Position, Char], iteration : Int, cache : Map[Map[Position, Char], Int]): Map[Position, Char] = {
    val tn = tiltNorth(start)
    val tw = tiltWest(tn)
    val ts = tiltSouth(tw)
    val te = tiltEast(ts)

    if (cache.contains(te)) {
      val cycleLength = iteration - cache(te)
      val index = (1000000000 - cache(te)) % cycleLength + cache(te) - 1
      cache.find(e => e._2 == index).get._1
    } else {
      spinCycle(te, iteration + 1, cache + (te -> iteration))
    }
  }

  private val partTwo = spinCycle(rocks, 0, Map(rocks -> 0))

  override val part1Answer: Int = tiltNorth(rocks).filter(e => e._2 == 'O').map(input.length - _._1.y).sum
  override val part2Answer: Int = partTwo.filter(e => e._2 == 'O').map(input.length - _._1.y).sum
}


