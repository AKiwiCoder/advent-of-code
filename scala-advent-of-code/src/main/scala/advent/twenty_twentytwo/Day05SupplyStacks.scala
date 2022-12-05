package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05SupplyStacks(filename : String) extends DailyProblem[String, String] {
  private val input = FileUtilities.readFile(filename)

  private val stacks = input.filter(p => !p.trim().isEmpty && !p.startsWith("move")).reverse

  println(stacks)

  private def split(l : String) : List[Char] = l.grouped(4).map(f => if (f.trim().isEmpty) ' ' else f(1)).toList

  private val piles = stacks.map(line => split(line))

  @tailrec
  private def concat(idx : Int, data : List[List[Char]], acc : List[Char]) : List[Char] = {
    if (data.isEmpty) {
      acc
    } else {
      val c = data.head(idx)
      if (c == ' ') {
        concat(idx, data.tail, acc)
      } else {
        concat(idx, data.tail, c :: acc)
      }
    }
  }

  private val start_piles = piles.head.indices.map(p => concat(p, piles.tail, List()))

  private val start_position = piles.head.indices.map(idx => (idx + 1) -> start_piles(idx)).toMap

  println(start_position)

  private val entry = "move ([0-9]+) from ([0-9]+) to ([0-9]+)".r

  case class Move(cnt : Int, from : Int, to: Int)

  private val moves = input.filter(p => p.startsWith("move")).map(p => p match {
    case entry(c,f,t) => Move(c.toInt, f.toInt, t.toInt)
  })

  private def perform_1(moves : List[Move], position : Map[Int, List[Char]]) : Map[Int, List[Char]] = {
    if (moves.isEmpty) {
      position
    } else {
      val m = moves.head
      val taken : List[Char] = position(m.from).slice(0, m.cnt)
      val np1 : Map[Int, List[Char]] = position + (m.from -> position(m.from).drop(m.cnt))
      val np2  : Map[Int, List[Char]] = np1 + (m.to -> (taken.reverse ::: position(m.to)))
      perform_1(moves.tail, np2)
    }
  }

  private def perform_2(moves: List[Move], position: Map[Int, List[Char]]): Map[Int, List[Char]] = {
    if (moves.isEmpty) {
      position
    } else {
      val m = moves.head
      val taken: List[Char] = position(m.from).slice(0, m.cnt)
      val np1: Map[Int, List[Char]] = position + (m.from -> position(m.from).drop(m.cnt))
      val np2: Map[Int, List[Char]] = np1 + (m.to -> (taken ::: position(m.to)))
      perform_2(moves.tail, np2)
    }
  }


  private val end_position_1 = perform_1(moves, start_position)
  private val end_position_2 = perform_2(moves, start_position)

  override val part1Answer: String = piles.head.indices.map(idx => end_position_1(idx+1).head).toList.foldLeft("")((s,c) => s + c)
  override val part2Answer:  String = piles.head.indices.map(idx => end_position_2(idx+1).head).toList.foldLeft("")((s,c) => s + c)
}

