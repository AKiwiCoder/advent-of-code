package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day04PassportProcessing(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  val entryPattern = "([a-z]+):(.+)".r

  def parse(input: List[String], fields: Map[String, String], acc: List[Map[String, String]]): List[Map[String, String]] = {
    if (input == Nil) {
      fields :: acc
    } else {
      if (input.head.trim.isEmpty) {
        parse(input.tail, Map(), fields :: acc)
      } else {
        val newFields = input.head.split(" ").foldLeft(Map[String, String]())((fields, field) => field match {
          case entryPattern(a, b) => fields + (a -> b)
        })
        parse(input.tail, fields ++ newFields, acc)
      }
    }
  }

  val passports = parse(input, Map(), List())

  def isValid(passport: Map[String, String]): Boolean = (passport.size == 8) || (passport.size == 7 && !passport.contains("cid"))

  def part1(): Int = passports.count(passport => isValid(passport))

  def isBetween(value: Int, min: Int, max: Int): Boolean = min <= value && value <= max

  val inPattern = "([0-9]+)in".r
  val cmPattern = "([0-9]+)cm".r
  val hclPattern = "#[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]".r
  val pidPattern = "[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f]".r

  def isValidField(passport: Map[String, String]): Boolean = {
    val byr = isBetween(passport("byr").toInt, 1920, 2002)
    val iyr = isBetween(passport("iyr").toInt, 2010, 2020)
    val eyr = isBetween(passport("eyr").toInt, 2020, 2030)
    val hgt = passport("hgt") match {
      case inPattern(h) => isBetween(h.toInt, 59, 76)
      case cmPattern(h) => isBetween(h.toInt, 150, 193)
      case _ => false
    }
    val hcl = passport("hcl") match {
      case hclPattern() => true
      case _ => false
    }
    val ecl = passport("ecl") match {
      case "amb" | "blu" | "brn" | "gry" | "grn" | "hzl" | "oth" => true
      case _ => false
    }
    val pid = passport("pid") match {
      case pidPattern() => true
      case _ => false
    }

    byr && iyr && eyr && hgt && hcl && ecl && pid
  }

  def part2(): Int = {
    passports.filter(passport => isValid(passport)).count(passport => isValidField(passport))
  }

  //    byr (Birth Year) - four digits; at least 1920 and at most 2002.
  //    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
  //    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
  //    hgt (Height) - a number followed by either cm or in:
  //      If cm, the number must be at least 150 and at most 193.
  //    If in, the number must be at least 59 and at most 76.
  //    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
  //      ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
  //    pid (Passport ID) - a nine-digit number, including leading zeroes.
  //      cid (Country ID) - ignored, missing or not.


  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


