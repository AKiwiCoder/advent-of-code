package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec


class Day23UnstableDiffusion(filename: String) extends DailyProblem[Int, Int] {
  case class Point(x: Int, y: Int)

  abstract class Move {
    def canMove(current: Set[Point], p: Point): Boolean

    def move(p: Point): Point
  }

  case class North() extends Move {
    override def canMove(current: Set[Point], p: Point): Boolean = !(current.contains(Point(p.x, p.y - 1)) || current.contains(Point(p.x - 1, p.y - 1)) || current.contains(Point(p.x + 1, p.y - 1)))

    override def move(p: Point): Point = Point(p.x, p.y - 1)
  }

  case class South() extends Move {
    override def canMove(current: Set[Point], p: Point): Boolean = !(current.contains(Point(p.x, p.y + 1)) || current.contains(Point(p.x - 1, p.y + 1)) || current.contains(Point(p.x + 1, p.y + 1)))


    override def move(p: Point): Point = Point(p.x, p.y + 1)
  }

  case class West() extends Move {
    override def canMove(current: Set[Point], p: Point): Boolean = !(current.contains(Point(p.x - 1, p.y)) || current.contains(Point(p.x - 1, p.y + 1)) || current.contains(Point(p.x - 1, p.y - 1)))

    override def move(p: Point): Point = Point(p.x - 1, p.y)
  }

  case class East() extends Move {
    override def canMove(current: Set[Point], p: Point): Boolean = !(current.contains(Point(p.x + 1, p.y)) || current.contains(Point(p.x + 1, p.y + 1)) || current.contains(Point(p.x + 1, p.y - 1)))

    override def move(p: Point): Point = Point(p.x + 1, p.y)
  }


  def shouldMove(current: Set[Point], p: Point): Boolean = {
    current.contains(Point(p.x, p.y - 1)) ||
      current.contains(Point(p.x - 1, p.y - 1)) ||
      current.contains(Point(p.x + 1, p.y - 1)) ||
      current.contains(Point(p.x - 1, p.y)) ||
      current.contains(Point(p.x + 1, p.y)) ||
      current.contains(Point(p.x - 1, p.y + 1)) ||
      current.contains(Point(p.x, p.y + 1)) ||
      current.contains(Point(p.x + 1, p.y + 1))
  }

  private val input = FileUtilities.readFile(filename)

  private val startingElves = input.indices.foldLeft(List[Point]())((acc, y) => input(y).indices.foldLeft(acc)((acc, x) => if (input(y)(x) == '#') Point(x, y) :: acc else acc)).toSet


  def dump(elves: Set[Point]): Unit = {
    val minx = elves.map(_.x).min - 1
    val maxx = elves.map(_.x).max + 1
    val miny = elves.map(_.y).min - 1
    val maxy = elves.map(_.y).max + 1

    (miny to maxy).foreach(y => {
      (minx to maxx).foreach(x => {
        if (elves.contains(Point(x, y))) {
          print("#")
        } else {
          print(".")
        }
      })
      println("")
    })
  }

  @tailrec
  private def part1(round: Int, elves: Set[Point], directions: List[Move]): Set[Point] = {
    if (round == 0) {
      elves
    } else {
      val elvesInRightPlace = elves.filterNot(p => shouldMove(elves, p))
      val needingMove = elves.filter(p => shouldMove(elves, p))
      if (needingMove.isEmpty) {
        elves
      } else {
        val suggestedMoves = needingMove.foldLeft(Map[Point, Point]())((moves, elf) => {
          directions.find(dir => dir.canMove(elves, elf)) match {
            case Some(d) => moves + (elf -> d.move(elf))
            case None => moves + (elf -> elf)
          }
        })

        val movedElves = suggestedMoves.foldLeft(List[Point]())((acc, mv) => if (suggestedMoves.values.count(_ == mv._2) == 2) mv._1 :: acc else mv._2 :: acc)

        part1(round - 1, elvesInRightPlace ++ movedElves, directions.tail ::: List(directions.head))
      }
    }
  }

  @tailrec
  private def part2(round : Int, elves: Set[Point], directions: List[Move]): Int = {
    val elvesInRightPlace = elves.filterNot(p => shouldMove(elves, p))
    val needingMove = elves.filter(p => shouldMove(elves, p))
    if (needingMove.isEmpty) {
      round
    } else {
      val suggestedMoves = needingMove.foldLeft(Map[Point, Point]())((moves, elf) => {
        directions.find(dir => dir.canMove(elves, elf)) match {
          case Some(d) => moves + (elf -> d.move(elf))
          case None => moves + (elf -> elf)
        }
      })
      val movedElves = suggestedMoves.foldLeft(List[Point]())((acc, mv) => if (suggestedMoves.values.count(_ == mv._2) == 2) mv._1 :: acc else mv._2 :: acc)
      part2(round + 1, elvesInRightPlace ++  movedElves, directions.tail ::: List(directions.head))
    }
  }

  private def count(map: Set[Point]): Int = {
    val minx = map.map(_.x).min
    val maxx = map.map(_.x).max + 1
    val miny = map.map(_.y).min
    val maxy = map.map(_.y).max + 1
    ((maxx - minx) * (maxy - miny)) - map.size
  }

  override val part1Answer: Int = count(part1(10, startingElves, List(North(), South(), West(), East())))
  override val part2Answer: Int = part2(0, startingElves, List(North(), South(), West(), East())) + 1
}
