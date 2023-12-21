package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec

class Day21StepCounter(filename : String, partOneSteps : Int, partTwoSteps: Int) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename)

  private val maxY = input.length
  private val maxX = input.head.length

  private val startY = input.takeWhile(!_.contains("S")).length
  private val startX = input.find(_.contains("S")).get.indexOf("S")

  @tailrec
  private def walk(maxSteps : Int, todo : List[(Point, Int)], visited : Map[Int, Set[Point]]) : Set[Point] = {
    if (todo.isEmpty) {
      visited(maxSteps)
    } else {
      val (next, best) = todo.head

      val gridY = ((next.y % maxY) + maxY) % maxY
      val gridX = ((next.x % maxX) + maxX) % maxX

      if (input(gridY)(gridX) == '#') {
        walk(maxSteps, todo.tail, visited)
      } else if (visited.getOrElse(best, Set()).contains(next)) {
        walk(maxSteps, todo.tail, visited)
      } else {
        val updateEntry = visited.getOrElse(best, Set()) + next
        val newVisited = visited + (best -> updateEntry)
        if (best == maxSteps) {
          walk(maxSteps, todo.tail, newVisited)
        } else {
          val nextSteps = List(
            ( Point(next.y - 1, next.x), best + 1),
            ( Point(next.y + 1, next.x), best + 1),
            ( Point(next.y, next.x - 1), best + 1),
            ( Point(next.y, next.x + 1), best + 1),
          )
          walk(maxSteps, nextSteps ::: todo.tail, newVisited)
        }
      }
    }
  }

  def partTwo() : Long = {
    val total0 = walk(65, List((Point(startY, startX), 0)), Map()).size
    val total1 = walk(327, List((Point(startY, startX), 0)), Map()).size
    val total2 = walk(589, List((Point(startY, startX), 0)), Map()).size

    // We have an geometric progression
    val difference10 = total1 - total0
    val difference21 = total2 - total1

    // So increases at diffDifference each step
    val diffDifference = difference21 - difference10

    // 262 because square is 131 on a side
    val howManyLoops: Long = 26501365 / 262 - 1
    val currentLoopNumber: Long = 589 / 262 - 1

    // How many full squares
    val loopCount: Long = howManyLoops - currentLoopNumber

    // How many edge squares
    val loopTriangles: Long = (howManyLoops * (howManyLoops + 1)) / 2 - (currentLoopNumber * (currentLoopNumber + 1)) / 2

    diffDifference * loopTriangles + difference10 * loopCount + total2
  }

  override val part1Answer: Int = walk(partOneSteps, List((Point(startY, startX), 0)), Map()).size
  override val part2Answer: Long = if (partTwoSteps > 10000) partTwo() else walk(partTwoSteps, List((Point(startY, startX), 0)), Map()).size
}


