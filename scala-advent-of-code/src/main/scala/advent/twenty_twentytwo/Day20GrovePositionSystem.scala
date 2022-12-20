package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day20GrovePositionSystem(filename: String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  case class Node(index: Int, value: Long)

  @tailrec
  private def step(current: Node, count: Long, next: Map[Node, Node], prev: Map[Node, Node]): Node = {
    if (count == 0)
      current
    else if (count > 0)
      step(next(current), count - 1, next, prev)
    else
      step(prev(current), count + 1, next, prev)
  }

  @tailrec
  private def locateZero(current: Node, next: Map[Node, Node]): Node = if (current.value == 0) current else locateZero(next(current), next)

  @tailrec
  private def locateIndex(current: Node, index: Int, next: Map[Node, Node]): Node = if (current.index == index) current else locateIndex(next(current), index, next)

  def move(after: Boolean, source: Node, destination: Node, next: Map[Node, Node], prev: Map[Node, Node]): (Node, Map[Node, Node], Map[Node, Node]) = {
    if (source == destination) {
      (source, next, prev)
    } else {
      val srcNext = next(source)
      val srcPrev = prev(source)
      val newNext = next + (srcPrev -> srcNext)
      val newPrev = prev + (srcNext -> srcPrev)

      if (after) {
        val dstNext = newNext(destination)

        val newNewNext = newNext + (source -> dstNext, destination -> source)
        val newNewPrev = newPrev + (dstNext -> source, source -> destination)

        (source, newNewNext, newNewPrev)
      } else {
        val dstPrev = newPrev(destination)

        val newNewNext = newNext + (dstPrev -> source, source -> destination)
        val newNewPrev = newPrev + (destination -> source, source -> dstPrev)

        (source, newNewNext, newNewPrev)
      }
    }
  }

  def remove(source : Node, next : Map[Node, Node], prev : Map[Node,Node]) : (Map[Node, Node], Map[Node,Node]) = {
    val srcNext = next(source)
    val srcPrev = prev(source)
    (next + (srcPrev -> srcNext), prev + (srcNext -> srcPrev))
  }

  def insert(after: Boolean, source: Node, destination: Node, next: Map[Node, Node], prev: Map[Node, Node]): (Node, Map[Node, Node], Map[Node, Node]) = {
      if (after) {
        val dstNext = next(destination)

        val newNewNext = next + (source -> dstNext, destination -> source)
        val newNewPrev = prev + (dstNext -> source, source -> destination)

        (source, newNewNext, newNewPrev)
      } else {
        val dstPrev = prev(destination)

        val newNewNext = next + (dstPrev -> source, source -> destination)
        val newNewPrev = prev + (destination -> source, source -> dstPrev)

        (source, newNewNext, newNewPrev)
      }
    }

  @tailrec
  private def mix(indexes: List[Int], current: Node, cNext: Map[Node, Node], cPrev: Map[Node, Node]): (Node, Map[Node, Node], Map[Node, Node]) = {
    if (indexes.isEmpty) {
      (locateZero(current, cNext), cNext, cPrev)
    } else {
      val source = locateIndex(current, indexes.head, cNext)
      val start = if (source.value >= 0) cNext(source) else cPrev(source)

      val (removedNext, removedPrev) = remove(source, cNext, cPrev)

      val destination = step(start, (source.value % (input.size -1)), removedNext, removedPrev)

      val (newCurrent, newNext, newPrev) = insert(source.value < 0, source, destination, removedNext, removedPrev)
      mix(indexes.tail, newCurrent, newNext, newPrev)
    }
  }

  @tailrec
  private def run(count : Int, indexes: List[Int], current: Node, cNext: Map[Node, Node], cPrev: Map[Node, Node]): (Node, Map[Node, Node], Map[Node, Node]) = {
    if (count == 0) {
      (locateZero(current, cNext), cNext, cPrev)
    } else {
      val (nC, nN, nP) = mix(indexes, current, cNext, cPrev)
      run(count - 1, indexes, nC, nN, nP)
    }
  }

  private val nodeDict1 = input.indices.map(idx => Node(idx, input(idx).toLong))
  private val origNext1 = nodeDict1.map(n => n -> nodeDict1((n.index + 1) % nodeDict1.size)).toMap
  private val origPrev1 = nodeDict1.map(n => n -> nodeDict1((n.index - 1 + nodeDict1.size) % nodeDict1.size)).toMap

  private val nodeDict2= input.indices.map(idx => Node(idx, input(idx).toLong * 811589153L))
  private val origNext2 = nodeDict2.map(n => n -> nodeDict2((n.index + 1) % nodeDict2.size)).toMap
  private val origPrev2 = nodeDict2.map(n => n -> nodeDict2((n.index - 1 + nodeDict2.size) % nodeDict2.size)).toMap

  private val (z1, n1, p1) = mix(input.indices.toList, nodeDict1(0), origNext1, origPrev1)
  private val (z2, n2, p2) = run(10, input.indices.toList, nodeDict2(0), origNext2, origPrev2)

  override val part1Answer: Long = step(z1, 1000, n1, p1).value + step(z1, 2000, n1, p1).value + step(z1, 3000, n1, p1).value
  override val part2Answer: Long = step(z2, 1000, n2, p2).value + step(z2, 2000, n2, p2).value + step(z2, 3000, n2, p2).value
}
