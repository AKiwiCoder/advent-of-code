package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FacingEast, FacingNorth, FacingSouth, FacingWest, FileUtilities, Point2d}

import scala.annotation.tailrec

class Day15Chiton(filename: String) extends DailyProblem[Int, Int] {

  private val originalInput = FileUtilities.readFile(filename).map(_.toCharArray.toList.map(_.toString.toInt))

  private def calculatePath(problemMap : Map[Point2d, Int]): Int = {
    val END = Point2d(problemMap.keys.map(_.x).max, problemMap.keys.map(_.y).max)

    def valid(p: Point2d): Boolean = {
      p.x >= 0 && p.y >= 0 && p.x <= END.x && p.y <= END.y
    }

    @tailrec
    def walk(iteration : Long, toCheck: Set[Point2d],  riskMap: Map[Point2d, Int]): Map[Point2d, Int] = {
      if (toCheck.isEmpty) {
        riskMap
      } else {
        val current = toCheck.head

        val neighbours = List(Point2d(current.x, current.y - 1), Point2d(current.x, current.y + 1), Point2d(current.x + 1, current.y), Point2d(current.x - 1, current.y))

        val validNeighbours = neighbours.filter(valid)

        val lessRiskyNeighbours = validNeighbours.filter(next => riskMap(current) + problemMap(next) < riskMap.getOrElse(next, Int.MaxValue))

        val nextToCheck = lessRiskyNeighbours.foldLeft(toCheck.tail)((acc, p) => acc + p)

        val updatedRiskMap = lessRiskyNeighbours.foldLeft(riskMap)((map, point) => map + (point -> (riskMap(current) + problemMap(point))))

        walk(iteration + 1, nextToCheck, updatedRiskMap)
      }
    }

    val result = walk(0, Set(Point2d(0, 0)), Map(Point2d(0,0) -> problemMap(Point2d(0,0))))
    result(END) - problemMap(Point2d(0,0))
  }

  private val partOneMap = originalInput.indices.foldLeft(Map[Point2d, Int]())((map, x) => originalInput.indices.foldLeft(map)((map, y) => map + (Point2d(x, y) -> originalInput(y)(x))))

  private val width = originalInput.head.length
  private val height = originalInput.length

  private def wrap(value : Int) : Int = {
    value match {
      case 1|2|3|4|5|6|7|8|9 => value
      case 10|11|12|13|14|15|16|17|18 => value - 9
      case 19 => value - 18
    }
  }

  private val partTwoMap = (0 until 5).foldLeft(Map[Point2d, Int]())((map, xMul) =>
    (0 until 5).foldLeft(map)((map, yMul) =>
      partOneMap.foldLeft(map)((map, entry) => {
        map + (Point2d(entry._1.x + width * xMul, entry._1.y + height * yMul) -> wrap(entry._2 + yMul + xMul))
      })))

  override val part1Answer: Int = calculatePath(partOneMap)
  override val part2Answer: Int = calculatePath(partTwoMap)
}


