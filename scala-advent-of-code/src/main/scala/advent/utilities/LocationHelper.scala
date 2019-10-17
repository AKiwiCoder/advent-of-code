package advent.utilities

import scala.annotation.tailrec

abstract class Facing
case class North() extends Facing
case class West() extends Facing
case class East() extends Facing
case class South() extends Facing

abstract class Turn
case class Left() extends Turn
case class Right() extends Turn

case class Point(y: Int, x: Int)

object LocationHelper {
  def turn(facing: Facing, turn: Turn): Facing =
    facing match {
      case North() => turn match {
        case Left() => West()
        case Right() => East()
      }
      case West() => turn match {
        case Left() => South()
        case Right() => North()
      }
      case East() => turn match {
        case Left() => North()
        case Right() => South()
      }
      case South() => turn match {
        case Left() => East()
        case Right() => West()
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
      case North() => Point(start.y - 1, start.x)
      case West() => Point(start.y, start.x - 1)
      case East() => Point(start.y, start.x + 1)
      case South() => Point(start.y + 1, start.x)
    }
}
