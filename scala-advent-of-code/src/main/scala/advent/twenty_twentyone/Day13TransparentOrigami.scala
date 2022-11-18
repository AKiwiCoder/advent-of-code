package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

abstract class FoldInstruction {
  def mapper(in: Point2d): Point2d = ???
}

case class FoldX(x: Int) extends FoldInstruction {
  override def mapper(in: Point2d): Point2d = {
    Point2d(if (in.x > x) x - Math.abs(in.x - x) else in.x, in.y)
  }
}

case class FoldY(y: Int) extends FoldInstruction {
  override def mapper(in: Point2d): Point2d = {
    Point2d(in.x, if (in.y > y) y - Math.abs(in.y - y) else in.y)
  }
}

class Day13TransparentOrigami(filename: String) extends DailyProblem[Int, String] {

  private val foldX = "fold along x=([0-9]+)".r
  private val foldY = "fold along y=([0-9]+)".r

  private val input = FileUtilities.readFile(filename)

  private val paper = input.filter(line => line.contains(",")).map(line => line.split(",")).map(bits => Point2d(bits(0).toInt, bits(1).toInt)).toSet
  private val instructions = input.filter(line => line.contains("=")).map {
    case foldX(x) => FoldX(x.toInt)
    case foldY(y) => FoldY(y.toInt)
  }

  private def convertToString(points: Set[Point2d]): String = {
    val xs = points.map(_.x)
    val ys = points.map(_.y)
    val minX = xs.min
    val maxX = xs.max
    val minY = ys.min
    val maxY = ys.max

    (minY to maxY).foldLeft("")((acc, y) => {
      (minX to maxX).foldLeft(acc)((acc, x) => if (points.contains(Point2d(x, y))) acc + "*" else acc + " ") + "\n"
    })
  }

  override val part1Answer: Int = paper.map(instructions.head.mapper).size
  override val part2Answer: String = convertToString(instructions.foldLeft(paper)((acc, fold) => acc.map(fold.mapper))).trim
}


