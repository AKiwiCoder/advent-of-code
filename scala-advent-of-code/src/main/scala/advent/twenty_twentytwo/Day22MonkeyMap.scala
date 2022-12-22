package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day22MonkeyMap(filename: String, sideSize : Int, sides : List[(Int,Int,Int,Int,Int)], example : Boolean) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  private val map = input.take(input.size - 2).map(_.toList)
  private val path = input.last.replace("R", "\nR\n").replace("L", "\nL\n").split("\n").toList.filterNot(_.isEmpty)

  private val cube = sides.map(s => s._1 -> (s._3 to s._5).foldLeft(List[List[Char]]())((acc, y) => map(y).slice(s._2, s._4+1) :: acc).reverse).toMap

  private val part2MaxX = sideSize - 1
  private val part2MaxY = sideSize - 1

  private val NUMBER = "([0-9]+)".r

  private val RIGHT = 0
  private val DOWN = 1
  private val LEFT = 2
  private val UP = 3

  private val LEFT_TURNS = Map(RIGHT -> UP, DOWN -> RIGHT, LEFT -> DOWN, UP -> LEFT)
  private val RIGHT_TURNS = Map(RIGHT -> DOWN, DOWN -> LEFT, LEFT -> UP, UP -> RIGHT)

  private val nextStep: (Int, Int, Int, Int) => (Int, Int, Int, Int) = if (example) exampleNextStep else realNextStep

  def left(o:Int) :Int = LEFT_TURNS(o)
  def right(o:Int) :Int = RIGHT_TURNS(o)

  private def nextX(x: Int, dx: Int, y: Int): (Int, Int) = {
    val line = map(y)
    val newX = x + dx

    val minX = line.indexWhere(!_.isWhitespace)
    val maxX = line.lastIndexWhere(!_.isWhitespace)

    if (newX > maxX)
      (minX, y)
    else if (newX < minX)
      (maxX, y)
    else
      (newX, y)
  }

  private def nextY(x: Int, y: Int, dy: Int): (Int, Int) = {
    val newY = y + dy

    val minY = map.indexWhere(line => if (line.length > x) !line(x).isWhitespace else false)
    val maxY = map.lastIndexWhere(line => if (line.length > x) !line(x).isWhitespace else false)

    if (newY > maxY)
      (x, minY)
    else if (newY < minY)
      (x, maxY)
    else
      (x, newY)
  }

  private def forward(count: Int, facing: Int, x: Int, y: Int): (Int, Int, Int) = {
    if (count == 0) {
      (facing, x, y)
    } else {
      val (newX, newY) = facing match {
        case 0 => nextX(x, 1, y)
        case 1 => nextY(x, y, 1)
        case 2 => nextX(x, -1, y)
        case 3 => nextY(x, y, -1)
      }
      if (map(newY)(newX) == '.') {
        forward(count - 1, facing, newX, newY)
      } else {
        (facing, x, y)
      }
    }
  }

  private def forwardCube(count: Int, facing: Int, x: Int, y: Int, z : Int): (Int, Int, Int, Int) = {
    if (count == 0) {
      (facing, x, y, z)
    } else {
      val (newFacing: Int, newX: Int, newY: Int, newZ: Int) = nextStep(facing, x, y, z)
      if (cube(newZ)(newY)(newX) == '.') {
        forwardCube(count - 1, newFacing, newX, newY, newZ)
      } else {
        (facing, x, y, z)
      }
    }
  }

  private def exampleNextStep(facing: Int, x: Int, y: Int, z: Int) : (Int,Int,Int,Int) = {
    z match {
      case 1 => facing match {
        case RIGHT => if (x == part2MaxX) (LEFT, part2MaxX, part2MaxY - y, 6) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 4) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (DOWN, y, 0, 3) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (DOWN, part2MaxX - x, 0, 2) else (UP, x, y - 1, z)
      }
      case 2 => facing match {
        case RIGHT => if (x == part2MaxX) (RIGHT, 0, y, 3) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (UP, part2MaxX - x, part2MaxY, 5) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (UP, part2MaxX - y, part2MaxY, 6) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (DOWN, part2MaxX - x, 0, 1) else (UP, x, y - 1, z)
      }
      case 3 => facing match {
        case RIGHT => if (x == part2MaxX) (RIGHT, x, 0, 4) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (RIGHT, 0, part2MaxX - x, 5) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (LEFT, x, part2MaxY, 2) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (RIGHT, 0, x, 1) else (UP, x, y - 1, z)
      }
      case 4 => facing match {
        case RIGHT => if (x == part2MaxX) (DOWN, part2MaxX - y, 0, 6) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 5) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (LEFT, x, part2MaxY, 3) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (DOWN, x, 0, 5) else (UP, x, y - 1, z)
      }
      case 5 => facing match {
        case RIGHT => if (x == part2MaxX) (RIGHT, 0, y, 6) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (UP, part2MaxX - x, part2MaxY, 2) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (UP, part2MaxX - y, part2MaxY, 3) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (UP, x, part2MaxY, 4) else (UP, x, y - 1, z)
      }
      case 6 => facing match {
        case RIGHT => if (x == part2MaxX) (LEFT, part2MaxX, part2MaxY - x, 1) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (RIGHT, 0, part2MaxX - x, 2) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (LEFT, part2MaxX, y, 5) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (LEFT, part2MaxX, part2MaxY - x, 4) else (UP, x, y - 1, z)
      }
    }
  }

  private def realNextStep(facing: Int, x: Int, y: Int, z: Int): (Int, Int, Int, Int) = {
    z match {
      case 1 => facing match {
        case RIGHT => if (x == part2MaxX) (RIGHT, 0, y, 2) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 3) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (RIGHT, 0, part2MaxY - y, 4) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (RIGHT, 0, x, 6) else (UP, x, y - 1, z)
      }
      case 2 => facing match {
        case RIGHT => if (x == part2MaxX) (LEFT, part2MaxX, part2MaxY-y, 5) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (LEFT, part2MaxX, x, 3) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (LEFT, part2MaxX, y, 1) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (UP, x, part2MaxY, 6) else (UP, x, y - 1, z)
      }
      case 3 => facing match {
        case RIGHT => if (x == part2MaxX) (UP, y, part2MaxY, 2) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 5) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (DOWN, y, 0, 4) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (UP, x, part2MaxY, 1) else (UP, x, y - 1, z)
      }
      case 4 => facing match {
        case RIGHT => if (x == part2MaxX) (RIGHT, 0, y, 5) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 6) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (RIGHT, 0, part2MaxY - y, 1) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (RIGHT, 0, x, 3) else (UP, x, y - 1, z)
      }
      case 5 => facing match {
        case RIGHT => if (x == part2MaxX) (LEFT, part2MaxX, part2MaxY-y, 2) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (LEFT, part2MaxX, x,6) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (LEFT, part2MaxX, y, 4) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (UP, x, part2MaxY, 3) else (UP, x, y - 1, z)
      }
      case 6 => facing match {
        case RIGHT => if (x == part2MaxX) (UP, y, part2MaxY, 5) else (RIGHT, x + 1, y, z)
        case DOWN => if (y == part2MaxY) (DOWN, x, 0, 2) else (DOWN, x, y + 1, z)
        case LEFT => if (x == 0) (DOWN, y, 0, 1) else (LEFT, x - 1, y, z)
        case UP => if (y == 0) (UP, x, part2MaxY, 4) else (UP, x, y - 1, z)
      }
    }
  }

  @tailrec
  private def walk1(steps: List[String], facing: Int, x: Int, y: Int): (Int, Int, Int) = {
    if (steps.isEmpty) {
      (facing, x, y)
    } else {
      val step = steps.head
      val (newFacing, newX, newY) = step match {
        case "L" => (left(facing), x, y)
        case "R" => (right(facing), x, y)
        case NUMBER(count) => forward(count.toInt, facing, x, y)
      }
      walk1(steps.tail, newFacing, newX, newY)
    }
  }

  @tailrec
  private def walk2(steps: List[String], facing: Int, x: Int, y: Int, z: Int): (Int, Int, Int, Int) = {
    if (steps.isEmpty) {
      (facing, x, y, z)
    } else {
      val step = steps.head
      val (newFacing, newX, newY, newZ) = step match {
        case "L" => (left(facing), x, y, z)
        case "R" => (right(facing), x, y, z)
        case NUMBER(count) => forwardCube(count.toInt, facing, x, y, z)
      }
      walk2(steps.tail, newFacing, newX, newY, newZ)
    }
  }

  private def calculateKey(f : Int, x : Int, y: Int) = (y + 1) * 1000 + (x + 1) * 4 + f

  override val part1Answer: Int =  walk1(path, 0, map.head.indexWhere(_ == '.'), 0) match {
    case (f, x, y) => calculateKey(f, x, y)
  }

  override val part2Answer: Int = walk2(path, 0, cube(1).head.indexWhere(_ == '.'), 0, 1) match {
    case (f, x, y, z) => sides(z - 1) match {
      case (_, l, t, _, _) => calculateKey(f, x + l, y + t)
    }
  }
}
