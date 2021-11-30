package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities
import scala.annotation.tailrec

abstract class Item {

}

case class Microchip(name: String) extends Item

case class Generator(name: String) extends Item

class Day11RadioisotopeThermoelectricGenerators(filename: String) extends DailyProblem[Int, Int] {
//  private val generatorPattern = "a ([^\\s]+) generator".r
//  private val microchipPattern = "a ([^\\s]+) microchip".r
//  private val floorPattern = "The ([^\\s]+) floor".r
//
//  def parser(line: String): List[(Int, Item)] = {
//    val floor : Int = floorPattern.findFirstMatchIn(line).get.group(1) match {
//        case "first" => 1
//        case "second" => 2
//        case "third" => 3
//        case "fourth" => 4
//    }
//
//    val withGens = generatorPattern.findAllMatchIn(line).toList.foldLeft(List[(Int, Item)]())((list, generator) => (floor, Generator(generator.group(1))) :: list)
//    val withChips = microchipPattern.findAllMatchIn(line).toList.foldLeft(withGens)((list, microchip) => (floor, Microchip(microchip.group(1))) :: list)
//
//    withChips
//  }
//
//  private val floors = FileUtilities.readFile(filename, parser)
//
//  println(floors)

  override val part1Answer: Int = 0
  override val part2Answer: Int = 0
}

