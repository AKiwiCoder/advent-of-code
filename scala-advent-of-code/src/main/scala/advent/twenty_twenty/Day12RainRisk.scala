package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

abstract class Facing()

case class East() extends Facing

case class West() extends Facing

case class South() extends Facing

case class North() extends Facing

class Day12RainRisk(filename: String) extends DailyProblem[Long, Long] {
  private val input = FileUtilities.readFile(filename)

  def left(facing: Facing, count: Int): Facing = {
    count match {
      case 90 => facing match {
        case North() => West()
        case South() => East()
        case West() => South()
        case East() => North()
      }
      case 180 => facing match {
        case North() => South()
        case South() => North()
        case West() => East()
        case East() => West()
      }
      case 270 => facing match {
        case North() => East()
        case South() => West()
        case West() => North()
        case East() => South()
      }
      case 360 => facing
    }
  }

  def right(facing: Facing, count: Int): Facing = {
    count match {
      case 90 => facing match {
        case North() => East()
        case South() => West()
        case West() => North()
        case East() => South()
      }
      case 180 => facing match {
        case North() => South()
        case South() => North()
        case West() => East()
        case East() => West()
      }
      case 270 => facing match {
        case North() => West()
        case South() => East()
        case West() => South()
        case East() => North()
      }
      case 360 => facing
    }
  }

  def forward(current: Point2d, facing: Facing, count: Int): Point2d = {
    facing match {
      case North() => Point2d(current.x, current.y + count)
      case South() => Point2d(current.x, current.y - count)
      case West() => Point2d(current.x - count, current.y)
      case East() => Point2d(current.x + count, current.y)
    }
  }

  def part1(): Long = {
    @tailrec
    def walk(current: Point2d, facing: Facing, path: List[String]): Long = {
      if (path == Nil) {
        Math.abs(current.x) + Math.abs(current.y)
      } else {
        val move = path.head
        val m = move.head
        val count = move.tail.toInt

        m match {
          case 'N' => walk(Point2d(current.x, current.y + count), facing, path.tail)
          case 'S' => walk(Point2d(current.x, current.y - count), facing, path.tail)
          case 'E' => walk(Point2d(current.x + count, current.y), facing, path.tail)
          case 'W' => walk(Point2d(current.x - count, current.y), facing, path.tail)
          case 'L' => walk(current, left(facing, count), path.tail)
          case 'R' => walk(current, right(facing, count), path.tail)
          case 'F' => walk(forward(current, facing, count), facing, path.tail)
        }
      }
    }

    walk(Point2d(0, 0), East(), input)
  }

  def rotateLeft(point: Point2d, count: Int): Point2d = {
    count match {
      case 90 => Point2d(-point.y, point.x)
      case 180 => Point2d(-point.x, -point.y)
      case 270 => Point2d(point.y, -point.x)
      case 360 => point
    }
  }

  def rotateRight(point: Point2d, count: Int): Point2d = {
    count match {
      case 90 => Point2d(point.y, -point.x)
      case 180 => Point2d(-point.x, -point.y)
      case 270 => Point2d(-point.y, point.x)
      case 360 => point
    }
  }

  def part2(): Long = {
    @tailrec
    def walk(ship: Point2d, waypoint: Point2d, facing: Facing, path: List[String]): Long = {
      if (path == Nil) {
        Math.abs(ship.x ) + Math.abs(ship.y)
      } else {
        val move = path.head
        val m = move.head
        val count = move.tail.toInt

        m match {
          case 'N' => walk(ship, Point2d(waypoint.x, waypoint.y + count), facing, path.tail)
          case 'S' => walk(ship, Point2d(waypoint.x, waypoint.y - count), facing, path.tail)
          case 'E' => walk(ship, Point2d(waypoint.x + count, waypoint.y), facing, path.tail)
          case 'W' => walk(ship, Point2d(waypoint.x - count, waypoint.y), facing, path.tail)
          case 'L' => walk(ship, rotateLeft(waypoint, count), facing,path.tail)
          case 'R' => walk(ship, rotateRight(waypoint, count), facing,path.tail)
          case 'F' => walk(Point2d(ship.x + waypoint.x * count, ship.y + waypoint.y * count), waypoint, facing, path.tail)
        }
      }
    }

    walk(Point2d(0, 0), Point2d(10, 1), East(), input)
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Long = part2()
}


