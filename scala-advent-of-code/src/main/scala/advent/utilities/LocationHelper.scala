package advent.utilities

import scala.annotation.tailrec

abstract class Facing
case class FacingNorth() extends Facing
case class FacingWest() extends Facing
case class FacingEast() extends Facing
case class FacingSouth() extends Facing

abstract class Turn
case class TurnLeft() extends Turn
case class TurnRight() extends Turn
case class TurnNone() extends Turn
case class TurnReverse() extends Turn

case class Point(y: Int, x: Int)

object Point {
  def manhattanDistance(current: Point, start: Point): Int = Math.abs(start.y - current.y) + Math.abs(start.x - current.x)

  def distance(a: Point, b: Point): Double = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))

  def calculateAngle(a: Point, b: Point, c: Point): Double = {
    val deg = Math.toDegrees(Math.atan2(c.y - a.y, c.x - a.x) - Math.atan2(b.y - a.y, b.x - a.x))
    if (deg < 0) deg + 360 else deg
  }

  def isBlocking(origin: Point, target: Point, blocker: Point): Boolean = {
    Math.abs(Point.distance(origin, blocker) + Point.distance(target, blocker) - Point.distance(origin, target)) < 0.000001
  }
}

object LocationHelper {
  def turn(facing: Facing, turn: Turn): Facing =
    facing match {
      case FacingNorth() => turn match {
        case TurnLeft() => FacingWest()
        case TurnRight() => FacingEast()
        case TurnNone() => facing
        case TurnReverse() => FacingSouth()
      }
      case FacingWest() => turn match {
        case TurnLeft() => FacingSouth()
        case TurnRight() => FacingNorth()
        case TurnNone() => facing
        case TurnReverse() => FacingEast()
      }
      case FacingEast() => turn match {
        case TurnLeft() => FacingNorth()
        case TurnRight() => FacingSouth()
        case TurnNone() => facing
        case TurnReverse() => FacingWest()
      }
      case FacingSouth() => turn match {
        case TurnLeft() => FacingEast()
        case TurnRight() => FacingWest()
        case TurnNone() => facing
        case TurnReverse() => FacingNorth()
      }
    }

  @tailrec
  def steps(current: Point, facing: Facing, count: Int, steps: List[Point]): (Point, List[Point]) = {
    if (count == 0) {
      (current, steps)
    } else {
      val nextPos = step(current, facing)
      LocationHelper.steps(nextPos, facing, count - 1, nextPos :: steps)
    }
  }

  def step(start: Point, facing: Facing): Point =
    facing match {
      case FacingNorth() => Point(start.y - 1, start.x)
      case FacingWest() => Point(start.y, start.x - 1)
      case FacingEast() => Point(start.y, start.x + 1)
      case FacingSouth() => Point(start.y + 1, start.x)
    }
}
