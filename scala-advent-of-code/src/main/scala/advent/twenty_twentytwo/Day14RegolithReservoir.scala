package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day14RegolithReservoir(filename: String) extends DailyProblem[Int, Int] {
  case class Point(x: Int, y: Int)

  def draw(paths: List[List[List[Int]]]): Set[Point] = {
    def drawSegment(s: List[Int], e: List[Int]): Set[Point] = {
      val start = Point(s(0), s(1))
      val end = Point(e(0), e(1))
      val dx = Math.round(Math.signum(end.x - start.x))
      val dy = Math.round(Math.signum(end.y - start.y))

      if (dx == 0)
        (start.y to end.y by dy).map(y => Point(start.x, y)).toSet
      else
        (start.x to end.x by dx).map(x => Point(x, start.y)).toSet
    }

    def drawPath(path: List[List[Int]]): Set[Point] = {
      path.foldLeft((Set[Point](), path.tail))((acc, current) => if (acc._2.isEmpty) acc else (acc._1 ++ drawSegment(current, acc._2.head), acc._2.tail))._1
    }

    paths.foldLeft(Set[Point]())((acc, path) => acc ++ drawPath(path))
  }

  private val input = FileUtilities.readFile(filename).map(path => path.split(" -> ").map(c => c.split(",").map(_.toInt).toList).toList)
  val zone = draw(input)

  val top = 0
  val bottom = zone.map(_.y).max + 2
  val left = zone.map(_.x).min - 5
  val right = zone.map(_.x).max + 5


  @tailrec
  private def walk_sand1(p: Point, map: Set[Point]): (Boolean, Set[Point]) = {
    def below_free(): Boolean = !map.contains(Point(p.x, p.y + 1))

    def down_left_free(): Boolean = !map.contains(Point(p.x - 1, p.y + 1))

    def down_right_free(): Boolean = !map.contains(Point(p.x + 1, p.y + 1))

    if (p.y > bottom) {
      (false, map)
    } else if (below_free()) {
      walk_sand1(Point(p.x, p.y + 1), map)
    } else if (down_left_free()) {
      walk_sand1(Point(p.x - 1, p.y + 1), map)
    } else if (down_right_free()) {
      walk_sand1(Point(p.x + 1, p.y + 1), map)
    } else {
      (true, map + p)
    }
  }

  @tailrec
  private def walk_sand2(p: Point, map: Set[Point]): Set[Point] = {
    def below_free(): Boolean = !map.contains(Point(p.x, p.y + 1))

    def down_left_free(): Boolean = !map.contains(Point(p.x - 1, p.y + 1))

    def down_right_free(): Boolean = !map.contains(Point(p.x + 1, p.y + 1))

    if (p.y == bottom-1) {
      map + p
    } else if (below_free()) {
      walk_sand2(Point(p.x, p.y + 1), map)
    } else if (down_left_free()) {
      walk_sand2(Point(p.x - 1, p.y + 1), map)
    } else if (down_right_free()) {
      walk_sand2(Point(p.x + 1, p.y + 1), map)
    } else {
      map + p
    }
  }

  @tailrec
  private def dropSand1(map: Set[Point]): Set[Point] = {
    val step = walk_sand1(Point(500, 0), map)
    if (!step._1)
      step._2
    else
      dropSand1(step._2)
  }

  private def dropSand2(map: Set[Point]): Set[Point] = {
    val step = walk_sand2(Point(500, 0), map)
    if (step.contains(Point(500, 0)))
      step
    else
      dropSand2(step)
  }

  override val part1Answer: Int = dropSand1(zone).size - zone.size
  override val part2Answer: Int = dropSand2(zone).size - zone.size
}
