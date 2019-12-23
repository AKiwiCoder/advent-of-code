package advent.twenty_nineteen

import advent.common.DailyProblem

import scala.annotation.tailrec

class Day23CategorySix(filename: String) extends DailyProblem[Long, Long] {
  private val program = IntComputer.loadProgram(filename)

  val map = (0L to 50L).map(idx => idx -> IntComputerState.copyState(IntComputerState.newState(program), List(idx, -1L))).toMap

  @tailrec
  private def executeNetworkPart1(next: Long, states: Map[Long, IntComputerState]): (Long, Long) = {
    val nextState = states(next)
    val toProcess = if (nextState.input.isEmpty) IntComputerState.copyState(nextState, nextState.input ::: List(-1L)) else nextState
    val newState = IntComputer.execute(toProcess)
    if (newState.output.isEmpty) {
      executeNetworkPart1((next + 1) % 50, states + (next -> newState))
    } else {

      val messages = newState.output.grouped(3).toList

      val natMessages = messages.filter(p => p(0) == 255)
      if (natMessages.nonEmpty) {
        (natMessages.head(1), natMessages.head(2))
      }
      else {
        val newStates = messages.foldLeft(states)((acc, msg) => {
          val destAddress = msg(0)
          val oldDestState = acc(destAddress)
          val newDestState = IntComputerState.copyState(oldDestState, oldDestState.input ::: List(msg(1), msg(2)))

          acc + (destAddress -> newDestState)
        })
        val newSrcState = IntComputerState.copyState(newState, newState.input.take(3))
        executeNetworkPart1((next + 1) % 50, newStates + (next -> newSrcState))
      }
    }
  }

  private def executeNetworkPart2(next: Long, idleCount: Int, states: Map[Long, IntComputerState], lastNat: List[(Long, Long)], natSent: Set[Long]): Long = {
    if (idleCount == 50) {
      val idleMessage = lastNat.head
      if (natSent.contains(idleMessage._2)) {
        lastNat.head._2
      } else {
        val newStates = states + (0L -> IntComputerState.copyState(states(0), List(idleMessage._1, idleMessage._2)))
        executeNetworkPart2(0, 0, newStates, List(), natSent + idleMessage._2)
      }
    } else {
      val nextState = states(next)
      val toProcess = if (nextState.input.isEmpty) IntComputerState.copyState(nextState, nextState.input ::: List(-1L)) else nextState
      val newState = IntComputer.execute(toProcess)
      if (newState.output.isEmpty) {
        executeNetworkPart2((next + 1) % 50, idleCount + 1, states + (next -> newState), lastNat, natSent)
      } else {
        val messages = newState.output.grouped(3).toList
        val natMessages = messages.filter(p => p(0) == 255)
        val newLastNat = natMessages.foldLeft(lastNat)((acc, msg) => (msg(1), msg(2)) :: acc)
        val newStates = messages.filter(msg => msg(0) != 255).foldLeft(states)((acc, msg) => {
          val destAddress = msg(0)
          val oldDestState = acc(destAddress)
          val newDestState = IntComputerState.copyState(oldDestState, oldDestState.input ::: List(msg(1), msg(2)))

          acc + (destAddress -> newDestState)
        })
        val newSrcState = IntComputerState.copyState(newState, newState.input.take(3))
        executeNetworkPart2((next + 1) % 50, 0, newStates + (next -> newSrcState), newLastNat, natSent)
      }
    }
  }

  override val part1Answer: Long = executeNetworkPart1(0, map)._2
  override val part2Answer: Long = executeNetworkPart2(0, 0, map, List(), Set())
}


