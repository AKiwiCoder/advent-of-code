package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec
import scala.collection.immutable.Map

class Day16ProboscideaVolcanium(filename: String) extends DailyProblem[Int, Int] {
  val line = "Valve (\\w+) has flow rate=(\\d+); [\\w]+ [\\w]+ to [\\w]+ ([\\w, ]*)".r

  case class Valve(name: String, flow: Int, lead: List[String])

  private val input = FileUtilities.readFile(filename).map {
    case line(n, f, l) => Valve(n, f.toInt, l.split(",").map(_.trim).toList)
  }.map(node => node.name -> node).toMap

  case class Part1State(current: String, opened: Set[String], flowed: Int, tick: Int)

  @tailrec
  private def part1(stack: Set[Part1State], best: Map[Int, Int]): Int = {
    if (stack.isEmpty) {
      best(30)
    } else {
      val h = stack.head
      if (h.tick > 30) {
        part1(stack.tail, best)
      } else if (best.getOrElse(h.tick-1, 0) > h.flowed) {
       part1(stack.tail, best)
      } else {
        val newBestFlow = best + (h.tick -> Math.max(best.getOrElse(h.tick, 0), (h.flowed)))
        val updatedFlow = h.opened.foldLeft(h.flowed)((acc, n) => acc + input(n).flow)

        if (h.opened.size == input.size) {
          // If all values open ... stay still
          val stayHere = Part1State(h.current, h.opened, updatedFlow, h.tick + 1)
          part1(stack.tail + stayHere, newBestFlow)
        } else if (h.opened.contains(h.current) || input(h.current).flow == 0) {
          // If we have already opened the value in this room, move to another room
          val newPendingStack = input(h.current).lead.foldLeft(stack.tail)((acc, next) => {
            acc + Part1State(next, h.opened, updatedFlow, h.tick + 1)
          })

          part1(newPendingStack, newBestFlow)
        } else {
          // Open the valve in this room
          val openThisValve = Part1State(h.current, h.opened + h.current, updatedFlow, h.tick + 1)
          val pending = stack.tail + openThisValve
          val newPendingStack = input(h.current).lead.foldLeft(pending)((acc, next) => {
            acc + Part1State(next, h.opened, updatedFlow, h.tick + 1)
          })

          part1(newPendingStack, newBestFlow)
        }
      }
    }
  }

  case class Part2State(currentMe: String, currentElephant: String, meMove: Boolean, opened: Set[String], pendingFlowed : Int, flowed: Int, tick: Int)

  @tailrec
  private def part2(stack: Set[Part2State], best: Map[Int, Int]): Int = {
    if (stack.size % 1000 == 0) {
      println(stack.size + " " + best.getOrElse(26, 0))
    }
    if (stack.isEmpty) {
      best(26)
    } else {
      val h = stack.head
      if (h.tick > 26) {
        part2(stack.tail, best)
      } else if (best.getOrElse(h.tick - 1, 0) > h.flowed) {
        part2(stack.tail, best)
      } else {
        val newBestFlow = best + (h.tick -> Math.max(best.getOrElse(h.tick, 0), h.flowed))

        val updatedPendingFlowed = if (h.meMove) h.opened.foldLeft(h.flowed)((acc, n) => acc + input(n).flow) else h.pendingFlowed

        val updatedFlow = if (h.meMove) h.flowed else h.pendingFlowed

        if (h.opened.size == input.size) {
          // If all valves open ... stay still
          val stayHere = Part2State(h.currentMe, h.currentElephant, !h.meMove, h.opened, updatedPendingFlowed, updatedFlow, if (h.meMove) h.tick else h.tick + 1)
          part2(stack.tail + stayHere, newBestFlow)
        } else {
          if (h.meMove) {
            // My Move
            if (h.opened.contains(h.currentMe) || input(h.currentMe).flow == 0) {
              // If we have already opened the value in this room, move to another room
              val newPendingStack = input(h.currentMe).lead.foldLeft(stack.tail)((acc, next) => {
                acc + Part2State(next,  h.currentElephant, !h.meMove, h.opened, updatedPendingFlowed, updatedFlow, h.tick)
              })

              part2(newPendingStack, newBestFlow)
            } else {
              // Open the valve in this room
              val openThisValve = Part2State(h.currentMe,  h.currentElephant, !h.meMove, h.opened + h.currentMe, updatedPendingFlowed, updatedFlow, h.tick)
              val pending = stack.tail + openThisValve
              val newPendingStack = input(h.currentMe).lead.foldLeft(pending)((acc, next) => {
                acc + Part2State(next, h.currentElephant, !h.meMove, h.opened, updatedPendingFlowed, updatedFlow, h.tick)
              })

              part2(newPendingStack, newBestFlow)
            }
          } else {
            // Elephants Turn
            if (h.opened.contains(h.currentElephant) || input(h.currentElephant).flow == 0) {
              // If we have already opened the value in this room, move to another room
              val newPendingStack = input(h.currentElephant).lead.foldLeft(stack.tail)((acc, next) => {
                acc + Part2State(h.currentMe, next, !h.meMove, h.opened, updatedPendingFlowed, updatedFlow, h.tick + 1)
              })

              part2(newPendingStack, newBestFlow)
            } else {
              // Open the valve in this room
              val openThisValve = Part2State(h.currentMe, h.currentElephant, !h.meMove, h.opened + h.currentElephant, updatedPendingFlowed, updatedFlow, h.tick + 1)
              val pending = stack.tail + openThisValve
              val newPendingStack = input(h.currentElephant).lead.foldLeft(pending)((acc, next) => {
                acc + Part2State(h.currentMe, next, !h.meMove, h.opened, updatedPendingFlowed, updatedFlow, h.tick + 1)
              })

              part2(newPendingStack, newBestFlow)
            }
          }
        }
      }
    }
  }

  override val part1Answer: Int = part1(Set(Part1State("AA", Set(), 0, 0)), Map())
  override val part2Answer: Int = part2(Set(Part2State("AA", "AA", meMove = true, Set(), 0, 0, 0)), Map())
}
