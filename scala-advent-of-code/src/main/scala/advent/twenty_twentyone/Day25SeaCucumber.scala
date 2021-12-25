package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

class Day25SeaCucumber(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).zipWithIndex.flatMap(row => row._1.zipWithIndex.map(col => (Point2d(col._2, row._2), col._1)))


  private val bottom = input.map(_._1.y).max
  private val right = input.map(_._1.x).max

  private val eastCucumbers = input.filter(_._2 == '>').map(_._1)
  private val downCucumbers = input.filter(_._2 == 'v').map(_._1)

  private def dump(east : List[Point2d], down : List[Point2d]) : Unit = {
      (0 to bottom).foreach(row => {
        (0 to right).foreach(col => {
          val point = Point2d(col, row)
          if (east.contains(point) && down.contains(point)) {
            print("*")
          } else if (east.contains(point)) {
            print(">")
          } else  if (down.contains(point)) {
            print("v")
          } else {
            print(".")
          }
        })
        println("")
      })
  }

  private def part1(): Int = {
    @tailrec
    def move(count: Int, east: List[Point2d], down: List[Point2d]): Int = {
      val (newEast, eastChanges) = east.foldLeft((List[Point2d](), 0))((acc, oldLoc) => {
        val newLoc = Point2d(if (oldLoc.x == right) 0 else oldLoc.x + 1, oldLoc.y)
        if (east.contains(newLoc) || down.contains(newLoc)) {
          (oldLoc :: acc._1, acc._2)
        } else {
          (newLoc :: acc._1, acc._2 + 1)
        }
      })

      val (newDown, downChanges) = down.foldLeft((List[Point2d](), 0))((acc, oldLoc) => {
        val newLoc = Point2d(oldLoc.x, if (oldLoc.y == bottom) 0 else oldLoc.y + 1)
        if (newEast.contains(newLoc) || down.contains(newLoc)) {
          (oldLoc :: acc._1, acc._2)
        } else {
          (newLoc :: acc._1, acc._2 + 1)
        }
      })

      if (eastChanges + downChanges == 0) {
        count
      } else {
        move(count + 1, newEast, newDown)
      }
    }

    move(1, eastCucumbers, downCucumbers)
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = 0
}


