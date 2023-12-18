package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.LocationHelper.{step, turn}
import advent.utilities._

import scala.annotation.tailrec
import scala.collection.mutable

class Day17ClumsyCrucible(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private def heatloss(point: Point) = input(point.y)(point.x).asDigit

  private val maxY = input.length
  private val maxX = input.head.length

  private def isValid(point: Point): Boolean = point.y >= 0 && point.x >= 0 && point.y < maxY && point.x < maxX

  private val start = Point(0, 0)
  private val end = Point(input.length - 1, input.head.length - 1)

   case class StateKey(path: Point, facing: Facing, steps: Int)

   case class State(path: Point, facing: Facing, steps: Int, heatLoss: Int) {
    def key(): StateKey = StateKey(path, facing, steps)
  }

  private def addIfBetter(newState: State, oldTodo: mutable.PriorityQueue[State], oldBest: Map[StateKey, Int]): (mutable.PriorityQueue[State], Map[StateKey, Int]) = {
    val key = newState.key()
    val heatLimit = Math.min(oldBest.getOrElse(key, Int.MaxValue), oldBest.getOrElse(key, Int.MaxValue))

    val result = if (newState.heatLoss < heatLimit) {
      oldTodo.enqueue(newState)
      (oldTodo, oldBest + (key -> newState.heatLoss))
    } else {
      (oldTodo, oldBest)
    }

    (result._1, result._2)
  }

  def dist(state: State): Int = {
    Point.manhattanDistance(state.path, end)
  }

  @tailrec
  private def moveCrucible(todo: mutable.PriorityQueue[State], bestSoFar: Map[StateKey, Int]): Int = {
    if (todo.isEmpty) {
      bestSoFar.filter(p => p._1.path == end).values.min
    } else {
      val current = todo.dequeue()

      val forwardLocation = step(current.path, current.facing)

      val leftFacing = turn(current.facing, TurnLeft())
      val leftLocation = step(current.path, leftFacing)

      val rightFacing = turn(current.facing, TurnRight())
      val rightLocation = step(current.path, rightFacing)

      val oldTodo = todo
      val (todoForward, bestForward) = if (isValid(forwardLocation) && current.steps < 3) {
        addIfBetter(State(forwardLocation, current.facing, current.steps + 1, current.heatLoss + heatloss(forwardLocation)), oldTodo, bestSoFar)
      } else {
        (oldTodo, bestSoFar)
      }

      val (todoLeft, bestLeft) = if (isValid(leftLocation)) {
        addIfBetter(State(leftLocation, leftFacing, 1, current.heatLoss + heatloss(leftLocation)), todoForward, bestForward)
      } else {
        (todoForward, bestForward)
      }

      val (todoRight, bestRight) = if (isValid(rightLocation)) {
        addIfBetter(State(rightLocation, rightFacing, 1, current.heatLoss + heatloss(rightLocation)), todoLeft, bestLeft)
      } else {
        (todoLeft, bestLeft)
      }

      moveCrucible(todoRight, bestRight)
    }
  }

  @tailrec
  private def moveUltraCrucible(todo: mutable.PriorityQueue[State], bestSoFar: Map[StateKey, Int]): Int = {
    if (todo.isEmpty) {
      bestSoFar.filter(p => p._1.path == end).values.min
    } else {
      val current = todo.dequeue()

      val forwardLocation = step(current.path, current.facing)

      val oldTodo = todo
      val (todoForward, bestForward) = if (isValid(forwardLocation) && current.steps < 10) {
        addIfBetter(State(forwardLocation, current.facing, current.steps + 1, current.heatLoss + heatloss(forwardLocation)), oldTodo, bestSoFar)
      } else {
        (oldTodo, bestSoFar)
      }

      val (todoLeft, bestLeft) = if (current.steps >= 4) {
        val leftFacing = turn(current.facing, TurnLeft())

        val leftLocation0 = step(current.path, leftFacing)
        val leftLocation1 = step(leftLocation0, leftFacing)
        val leftLocation2 = step(leftLocation1, leftFacing)
        val leftLocation3 = step(leftLocation2, leftFacing)

        if (isValid(leftLocation3)) {
          val pathHeatloss = current.heatLoss + heatloss(leftLocation0) + heatloss(leftLocation1) + heatloss(leftLocation2) + heatloss(leftLocation3);
          addIfBetter(State(leftLocation3, leftFacing, 4, pathHeatloss), todoForward, bestForward)
        } else {
          (todoForward, bestForward)
        }
      } else {
        (todoForward, bestForward)
      }

      val (todoRight, bestRight) = if (current.steps >= 4) {
        val rightFacing = turn(current.facing, TurnRight())

        val rightLocation0 = step(current.path, rightFacing)
        val rightLocation1 = step(rightLocation0, rightFacing)
        val rightLocation2 = step(rightLocation1, rightFacing)
        val rightLocation3 = step(rightLocation2, rightFacing)


        if (isValid(rightLocation3)) {
          val pathHeatloss = current.heatLoss + heatloss(rightLocation0) + heatloss(rightLocation1) + heatloss(rightLocation2) + heatloss(rightLocation3);
          addIfBetter(State(rightLocation3, rightFacing, 4, pathHeatloss), todoLeft, bestLeft)
        } else {
          (todoLeft, bestLeft)
        }
      } else {
        (todoLeft, bestLeft)
      }

      moveUltraCrucible(todoRight, bestRight)
    }
  }


  private def order(state: State) = dist(state)

  val startSouth0 = step(start, FacingSouth())
  val startSouth1 = step(startSouth0, FacingSouth())
  val startSouth2 = step(startSouth1, FacingSouth())
  val startSouth3 = step(startSouth2, FacingSouth())

  val startEast0 = step(start, FacingEast())
  val startEast1 = step(startEast0, FacingEast())
  val startEast2 = step(startEast1, FacingEast())
  val startEast3 = step(startEast2, FacingEast())

  val startUltraSouth = State(startSouth3,FacingSouth(), 4, heatloss(startSouth0) + heatloss(startSouth1) + heatloss(startSouth2) + heatloss(startSouth3))
  val startUltraEast = State(startEast3,FacingEast(), 4, heatloss(startEast0) + heatloss(startEast1) + heatloss(startEast2) + heatloss(startEast3))

  override val part1Answer: Int = moveCrucible(mutable.PriorityQueue(State(start, FacingSouth(), 0, 0), State(start, FacingEast(), 0, 0))(Ordering.by(order)), Map())
  override val part2Answer: Int = moveUltraCrucible(mutable.PriorityQueue(startUltraSouth, startUltraEast)(Ordering.by(order)), Map())
}


