package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day21AllergenAssessment(filename: String) extends DailyProblem[Int, String] {

  private def parse(line: String): (List[String], List[String]) = {
    val name = line.substring(0, line.indexOf("(contains")).split(" ").map(_.trim).toList
    val allergens = line.substring(line.indexOf("(contains") + 10, line.indexOf(")")).split(",").map(_.trim).toList
    (name, allergens)
  }

  private val input = FileUtilities.readFile(filename, parse)
  
  @tailrec
  private def cleanup(working: Map[String, Set[String]]): Map[String, String] = {
    if (working.forall(entry => entry._2.size == 1)) {
      working.map(entry => entry._1 -> entry._2.head)
    } else {
      val identified = working.filter(entry => entry._2.size == 1)
      val used = identified.map(_._2.head).toSet
      val remaining = working.filterNot(entry => entry._2.size == 1)
      val newWorking = remaining.map(entry => entry._1 -> entry._2.diff(used))
      cleanup(newWorking ++ identified)
    }
  }

  private val possibleAllegenIngredients = input.foldLeft(Map[String, Set[String]]())((acc, entry) => entry._2.foldLeft(acc)((acc, allegen) => acc + (allegen -> acc.getOrElse(allegen, entry._1.toSet).intersect(entry._1.toSet))))
  private val allegenIngredients = cleanup(possibleAllegenIngredients)
  private val unsafeIngredients = allegenIngredients.values.toSet
  private val safeIngredentsCounts = input.map(entry => entry._1.count(ingredient => !unsafeIngredients.contains(ingredient)))

  override val part1Answer: Int = safeIngredentsCounts.sum
  override val part2Answer: String = allegenIngredients.toList.sortBy(_._1).map(_._2).mkString(",")
}


