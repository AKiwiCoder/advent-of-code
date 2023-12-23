package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec
import scala.collection.mutable

class Day23ALongWalk(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  val minY = 0
  val minX = 0
  val maxY = input.length
  val maxX = input.head.length

  val start = Point(0, input.head.indexOf("."))
  val end = Point(maxY - 1, input(maxY - 1).indexOf("."))

  private def isValid(point: Point): Boolean = {
    point.y >= 0 && point.x >= 0 && point.y < maxY && point.x < maxX
  }

  def checkPointAndAddToList(p: Point, useSlope: Boolean, slope: Char, path: (Point, Set[Point]), todo: mutable.Queue[(Point, Set[Point])]): Unit = {
    if (path._2.contains(p)) {
      // Cannot step on path again
    } else if (!isValid(p)) {
      // Cannot move outside map
    } else {
      if (useSlope) {
        if (input(p.y)(p.x) == '.' || input(p.y)(p.x) == slope) {
          // Can move
          todo.addOne((p, path._2 + path._1))
        }
      } else if (input(p.y)(p.x) != '#') {
        todo.addOne((p, path._2 + path._1))
      }
    }
  }

  @tailrec
  private def walkPath(todo: mutable.Queue[(Point, Set[Point])], useSlope: Boolean, finished: Int): Int = {
    if (todo.isEmpty) {
      finished
    } else {
      val path = todo.dequeue()
      if (path._1 == end) {
        if (path._2.size > finished) {
          walkPath(todo, useSlope, path._2.size)
        } else {
          walkPath(todo, useSlope, finished)
        }
      } else {
        val current = path._1

        val north = Point(current.y - 1, current.x)
        val south = Point(current.y + 1, current.x)
        val east = Point(current.y, current.x + 1)
        val west = Point(current.y, current.x - 1)

        checkPointAndAddToList(north, useSlope, '^', path, todo)
        checkPointAndAddToList(south, useSlope, 'v', path, todo)
        checkPointAndAddToList(east, useSlope, '>', path, todo)
        checkPointAndAddToList(west, useSlope, '<', path, todo)

        walkPath(todo, useSlope, finished)
      }
    }
  }

  override val part1Answer: Int = walkPath(mutable.Queue((start, Set())), true, 0)
  override val part2Answer: Int = walkPath(mutable.Queue((start, Set())), false, 0)
}


