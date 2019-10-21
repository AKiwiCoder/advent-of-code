package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day04SecurityThroughObscurity(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private def sorter(lhs: (Char, Int), rhs: (Char, Int)): Boolean = {
    if (lhs._2.equals(rhs._2)) {
      lhs._1 < rhs._1
    } else {
      lhs._2 > rhs._2
    }
  }

  private def validate(room: String): Boolean = {
    val checkSum = room.take(room.lastIndexOf('-')).filter(c => c != '-').foldLeft(Map[Char, Int]().withDefaultValue(0))((acc, c) => acc + (c -> (acc(c) + 1))).toList.sortWith(sorter).take(5).map(x => x._1).mkString
    room.endsWith("[" + checkSum + "]")
  }

  private def decrypt(key: String, count: Int): String =  key.map(c => (c match {
      case '-' => ' '
      case _ => (((c.toInt - 'a'.toInt + count) % 26) + 'a'.toInt).toChar
    })).mkString

  private val getId = "-([0-9]*)\\[([a-z]*)\\]".r

  private def getId(room: String): Int = room.drop(room.lastIndexOf('-')) match {
    case getId(id, checksum) => id.toInt
  }

  private val valid = input.filter(x => validate(x))

  private val decrypted = valid.map(room => (getId(room), room.take(room.lastIndexOf('-')))).foldLeft(Map[String, Int]())((acc, pair) => acc + (decrypt(pair._2, pair._1) -> pair._1))

  override val part1Answer: Int = valid.map(room => getId(room)).sum
  override val part2Answer: Int = decrypted.withDefaultValue(-1)("northpole object storage")
}
