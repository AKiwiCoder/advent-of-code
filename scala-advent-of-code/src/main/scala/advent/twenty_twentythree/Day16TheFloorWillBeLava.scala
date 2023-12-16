package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.LocationHelper.step
import advent.utilities._

import scala.annotation.tailrec

class Day16TheFloorWillBeLava(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  private val maxX = input.head.length
  private val maxY = input.length

  private val N = FacingNorth()
  private val E = FacingEast()
  private val W = FacingWest()
  private val S = FacingSouth()

  def isOffMap(p: Point): Boolean = {
    p.y < 0 || p.x < 0 || p.y >= maxY || p.x >= maxX
  }

  @tailrec
  private def walk(beams: List[(Point, Facing)], alreadyVisited: Set[(Point, Facing)], acc: Set[Point]): Set[Point] = {
    if (beams.isEmpty) {
      acc
    } else {
      val beam = beams.head
      val newPosition = step(beam._1, beam._2)
      if (isOffMap(newPosition) || alreadyVisited(beam)) {
        walk(beams.tail, alreadyVisited, acc)
      } else {
        val c = input(newPosition.y)(newPosition.x)
        val newBeams: List[(Point, Facing)] = c match {
          case '.' => List((newPosition, beam._2))
          case '/' => beam._2 match {
            case N => List((newPosition, E))
            case S => List((newPosition, W))
            case E => List((newPosition, N))
            case W => List((newPosition, S))
          }
          case '\\' => beam._2 match {
            case N => List((newPosition, W))
            case S => List((newPosition, E))
            case E => List((newPosition, S))
            case W => List((newPosition, N))
          }
          case '-' => beam._2 match {
            case N => List((newPosition, W), (newPosition, E))
            case S => List((newPosition, W), (newPosition, E))
            case E => List((newPosition, beam._2))
            case W => List((newPosition, beam._2))
          }
          case '|' => beam._2 match {
            case N => List((newPosition, beam._2))
            case S => List((newPosition, beam._2))
            case E => List((newPosition, N), (newPosition, S))
            case W => List((newPosition, N), (newPosition, S))
          }
        }
        walk(newBeams ::: beams, alreadyVisited + beam, acc + newPosition)
      }
    }
  }

  private val bestTopBottom = input.indices.map(y => {
    val startFromLeft = walk(List[(Point, Facing)]((Point(y, -1), E)), Set(), Set()).size
    val startFromRight = walk(List[(Point, Facing)]((Point(y, maxX), W)), Set(), Set()).size
    Math.max(startFromLeft, startFromRight)
  }).max
  private val bestLeftRight = input.head.indices.map(x => {
    val startFromTop = walk(List[(Point, Facing)]((Point(-1, x), S)), Set(), Set()).size
    val startFromBottom = walk(List[(Point, Facing)]((Point(maxY, x), N)), Set(), Set()).size
    Math.max(startFromTop, startFromBottom)
  }).max

  override val part1Answer: Int = walk(List[(Point, Facing)]((Point(0, -1), E)), Set(), Set()).size
  override val part2Answer: Int = Math.max(bestLeftRight, bestTopBottom)
}


