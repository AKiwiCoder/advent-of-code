package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.Int
import scala.annotation.tailrec

class Day13DistressSignal(filename: String) extends DailyProblem[Int, Int] {
  def parse(line : String): List[Any] = {
    val split = line.replace("[", "[,").replace("]", ",]").split(",").toList.filter(_.nonEmpty)
    def walk(l : List[String], working : List[Any]) : (List[String], List[Any]) = {
        if (l.isEmpty) {
          (Nil, working)
        } else {
          val h = l.head
          if (h == "[") {
            val lst = walk(l.tail, List())
            walk(lst._1, (lst._2.reverse :: working))
          } else if (h == "]") {
            (l.tail, working)
          } else {
            walk(l.tail, h.toInt :: working)
          }
        }
      }
    walk(split, List())._2.reverse.head.asInstanceOf[List[Any]]
  }

  private def in_order(left: List[Any], right: List[Any]): Option[Boolean] = {
    if (left.isEmpty && right.isEmpty) {
      Option.empty
    } else if (left.isEmpty) {
      Option.apply(true)
    } else if (right.isEmpty) {
      Option.apply(false)
    } else {
      (left.head, right.head) match {
        case (l: Int, r: Int) => if (l < r) Option.apply(true) else if (l > r) Option.apply(false) else in_order(left.tail, right.tail)
        case (l: List[Any], r: List[Any]) => in_order(l, r) match {
          case Some(t) => Option.apply(t)
          case None => in_order(left.tail, right.tail)
        }
        case (l: Int, r: List[Any]) => in_order(List(l), r)
        case (l: List[Any], r: Int) => in_order(l, List(r))
      }
    }
  }

  private val divider2 = List(List(2))
  private val divider6 = List(List(6))


  private val input = FileUtilities.readFile(filename).filter(_.nonEmpty).map(parse).grouped(2).toList
  private val inputPart2 = (divider2 :: divider6 :: input.flatten).sortWith((l,r) => in_order(l,r).getOrElse(false))

  override val part1Answer: Int = input.indices.map(p => if (in_order(input(p)(0), input(p)(1)).get) p + 1 else 0).sum
  override val part2Answer: Int = (inputPart2.indexOf(divider2) + 1) * (inputPart2.indexOf(divider6) + 1)
}
