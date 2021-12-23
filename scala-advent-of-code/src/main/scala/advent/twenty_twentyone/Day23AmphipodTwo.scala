package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.AmphipodHelperTwo.{alreadyInPlace, pathDistance}
import advent.twenty_twentyone.HelperTwo.{A_HOME_1, A_HOME_2, A_HOME_3, A_HOME_4, B_HOME_1, B_HOME_2, B_HOME_3, B_HOME_4, C_HOME_1, C_HOME_2, C_HOME_3, C_HOME_4, D_HOME_1, D_HOME_2, D_HOME_3, D_HOME_4, UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7, dump, isBlocked}
import advent.utilities.Point.manhattanDistance
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

object HelperTwo {
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
  val A_HOME_3: Point2d = Point2d(3, 4)
  val A_HOME_4: Point2d = Point2d(3, 5)
  val B_HOME_1: Point2d = Point2d(5, 2)
  val B_HOME_2: Point2d = Point2d(5, 3)
  val B_HOME_3: Point2d = Point2d(5, 4)
  val B_HOME_4: Point2d = Point2d(5, 5)
  val C_HOME_1: Point2d = Point2d(7, 2)
  val C_HOME_2: Point2d = Point2d(7, 3)
  val C_HOME_3: Point2d = Point2d(7, 4)
  val C_HOME_4: Point2d = Point2d(7, 5)
  val D_HOME_1: Point2d = Point2d(9, 2)
  val D_HOME_2: Point2d = Point2d(9, 3)
  val D_HOME_3: Point2d = Point2d(9, 4)
  val D_HOME_4: Point2d = Point2d(9, 5)

  val UPPER_1: Point2d = Point2d(1, 1)
  val UPPER_2: Point2d = Point2d(2, 1)
  val UPPER_3: Point2d = Point2d(4, 1)
  val UPPER_4: Point2d = Point2d(6, 1)
  val UPPER_5: Point2d = Point2d(8, 1)
  val UPPER_6: Point2d = Point2d(10, 1)
  val UPPER_7: Point2d = Point2d(11, 1)

  def isBlocked(occupied: Set[Point2d], start: Point2d, end: Point2d): Boolean = {
    val ups = if (start.y == 5) { // Up Four
      List(Point2d(start.x, 4), Point2d(start.x, 3), Point2d(start.x, 2), Point2d(start.x, 1))
    } else if (start.y == 4) { // Up Three
      List(Point2d(start.x, 3), Point2d(start.x, 2), Point2d(start.x, 1))
    } else if (start.y == 3) { // Up Two
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

    val downs = if (end.y == 5) { //
      List(Point2d(end.x, 4), Point2d(end.x, 3), Point2d(end.x, 2), Point2d(end.x, 1))
    } else if (end.y == 4) { //
      List(Point2d(end.x, 3), Point2d(end.x, 2), Point2d(end.x, 1))
    } else if (end.y == 3) { //
      List(Point2d(end.x, 2), Point2d(end.x, 1))
    } else if (end.y == 2) {
      List(Point2d(end.x, 1))
    } else {
      List()
    }

    val moves = end :: ups ::: horzontals ::: downs
    val occupiedSomewhere = moves.exists(point => occupied.contains(point) && point != start)
    if (occupiedSomewhere) {
    }
    occupiedSomewhere
  }
}

object AmphipodHelperTwo {
  def cost(name: Char): Long = {
    name match {
      case 'A' => 1L
      case 'B' => 10L
      case 'C' => 100L
      case 'D' => 1000L
    }
  }

  val UPPERS = List(UPPER_1, UPPER_2, UPPER_3, UPPER_4, UPPER_5, UPPER_6, UPPER_7)

  def findHome(name: Char, home4: Point2d, home3: Point2d, home2: Point2d, home1: Point2d, positions: Map[Point2d, Char]): List[Point2d] = {
    val oneFilled = positions.getOrElse(home1, '*') == name
    val tweFilled = positions.getOrElse(home2, '*') == name
    val threeFilled = positions.getOrElse(home3, '*') == name
    val fourFilled = positions.getOrElse(home4, '*') == name

    if (!fourFilled) List(home4) else if (!threeFilled) List(home3) else if (!tweFilled) List(home2) else if (!oneFilled) List(home1) else List()
  }

  def possibleMoves(name: Char, currentLoc: Point2d, positions: Map[Point2d, Char]): List[Point2d] = {
    if (UPPERS.contains(currentLoc)) {
      name match {
        case 'A' => findHome('A', A_HOME_4, A_HOME_3, A_HOME_2, A_HOME_1, positions)
        case 'B' => findHome('B', B_HOME_4, B_HOME_3, B_HOME_2, B_HOME_1, positions)
        case 'C' => findHome('C', C_HOME_4, C_HOME_3, C_HOME_2, C_HOME_1, positions)
        case 'D' => findHome('D', D_HOME_4, D_HOME_3, D_HOME_2, D_HOME_1, positions)
      }
    } else {
      name match {
        case 'A' => findHome('A', A_HOME_4, A_HOME_3, A_HOME_2, A_HOME_1, positions) ::: UPPERS
        case 'B' => findHome('B', B_HOME_4, B_HOME_3, B_HOME_2, B_HOME_1, positions) ::: UPPERS
        case 'C' => findHome('C', C_HOME_4, C_HOME_3, C_HOME_2, C_HOME_1, positions) ::: UPPERS
        case 'D' => findHome('D', D_HOME_4, D_HOME_3, D_HOME_2, D_HOME_1, positions) ::: UPPERS
      }
    }
  }


  def alreadyInPlace(name : Char, loc : Point2d, home4: Point2d, home3: Point2d, home2: Point2d, home1: Point2d, positions: Map[Point2d, Char]) : Boolean = {
    val tweFilled = positions.getOrElse(home2, '*') == name
    val threeFilled = positions.getOrElse(home3, '*') == name
    val fourFilled = positions.getOrElse(home4, '*') == name

    if (loc == home4) {
      true
    } else if (loc == home3 && fourFilled) {
      true
    } else if (loc == home2 && threeFilled && fourFilled) {
      true
    } else if (loc == home1 && tweFilled && threeFilled && fourFilled) {
      true
    } else {
      false
    }
  }

  def alreadyInPlace(positions: Map[Point2d, Char], loc: Point2d): Boolean = {
    positions(loc) match {
      case 'A' => alreadyInPlace('A', loc, A_HOME_4, A_HOME_3, A_HOME_2, A_HOME_1, positions)
      case 'B' => alreadyInPlace('B', loc, B_HOME_4, B_HOME_3, B_HOME_2, B_HOME_1, positions)
      case 'C' => alreadyInPlace('C', loc, C_HOME_4, C_HOME_3, C_HOME_2, C_HOME_1, positions)
      case 'D' => alreadyInPlace('D', loc, D_HOME_4, D_HOME_3, D_HOME_2, D_HOME_1, positions)
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

class Day23AmphipodTwo(filename: String) extends DailyProblem[Long, Long] {
  case class State(amphipods: Map[Point2d, Char]) {
    def isFinished(): Boolean = {
      amphipods.contains(A_HOME_1) && amphipods.contains(A_HOME_2) && amphipods.contains(A_HOME_3) && amphipods.contains(A_HOME_4) &&
        amphipods.contains(B_HOME_1) && amphipods.contains(B_HOME_2) && amphipods.contains(B_HOME_3) && amphipods.contains(B_HOME_4) &&
        amphipods.contains(C_HOME_1) && amphipods.contains(C_HOME_2) && amphipods.contains(C_HOME_3) && amphipods.contains(C_HOME_4) &&
        amphipods.contains(D_HOME_1) && amphipods.contains(D_HOME_2) && amphipods.contains(D_HOME_3) && amphipods.contains(D_HOME_4) &&
        amphipods(A_HOME_1) == 'A' && amphipods(A_HOME_2) == 'A' &&  amphipods(A_HOME_3) == 'A' && amphipods(A_HOME_4) == 'A' &&
        amphipods(B_HOME_1) == 'B' && amphipods(B_HOME_2) == 'B' &&  amphipods(B_HOME_3) == 'B' && amphipods(B_HOME_4) == 'B' &&
        amphipods(C_HOME_1) == 'C' && amphipods(C_HOME_2) == 'C' &&  amphipods(C_HOME_3) == 'C' && amphipods(C_HOME_4) == 'C' &&
        amphipods(D_HOME_1) == 'D' && amphipods(D_HOME_2) == 'D' &&  amphipods(D_HOME_3) == 'D' && amphipods(D_HOME_4) == 'D'
    }

    def alreadyInPlace(): Int = {
      amphipods.count(p => AmphipodHelperTwo.alreadyInPlace(amphipods, p._1))
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


  def part2(): Long = {
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
              val allPossibleMoves = AmphipodHelperTwo.possibleMoves(amphipod, currentLoc, currentState.amphipods)
              val possibleMoves = allPossibleMoves.filter(p => !isBlocked(currentAmphipodsPositions, currentLoc, p))

              val newStates = possibleMoves.map(newLoc => {
                val newEnergyCost = energySpentSoFar + pathDistance(currentLoc, newLoc) * AmphipodHelperTwo.cost(amphipod)
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

  override val part1Answer: Long = 0
  override val part2Answer: Long = part2()
}


