package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day20DonutMaze(filename: String) extends DailyProblem[Int, Int] {

  case class PointWithDepth(point: Point, depth: Int)

  private val inputMap = FileUtilities.readFile(filename).zipWithIndex.flatMap(pair => pair._1.zipWithIndex.map(e => (Point(pair._2, e._2)) -> e._1)).toMap

  private def move(map: Map[Point, Char], portals: Map[Point, Point], direction: Int, location: Point): Point = {
    direction match {
      case 1 => stepWithPortal(map, portals, location, FacingNorth())
      case 2 => stepWithPortal(map, portals, location, FacingSouth())
      case 3 => stepWithPortal(map, portals, location, FacingWest())
      case 4 => stepWithPortal(map, portals, location, FacingEast())
    }
  }

  private def move2(map: Map[Point, Char], portals: Map[Point, Point], direction: Int, location: PointWithDepth): PointWithDepth = {
    direction match {
      case 1 => stepWithPortalDepth(map, portals, location, FacingNorth())
      case 2 => stepWithPortalDepth(map, portals, location, FacingSouth())
      case 3 => stepWithPortalDepth(map, portals, location, FacingWest())
      case 4 => stepWithPortalDepth(map, portals, location, FacingEast())
    }
  }

  def stepWithPortal(map: Map[Point, Char], portals: Map[Point, Point], current: Point, facing: Facing): Point = {
    val nextLocation = LocationHelper.step(current, facing)
    if (map(nextLocation) >= 'A' && map(nextLocation) <= 'Z') {
      portals.getOrElse(current, current)
    } else {
      nextLocation
    }
  }

  @tailrec
  private def floodFill(map: Map[Point, Char], portals: Map[Point, Point], pending: List[(Int, Point)], bestSoFar: Map[Point, Int]): Map[Point, Int] = {
    pending match {
      case Nil => bestSoFar
      case (pBest, pLocation) :: pRest => {
        val currentBest = bestSoFar.getOrElse(pLocation, Integer.MAX_VALUE)
        if (pBest < currentBest) {
          // We are on a shorter path
          val newBestSoFar = if (pBest < currentBest) bestSoFar + (pLocation -> pBest) else bestSoFar
          val bbb = List(1, 2, 3, 4).map(direction => (pBest + 1, move(map, portals, direction, pLocation)))
          val newSteps = bbb.filter { case (best, location) => map.getOrElse(location, '#') == '.' && bestSoFar.getOrElse(location, Integer.MAX_VALUE) > best }
          floodFill(map, portals, pRest ::: newSteps, newBestSoFar)
        } else {
          // We had already found the shortest
          floodFill(map, portals, pRest, bestSoFar)
        }
      }
    }
  }

  var count = 0

  @tailrec
  private def floodFill2(maps: List[Map[Point, Char]], portals: Map[Point, Point], pending: List[(Int, PointWithDepth)], bestSoFar: Map[PointWithDepth, Int]): Map[PointWithDepth, Int] = {
    val endPointDept = PointWithDepth(endPoint, 0)

    pending match {
      case Nil => bestSoFar
      case (pBest, pLocation) :: pRest => {
        val currentBest = bestSoFar.getOrElse(pLocation, 10000)

        if (pBest < currentBest && pBest < bestSoFar.getOrElse(endPointDept, Integer.MAX_VALUE)) {

          // We are on a shorter path
          val newBestSoFar = bestSoFar + (pLocation -> pBest)

          val map = if (pLocation.depth == 0) maps(0) else maps(1)

          val bbb = List(1, 2, 3, 4).map(direction => (pBest + 1, move2(map, portals, direction, pLocation)))

          val newSteps = bbb.filter { case (_, location) => {
            if (location.depth == 0)
              maps(0).getOrElse(location.point, '#') == '.'
            else
              maps(1).getOrElse(location.point, '#') == '.'
          }
          }

          floodFill2(maps, portals, (pRest ::: newSteps).sortBy(_._1), newBestSoFar)
        } else {
          // We had already found the shortest
          floodFill2(maps, portals, pRest, bestSoFar)
        }
      }
    }
  }


  def tl(point: Point): Point = Point(point.y - 1, point.x - 1)

  def tr(point: Point): Point = Point(point.y - 1, point.x + 1)

  def bl(point: Point): Point = Point(point.y + 1, point.x - 1)

  def br(point: Point): Point = Point(point.y + 1, point.x + 1)

  def t(point: Point): Point = Point(point.y - 1, point.x)

  def l(point: Point): Point = Point(point.y, point.x - 1)

  def b(point: Point): Point = Point(point.y + 1, point.x)

  def r(point: Point): Point = Point(point.y, point.x + 1)

  def above(map: Map[Point, Char], point: Point): Boolean = isWall(map, tl(point)) && isWall(map, tr(point))

  def below(map: Map[Point, Char], point: Point): Boolean = isWall(map, bl(point)) && isWall(map, br(point))

  def left(map: Map[Point, Char], point: Point): Boolean = isWall(map, tl(point)) && isWall(map, bl(point))

  def right(map: Map[Point, Char], point: Point): Boolean = isWall(map, tr(point)) && isWall(map, br(point))

  def isWall(map: Map[Point, Char], point: Point): Boolean = map.getOrElse(point, ' ') == '#'

  val portalCharacters = inputMap.filter(p => p._2 >= 'A' && p._2 <= 'Z')
  var portalList: Map[String, List[Point]] = Map().withDefaultValue(List())
  for (p1 <- portalCharacters) {
    for (p2 <- portalCharacters) {
      if (p1 != p2) {
        if ((p1._1.x == p2._1.x + 1 && p1._1.y == p2._1.y) || (p1._1.y == p2._1.y + 1 && p1._1.x == p2._1.x)) {

          if (above(inputMap, p1._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (t(p1._1) :: portalList(name)))
          }
          if (below(inputMap, p1._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (b(p1._1) :: portalList(name)))
          }
          if (left(inputMap, p1._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (l(p1._1) :: portalList(name)))
          }
          if (right(inputMap, p1._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (r(p1._1) :: portalList(name)))
          }

          if (above(inputMap, p2._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (t(p2._1) :: portalList(name)))
          }
          if (below(inputMap, p2._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (b(p2._1) :: portalList(name)))
          }
          if (left(inputMap, p2._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (l(p2._1) :: portalList(name)))
          }
          if (right(inputMap, p2._1)) {
            val name =""+ p1._2 + p2._2
            portalList = portalList + (name -> (r(p2._1) :: portalList(name)))
          }
        }
      }
    }
  }

  private var startPoint = portalList("AA").head
  private var endPoint = portalList("ZZ").head
  private val portals = portalList.foldLeft(Map[Point, Point]())((acc, entry) => if (entry._2.size == 1) acc else acc + (entry._2(0) -> entry._2(1)) + (entry._2(1) -> entry._2(0)))
  private val distances = floodFill(inputMap, portals, List((0, startPoint)), Map())

  private val topLeftWall = inputMap.filter(e => e._2 == '#').keySet.toList.sortWith((lhs, rhs) => Point.manhattanDistance(lhs, Point(0, 0)) < Point.manhattanDistance(rhs, Point(0, 0))).head
  private val bottomRightWall = inputMap.filter(e => e._2 == '#').keySet.toList.sortWith((lhs, rhs) => Point.manhattanDistance(lhs, Point(0, 0)) > Point.manhattanDistance(rhs, Point(0, 0))).head

  def isOuter(point: Point): Boolean = {
    (point.y <= topLeftWall.y || point.y >= bottomRightWall.y) || (point.x <= topLeftWall.x || point.x >= bottomRightWall.x)
  }

  def isInner(point: Point): Boolean = {
    !isOuter(point)
  }

  def modifyMap(map: Map[Point, Char], portals: Map[Point, Point]): Map[Point, Char] = {
    portals.foldLeft(map)((acc, entry) => {
      val ac1 = if (isOuter(entry._1)) acc + (entry._1 -> '#') else acc
      val ac2 = if (isOuter(entry._2)) ac1 + (entry._2 -> '#') else ac1
      ac2
    })
  }

  private val part2Top = modifyMap(inputMap, portals)
  private val part2Bottom = modifyMap(inputMap, Map(startPoint -> endPoint))

  private val distances2 = floodFill2(List(part2Top, part2Bottom), portals, List((0, PointWithDepth(startPoint, 0))), Map())

  override val part1Answer: Int = distances.getOrElse(endPoint, -1)
  override val part2Answer: Int = distances2.getOrElse(PointWithDepth(endPoint, 0), -1)

  def stepWithPortalDepth(map: Map[Point, Char], portals: Map[Point, Point], current: PointWithDepth, facing: Facing): PointWithDepth = {
    val nextLocation = LocationHelper.step(current.point, facing)
    if (map(nextLocation) >= 'A' && map(nextLocation) <= 'Z') {
      if (portals.contains(current.point)) {
        PointWithDepth(portals(current.point), current.depth + (if (isInner(current.point)) 1 else -1))
      } else {
        PointWithDepth(current.point, current.depth)
      }
    } else {
      PointWithDepth(nextLocation, current.depth)
    }
  }
}


