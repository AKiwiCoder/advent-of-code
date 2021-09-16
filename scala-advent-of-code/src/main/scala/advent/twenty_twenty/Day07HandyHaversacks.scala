package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Bag(name: String, canContain: Map[String, Int])

class Day07HandyHaversacks(filename: String) extends DailyProblem[Int, Int] {

  private val pattern = "([0-9)+])([a-z ]+)".r

  private def parse(line: String): Bag = {
    val index = line.indexOf(" bags contain")
    val name = line.substring(0, index).trim
    val holds = line.substring(index + 13).split(",").foldLeft(Map[String, Int]())((acc, hold) => hold.substring(0, hold.indexOf("bag")).trim match {
      case pattern(count, name) => (acc + (name.trim -> count.toInt))
      case "no other" => acc
    })
    Bag(name, holds)
  }

  private val input = FileUtilities.readFile(filename, parse)

  private def part1(): Int = {
    @tailrec
    def calc(remaining: Set[String], names: Set[String]): Set[String] = {
      if (remaining.isEmpty) {
        names
      } else {
        val target = remaining.head
        if (names.contains(target)) {
          calc(remaining.tail, names)
        } else {
          val left = remaining.tail
          val canHold = input.filter(bag => bag.canContain.keys.count(name => name.equals(target)) > 0).map(bag => bag.name).toSet
          calc(left ++ canHold, names + target)
        }
      }
    }

    calc(Set("shiny gold"), Set()).size - 1
  }

  def part2(): Int = {

    val bags = input.map(bag => bag.name -> bag).toMap

    def calc(target: String): Int = {
      val bag = bags(target)
      val ans = bag.canContain.map(entry => {
        entry._2 * calc(entry._1)
      }).sum
      1 + ans
    }

    calc("shiny gold") - 1
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


