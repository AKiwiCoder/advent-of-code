package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Ingredient(name: String, amount: Long)

class Day14SpaceStoichiometry(filename: String) extends DailyProblem[Long, Long] {
  private val ingredient = "([0-9]*) ([A-Z]*)".r

  private def parser(line: String): (List[Ingredient], Ingredient) = {
    val x = line.split("=>")
    val src = x(0)
    val dest = x(1)

    val out = dest.trim match {
      case ingredient(a, n) => Ingredient(n, a.toLong)
    }

    (src.split(",").map(str => str.trim match {
      case ingredient(a, n) => Ingredient(n, a.toLong)
    }).toList, out)
  }

  private val input = FileUtilities.readFile(filename, parser)

  @tailrec
  private def doPart1(required: Map[String, Long], used: Map[String, Long], surplus: Map[String, Long]): Map[String, Long] = {
    if (required.isEmpty) {
      used
    } else {
      val req = required.head
      if (req._1 == "ORE") {
          doPart1(required.tail, used + ("ORE" -> (used.getOrElse("ORE", 0L) + req._2)), surplus)
      } else {
        val recipe = input.filter(entry => entry._2.name == req._1).head

        val surplusAvailable = surplus.getOrElse(req._1, 0L)
        val neededAmount = req._2 - surplusAvailable

        val multiplier = Math.round(Math.ceil(neededAmount.toDouble / recipe._2.amount.toDouble)).toLong
        val stuffRequired = recipe._1.map(r => r.name -> r.amount * multiplier).toMap
        val stuffMade = Ingredient(recipe._2.name, recipe._2.amount * multiplier)

        val newRequired = (required.tail.keySet ++ stuffRequired.keySet).map(e => e -> (required.getOrElse(e, 0L) + stuffRequired.getOrElse(e, 0L))).toMap

        doPart1(newRequired, used + (req._1 -> (used.getOrElse(req._1,0L) + req._2)), surplus + (req._1 -> (surplusAvailable + stuffMade.amount - req._2)))
      }
    }
  }

  private def doPart2(low : Long, high : Long): Long = {
    val mid = (high + low) / 2
    if (mid == high || mid == low) {
      mid
    } else {
      if (doPart1(Map("FUEL" -> mid), Map(), Map())("ORE") >= 1000000000000L) {
        doPart2(low, mid)
      } else {
        doPart2(mid, high)
      }
    }
  }

  override val part1Answer: Long = doPart1(Map("FUEL" -> 1), Map(), Map())("ORE")
  override val part2Answer: Long = doPart2(1, 1000000000000L)
}


