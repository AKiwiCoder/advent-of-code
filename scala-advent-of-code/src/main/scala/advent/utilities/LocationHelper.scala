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

case class Point(y: Int, x: Int)

object LocationHelper {
  def turn(facing: Facing, turn: Turn): Facing =
    facing match {
      case FacingNorth() => turn match {
        case TurnLeft() => FacingWest()
        case TurnRight() => FacingEast()
      }
      case FacingWest() => turn match {
        case TurnLeft() => FacingSouth()
        case TurnRight() => FacingNorth()
      }
      case FacingEast() => turn match {
        case TurnLeft() => FacingNorth()
        case TurnRight() => FacingSouth()
      }
      case FacingSouth() => turn match {
        case TurnLeft() => FacingEast()
        case TurnRight() => FacingWest()
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
