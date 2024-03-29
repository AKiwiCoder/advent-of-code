package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day18ManyWorldsInterpretation(filename: String) extends DailyProblem[Int, Int] {

  private case class State(locations: List[Point], keys: Set[Char])

  private val partOneMap = FileUtilities.readFile(filename).zipWithIndex.flatMap(pair => pair._1.zipWithIndex.map(e => (Point(pair._2, e._2)) -> e._1)).toMap

  private val inputStartPoint = partOneMap.filter { case (loc, ch) => ch == '@' }.head._1

  private val partTwoMap = partOneMap ++ Map((Point(inputStartPoint.y - 1, inputStartPoint.x - 1) -> '@')
    , (Point(inputStartPoint.y - 1, inputStartPoint.x) -> '#')
    , (Point(inputStartPoint.y - 1, inputStartPoint.x + 1) -> '@')
    , (Point(inputStartPoint.y, inputStartPoint.x - 1) -> '#')
    , (Point(inputStartPoint.y, inputStartPoint.x) -> '#')
    , (Point(inputStartPoint.y, inputStartPoint.x + 1) -> '#')
    , (Point(inputStartPoint.y + 1, inputStartPoint.x - 1) -> '@')
    , (Point(inputStartPoint.y + 1, inputStartPoint.x) -> '#')
    , (Point(inputStartPoint.y + 1, inputStartPoint.x + 1) -> '@'))

  private def move(direction: Int, location: Point): Point = {
    direction match {
      case 1 => LocationHelper.step(location, FacingNorth())
      case 2 => LocationHelper.step(location, FacingSouth())
      case 3 => LocationHelper.step(location, FacingWest())
      case 4 => LocationHelper.step(location, FacingEast())
    }
  }

  @tailrec
  private def floodFill(map: Map[Point, Char], pending: List[(Int, Point)], bestSoFar: Map[Point, Int]): Map[Point, Int] = {
    pending match {
      case Nil => bestSoFar
      case (pBest, pLocation) :: pRest => {
        val currentBest = bestSoFar.getOrElse(pLocation, Integer.MAX_VALUE)
        if (pBest < currentBest) {
          // We are on a shorter path
          val newBestSoFar = if (pBest < currentBest) bestSoFar + (pLocation -> pBest) else bestSoFar
          val newSteps = List(1, 2, 3, 4).map(direction => (pBest + 1, move(direction, pLocation))).filter { case (_, location) => map.getOrElse(location, '#') != '#' }
          floodFill(map, pRest ::: newSteps, newBestSoFar)
        } else {
          // We had already found the shortest
          floodFill(map, pRest, bestSoFar)
        }
      }
    }
  }

  private def shortestPath(map: Map[Point,Char], distances: Map[Point, Int], start: Point, end: Point): List[Point] = {
    @tailrec
    def walk(stack: List[(Point, List[Point])], bestSoFar: Map[List[Point], Int]): List[Point] = {
      if (stack.isEmpty) {
        val result = bestSoFar.filter(p => p._1.head == end).toList.sortWith(_._2 > _._2)
        if (result.isEmpty)
          List()
        else {
          result.head._1.reverse
        }
      } else {
        val head = stack.head
        val current = head._1
        if (distances.getOrElse(current, Integer.MIN_VALUE) >= bestSoFar.getOrElse(head._2, 0)) {
          val n = LocationHelper.step(current, FacingNorth())
          val s = LocationHelper.step(current, FacingSouth())
          val e = LocationHelper.step(current, FacingEast())
          val w = LocationHelper.step(current, FacingWest())

          val nd = distances.getOrElse(n, Integer.MIN_VALUE)
          val sd = distances.getOrElse(s, Integer.MIN_VALUE)
          val ed = distances.getOrElse(e, Integer.MIN_VALUE)
          val wd = distances.getOrElse(w, Integer.MIN_VALUE)

          var newStack: List[(Point, List[Point])] = List()
          var newBest = bestSoFar

          val en = (n, n :: head._2)
          val es = (s, s :: head._2)
          val ee = (e, e :: head._2)
          val ew = (w, w :: head._2)

          if (map.getOrElse(n, '#') != '#' && nd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = en :: newStack
            newBest = newBest + (en._2 -> nd)
          }

          if (map.getOrElse(s, '#') != '#' && sd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = es :: newStack
            newBest = newBest + (es._2 -> sd)
          }

          if (map.getOrElse(e, '#') != '#' && ed > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = ee :: newStack
            newBest = newBest + (ee._2 -> ed)
          }

          if (map.getOrElse(w, '#') != '#' && wd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = ew :: newStack
            newBest = newBest + (ew._2 -> wd)
          }

          walk(newStack ::: stack.tail, newBest)
        } else {
          walk(stack.tail, bestSoFar)
        }
      }
    }

    if (start == end) {
      List()
    } else {
      walk(List((start, List(start))), Map())
    }
  }

  private def calculateBestPath(map: Map[Point, Char], allPaths: Map[(Point, Point), List[Point]], states: Set[State]) : Map[State, Int] = {
    def mapEntry(point: Point, keys: Set[Char]): Char = {
      val mapChar = map.getOrElse(point, ' ')
      if (keys.contains(mapChar.toLower)) ' ' else mapChar
    }

    var cache: Map[(Point, Set[Char]), Map[Char, (Point, Int)]] = Map()

    def getVisibleKeys(point: Point, keys: Set[Char]): Map[Char, (Point, Int)] = {
      val k = (point, keys)
      if (cache.contains(k)) {
        cache(k)
      } else {
        val paths = allPaths.filter(e => e._1._1 == point)
        var result: Map[Char, (Point, Int)] = Map()
        for (path <- paths) {
          var blocked = false
          for (i <- path._2.indices) {
            if (mapEntry(path._2(i), keys).isUpper) {
              blocked = true
            }
            val c = mapEntry(path._2(i), keys)
            if (!blocked && c.isLower) {
              result = result + (c -> (path._2(i), i))
            }
          }
        }
        cache = cache + (k -> result)
        result
      }
    }

    @tailrec
    def walk(states: Set[State], seen: Map[State, Int]): Map[State, Int] = {
      if (states.isEmpty) {
        seen
      } else {
        val state = states.head
        val robotVisibleKeys = state.locations.map(p => (p, getVisibleKeys(p, state.keys))).filter(p => p._2.nonEmpty)
        val (newStates, newSeen) = robotVisibleKeys.foldLeft((states.tail, seen))((acc, move) => {
          move._2.foldLeft(acc)((acc, key) => {
            val startLoc = move._1
            val endLoc = key._2._1
            val newKey = key._1

            val newLocs = endLoc :: state.locations diff List(startLoc)
            val newState = State(newLocs, state.keys + newKey)
            val newLength = acc._2.getOrElse(state, 0) + key._2._2

            if (acc._2.getOrElse(newState, Integer.MAX_VALUE) > newLength) {
              (acc._1 + newState, acc._2 + (newState -> newLength))
            } else {
              acc
            }
          })
        })
        walk(newStates, newSeen)
      }
    }

    walk(states, Map())
  }

  def calculate(map: Map[Point, Char]): Int = {
    val startPoints = map.filter { case (_, ch) => ch == '@' }.keySet
    val keysToGet = map.filter(c => c._2 >= 'a' && c._2 <= 'z')
    val keysIncludingStart = (keysToGet.keySet ++ startPoints)
    val distances = keysIncludingStart.map(point => (point -> floodFill(map, List((0, point)), Map()))).toMap
    val routes = keysIncludingStart.toList.combinations(2).map(e => (e.head, e.last)).toList

    val allPaths = routes.foldLeft(Map[(Point, Point), List[Point]]())((acc, e) => {
      val path = shortestPath(map, distances(e._1), e._1, e._2)
      acc + ((e._1, e._2) -> path) + ((e._2, e._1) -> path.reverse)
    })

    val best = calculateBestPath(map, allPaths, Set(State(startPoints.toList, Set())))

    val pathLengths = best.toList.filter(e => e._1.keys.size == keysToGet.size).map(e => e._2)
    pathLengths match  {
      case Nil => 0
      case lst : List[Int] => lst.min
    }
  }

  override val part1Answer: Int = calculate(partOneMap)
  override val part2Answer: Int = calculate(partTwoMap)
}
