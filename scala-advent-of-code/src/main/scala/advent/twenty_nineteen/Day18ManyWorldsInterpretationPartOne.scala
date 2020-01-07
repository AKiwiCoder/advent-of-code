package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec


class Day18ManyWorldsInterpretationPartOne(filename: String) extends DailyProblem[Int, Int] {
  case class State(location: Point, keys: Set[Char])

  private val map = FileUtilities.readFile(filename).zipWithIndex.flatMap(pair => pair._1.zipWithIndex.map(e => (Point(pair._2, e._2)) -> e._1)).toMap.filter(e => e._2 != '.')

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
          val newSteps = List(1, 2, 3, 4).map(direction => (pBest + 1, move(direction, pLocation))).filter { case (_, location) => map.getOrElse(location, '.') != '#' }
          floodFill(map, pRest ::: newSteps, newBestSoFar)
        } else {
          // We had already found the shortest
          floodFill(map, pRest, bestSoFar)
        }
      }
    }
  }

  private def shortestPath(distances: Map[Point, Int], start: Point, end: Point): List[Point] = {
    @tailrec
    def walk(stack: List[(Point, List[Point])], bestSoFar: Map[List[Point], Int]): List[Point] = {
      if (stack.isEmpty) {
        bestSoFar.filter(p => p._1.head == end).toList.sortWith(_._2 > _._2).head._1.reverse
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

          if (map.getOrElse(n, '.') != '#' && nd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = en :: newStack
            newBest = newBest + (en._2 -> nd)
          }

          if (map.getOrElse(s, '.') != '#' && sd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = es :: newStack
            newBest = newBest + (es._2 -> sd)
          }

          if (map.getOrElse(e, '.') != '#' && ed > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
            newStack = ee :: newStack
            newBest = newBest + (ee._2 -> ed)
          }

          if (map.getOrElse(w, '.') != '#' && wd > bestSoFar.getOrElse(head._2, Integer.MIN_VALUE)) {
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
  private def walk(states: Set[State], seen: Map[State, Int]): Map[State, Int] = {
    if (states.size % 100 == 0) {
      println(states.size)
    }

    if (states.isEmpty) {
      seen
    } else {
      val state = states.head
      val visibleKeys = getVisibleKeys(state.location, state.keys)

      val (newState, newSeen) = visibleKeys.foldLeft((states.tail, seen))((acc, visible) => {
        val st = acc._1
        val ns = acc._2

        val newState = State(visible._2._1, state.keys + visible._1)
        val newLength = ns.getOrElse(state, 0) + visible._2._2

        if (ns.getOrElse(newState, Integer.MAX_VALUE) > newLength) {
          (st + newState, ns + (newState -> newLength))
        } else {
          (st, ns)
        }
      })

      walk(newState, newSeen)
    }
  }

  val startPoint = map.filter { case (loc, ch) => ch == '@' }.head
  val keysToGet = map.filter(c => c._2 >= 'a' && c._2 <= 'z')
  val keysIncludingStart = (keysToGet.keySet + startPoint._1)

  println("keysIncludingStart: " + keysIncludingStart.size)

  val distances = keysIncludingStart.map(point => (point -> floodFill(map, List((0, point)), Map()))).toMap

  println("distances: " + distances.keySet.size)
  println("Calculating Routes")

  val routes = keysIncludingStart.toList.combinations(2).map(e => (e.head, e.last)).toList

  println("Routes: " + routes.size)
  println("Calculating Shortest Paths")

  val allPaths = routes.foldLeft(Map[(Point, Point), List[Point]]())((acc, e) => acc + ((e._1, e._2) -> shortestPath(distances(e._1), e._1, e._2)) + ((e._2, e._1) -> shortestPath(distances(e._2), e._2, e._1)))

  println("All: " + allPaths.size)
  println("Starting Walking")

  val best = walk(Set(State(startPoint._1, Set())), Map())
  val bsf = best.toList.filter(e => e._1.keys.size == keysToGet.size).map(e => e._2).min

  override val part1Answer: Int = bsf
  override val part2Answer: Int = 0
}


