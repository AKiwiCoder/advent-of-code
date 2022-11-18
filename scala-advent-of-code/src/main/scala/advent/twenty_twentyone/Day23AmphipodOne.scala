package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.AmphipodHelperOne.{alreadyInPlace, pathDistance}
import advent.twenty_twentyone.HelperOne.{A_HOME_1, A_HOME_2, B_HOME_1, B_HOME_2, C_HOME_1, C_HOME_2, D_HOME_1, D_HOME_2, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7, dump, isBlocked}
import advent.utilities.Point.manhattanDistance
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

object HelperOne {
  def dump(map: Map[Point2d, Char]) = {
    val maxX = map.keys.map(_.x).max
    val maxY = map.keys.map(_.y).max

    val r = (0 to maxY).foldLeft("")((acc, row) => acc + (0 to maxX).foldLeft("")((acc, col) => acc + map.getOrElse(Point2d(col, row), ' ')) + "\n")
  }

  def dump(currentAmphipodsPositions: Map[Point2d, Char], map: Map[Point2d, Char]) = {
    val maxX = map.keys.map(_.x).max
    val maxY = map.keys.map(_.y).max

    val r = (0 to maxY).foldLeft("")((acc, row) => acc + (0 to maxX).foldLeft("")((acc, col) => acc + currentAmphipodsPositions.getOrElse(Point2d(col, row), map.getOrElse(Point2d(col, row), ' '))) + "\n")
    // println(r)
  }


  val A_HOME_1: Point2d = Point2d(3, 2)
  val A_HOME_2: Point2d = Point2d(3, 3)
  val B_HOME_1: Point2d = Point2d(5, 2)
  val B_HOME_2: Point2d = Point2d(5, 3)
  val C_HOME_1: Point2d = Point2d(7, 2)
  val C_HOME_2: Point2d = Point2d(7, 3)
  val D_HOME_1: Point2d = Point2d(9, 2)
  val D_HOME_2: Point2d = Point2d(9, 3)

  val UPPER_1: Point2d = Point2d(1, 1)
  val UPPER_2: Point2d = Point2d(2, 1)
  val UPPER_3: Point2d = Point2d(4, 1)
  val UPPER_4: Point2d = Point2d(6, 1)
  val UPPER_5: Point2d = Point2d(8, 1)
  val UPPER_6: Point2d = Point2d(10, 1)
  val UPPER_7: Point2d = Point2d(11, 1)

  def isBlocked(occupied: Set[Point2d], start: Point2d, end: Point2d): Boolean = {
    val ups = if (start.y == 3) { // Up Two
      List(Point2d(start.x, 2), Point2d(start.x, 1))
    } else if (start.y == 2) { // Up Onw
      List(Point2d(start.x, 1))
    } else { // Up None
      List()
    }

    val horzontals = if (start.x == end.x) { // No horizontals
      List()
    } else {
      (Math.min(start.x, end.x) to Math.max(start.x, end.x)).foldLeft(List[Point2d]())((acc, x) => Point2d(x, 1) :: acc)
    }

    val downs = if (end.y == 3) { //
      List(Point2d(end.x, 2), Point2d(end.x, 1))
    } else if (end.y == 2) {
      List(Point2d(end.x, 1))
    } else {
      List()
    }

    val moves = end :: ups ::: horzontals ::: downs
    val occupiedSomewhere = moves.exists(point => occupied.contains(point) && point != start)
    if (occupiedSomewhere) {
      // println("Move " + start + " to " + end + " Blocked ** + " + moves.filter(point => occupied.contains(point)))
    }
    occupiedSomewhere
  }
}

object AmphipodHelperOne {
  def cost(name: Char): Long = {
    name match {
      case 'A' => 1L
      case 'B' => 10L
      case 'C' => 100L
      case 'D' => 1000L
    }
  }

  val UPPERS = List(UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)

  def possibleMoves(name: Char, currentLoc: Point2d, positions: Map[Point2d, Char]): List[Point2d] = {
    if (UPPERS.contains(currentLoc)) {
      name match {
        case 'A' => if (positions.getOrElse(A_HOME_2,'*') == 'A') List(A_HOME_1) else List(A_HOME_2)
        case 'B' => if (positions.getOrElse(B_HOME_2,'*') == 'B') List(B_HOME_1) else List(B_HOME_2)
        case 'C' => if (positions.getOrElse(C_HOME_2,'*') == 'C') List(C_HOME_1) else List(C_HOME_2)
        case 'D' => if (positions.getOrElse(D_HOME_2,'*') == 'D') List(D_HOME_1) else List(D_HOME_2)
      }
    } else {
      name match {
        case 'A' => List(A_HOME_2, A_HOME_1, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)
        case 'B' => List(B_HOME_2, B_HOME_1, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)
        case 'C' => List(C_HOME_2, C_HOME_1, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)
        case 'D' => List(D_HOME_2, D_HOME_1, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)
      }
    }
  }

  def alreadyInPlace(positions: Map[Point2d, Char], loc: Point2d): Boolean = {
    positions(loc) match {
      case 'A' => loc == A_HOME_2 || (loc == A_HOME_1 && positions.getOrElse(A_HOME_2, '*') == 'A')
      case 'B' => loc == B_HOME_2 || (loc == B_HOME_1 && positions.getOrElse(B_HOME_2, '*') == 'B')
      case 'C' => loc == C_HOME_2 || (loc == C_HOME_1 && positions.getOrElse(C_HOME_2, '*') == 'C')
      case 'D' => loc == D_HOME_2 || (loc == D_HOME_1 && positions.getOrElse(D_HOME_2, '*') == 'D')
    }
  }

  def pathDistance(start: Point2d, end: Point2d): Long = {
    val bStartUpper = UPPERS.contains(start)
    val bEndUpper = UPPERS.contains(end)

    (bStartUpper, bEndUpper) match {
      case (true, false) => manhattanDistance(start, end)
      case (false, true) => manhattanDistance(start, end)
      case (false, false) => manhattanDistance(start, Point2d(end.x, 1)) + manhattanDistance(Point2d(end.x, 1), end)
    }
  }
}

class Day23AmphipodOne(filename: String) extends DailyProblem[Long, Long] {
  case class State(amphipods: Map[Point2d, Char]) {
    def isFinished(): Boolean = {
      amphipods.contains(A_HOME_1) && amphipods.contains(A_HOME_2) &&
        amphipods.contains(B_HOME_1) && amphipods.contains(B_HOME_2) &&
        amphipods.contains(C_HOME_1) && amphipods.contains(C_HOME_2) &&
        amphipods.contains(D_HOME_1) && amphipods.contains(D_HOME_2) &&
        amphipods(A_HOME_1) == 'A' && amphipods(A_HOME_2) == 'A' &&
        amphipods(B_HOME_1) == 'B' && amphipods(B_HOME_2) == 'B' &&
        amphipods(C_HOME_1) == 'C' && amphipods(C_HOME_2) == 'C' &&
        amphipods(D_HOME_1) == 'D' && amphipods(D_HOME_2) == 'D'
    }

    def alreadyInPlace(): Int = {
      amphipods.count(p => AmphipodHelperOne.alreadyInPlace(amphipods, p._1))
    }
  }
  private val input = FileUtilities.readFile(filename).zipWithIndex.flatMap(entry => entry._1.toCharArray.toList.zipWithIndex.map(cell => Point2d(cell._2, entry._2) -> cell._1)).toMap

  private val map = input.map(entry => entry._2 match {
    case 'A' => (entry._1 -> '.')
    case 'B' => (entry._1 -> '.')
    case 'C' => (entry._1 -> '.')
    case 'D' => (entry._1 -> '.')
    case c => (entry._1 -> c)
  })


  def part1(): Long = {
    @tailrec
    def run(tick: Long, states: List[(State, Long)], lowest: Long, considered: Map[State, Long]): Long = {
      if (tick % 10000 == 0) {
        println(states.size + " " + lowest + " " + considered.size)
      }
      if (states.isEmpty) {
        lowest
      } else {
        val (currentState, energySpentSoFar) = states.head

        val (newStates, newLowest, newConsidered) = if (currentState.isFinished()) {
          // If we are finished, we ignore us
          if (energySpentSoFar < lowest) {
            // If we are the lowest
            (states.tail, energySpentSoFar, considered + (currentState -> energySpentSoFar))
          } else {
            (states.tail, lowest, considered)
          }
        } else if (considered.getOrElse(currentState, Long.MaxValue) < energySpentSoFar) { // If we have got here cheaper
          (states.tail, lowest, considered)
        } else {
          val currentAmphipodsPositions = currentState.amphipods.keys.toSet

          val additionalStates = currentState.amphipods.foldLeft(List[(State, Long)]())((acc, entry) => {
            val (currentLoc, amphipod) = entry

            if (alreadyInPlace(currentState.amphipods, currentLoc)) {
              acc
            } else {
              val allPossibleMoves = AmphipodHelperOne.possibleMoves(amphipod, currentLoc, currentState.amphipods)
              val possibleMoves = allPossibleMoves.filter(p => !isBlocked(currentAmphipodsPositions, currentLoc, p))

              val newStates = possibleMoves.map(newLoc => {
                val newEnergyCost = energySpentSoFar + pathDistance(currentLoc, newLoc) * AmphipodHelperOne.cost(amphipod)
                val newState = State(currentState.amphipods.filter(_._1 != currentLoc) + (newLoc -> amphipod))
                (newState, newEnergyCost)
              })

              val newBetterStates = newStates.filter(st => (considered.getOrElse(st._1, Long.MaxValue) > st._2))

              newBetterStates ::: acc
            }
          })

          (additionalStates ::: states.tail, lowest, considered + (currentState -> energySpentSoFar))
        }

        run(tick + 1, newStates, newLowest, newConsidered)
      }
    }

    val start = State(input.filter(entry => Character.isUpperCase(entry._2)).map(entry => entry._1 -> entry._2))

    run(0, List((start, 0)), Long.MaxValue, Map())
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Long = 0
}


