package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point, TurnLeft, TurnRight}

abstract class KeypadMove
case class KeypadMoveUp() extends KeypadMove
case class KeypadMoveDown() extends KeypadMove
case class KeypadMoveLeft() extends KeypadMove
case class KeypadMoveRight() extends KeypadMove

class Day02BathroomSecurity(filename: String) extends DailyProblem[String, String] {
  private val keypadPart1: Array[Array[Char]] = Array[Array[Char]]( //
    Array('*', '*', '*', '*', '*'), //
    Array('*', '1', '2', '3', '*'), //
    Array('*', '4', '5', '6', '*'), //
    Array('*', '7', '8', '9', '*'), //
    Array('*', '*', '*', '*', '*'))

  private val keypadPart2: Array[Array[Char]] = Array[Array[Char]](
    Array('*', '*', '*', '*', '*', '*', '*'), //
    Array('*', '*', '*', '1', '*', '*', '*'), //
    Array('*', '*', '2', '3', '4', '*', '*'), //
    Array('*', '5', '6', '7', '8', '9', '*'), //
    Array('*', '*', 'A', 'B', 'C', '*', '*'), //
    Array('*', '*', '*', 'D', '*', '*', '*'), //
    Array('*', '*', '*', '*', '*', '*', '*'))

  private def parser(line: String): Seq[KeypadMove] = line.map {
    case 'U' => KeypadMoveUp()
    case 'D' => KeypadMoveDown()
    case 'L' => KeypadMoveLeft()
    case 'R' => KeypadMoveRight()
    case c => throw new IllegalArgumentException("Unknown character " + c)
  }

  private val moves = FileUtilities.readFile(filename, parser)

  def check(keypad: Array[Array[Char]], oldPoint: Point, newPoint: Point): Point = {
    if (keypad(newPoint.y)(newPoint.x) == '*') oldPoint else newPoint
  }

  def walkMoves(moves: Seq[Seq[KeypadMove]], keypad: Array[Array[Char]], fivePos: Point): String = {

    def line(remaining: Seq[KeypadMove], current: Point): (Char, Point) = {
      val p = remaining.foldLeft(current)((acc, move) => {
        move match {
          case KeypadMoveUp() => check(keypad, acc, Point(acc.y - 1, acc.x))
          case KeypadMoveDown() => check(keypad, acc, Point(acc.y + 1, acc.x))
          case KeypadMoveLeft() => check(keypad, acc, Point(acc.y, acc.x - 1))
          case KeypadMoveRight() => check(keypad, acc, Point(acc.y, acc.x + 1))
        }
      })
      (keypad(p.y)(p.x), p)
    }

    moves.foldLeft[(String, Point)](("", fivePos))((acc, move) => {
      val res = line(move, acc._2)
      (acc._1 + res._1, res._2)
    })._1
  }

  override val part1Answer: String = walkMoves(moves, keypadPart1, Point(2, 2))
  override val part2Answer: String = walkMoves(moves, keypadPart2, Point(3, 1))
}
