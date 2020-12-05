package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.ChecksumUtilities

import scala.annotation.tailrec

class Day05HowAboutANiceGameOfChess(input: String) extends DailyProblem[String, String] {

  private def generatePassword(password : Map[Char, Char]) : String = {
    "" +password('0') + password('1') + password('2') + password('3') + password('4') + password('5') + password('6') + password('7')
  }

  @tailrec
  private def generatePassword(index : Long, part1Password : String, part2Password : Map[Char, Char]) : (String, String) = {
    val result = generatePassword(part2Password)
    if (result.indexOf('-') < 0) {
      (part1Password, result)
    } else {
      val md5 = ChecksumUtilities.md5hashString(input + index)
      generatePassword(
        index + 1,
        if (md5.startsWith("00000") && part1Password.size < 8) part1Password + md5.charAt(5) else part1Password,
        if (md5.startsWith("00000") && part2Password(md5.charAt(5)) == '-') {
          part2Password + (md5.charAt(5) -> md5.charAt(6))
        } else {
          part2Password
        })
    }
  }

  val answer = generatePassword(0, "", Map[Char, Char]().withDefaultValue('-'))

  override val part1Answer: String = answer._1
  override val part2Answer: String = answer._2
}
