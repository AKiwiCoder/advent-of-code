package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities
import net.liftweb.json._

class Day12JSAbacusFrameworkIO(filename: String) extends DailyProblem[Int, Int] {
  private val jsons = FileUtilities.readFile(filename, parse)

  def sum(node: Any) : Int = {
    node match {
      case JInt(n) => n.toInt
      case JObject(fields) => fields.map(n => sum(n)).sum
      case JArray(fields) => fields.map(n => sum(n)).sum
      case JField(_, value) => sum(value)
      case JString(_) => 0
    }
  }

  def hasRed(node : Any) : Boolean = {
    node match {
      case JInt(_) => false
      case JObject(_) => false;
      case JArray(_) => false
      case JField(_, value) => hasRed(value, false)
      case JString(a) => a.equals("red")
    }
  }

  def sumAvoidingRed(node: Any) : Int = {
    node match {
      case JInt(n) => n.toInt
      case JObject(fields) => if (fields.foldLeft(false)((a, n) => a || hasRed(n.value))) 0 else fields.map(n => sumAvoidingRed(n)).sum
      case JArray(fields) => fields.map(n => sumAvoidingRed(n)).sum
      case JField(_, value) => sumAvoidingRed(value)
      case JString(a) => 0
    }
  }

  override val part1Answer: Int = jsons.foldLeft(0)((acc, json) => acc + sum(json))
  override val part2Answer: Int = jsons.foldLeft(0)((acc, json) => acc + sumAvoidingRed(json))
}
