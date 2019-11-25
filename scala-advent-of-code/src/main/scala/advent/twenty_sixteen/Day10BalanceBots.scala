package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities
import scala.annotation.tailrec


case class BotCompare(bot: String, low: Int, high: Int)

case class World(values: Map[String, List[Int]], compares: List[BotCompare]) {
  def canGiveOut(source: String): Boolean = {
    values.contains(source) && values(source).size == 2
  }

  def canTakeIn(dest: String): Boolean = {
    !values.contains(dest) || (values.contains(dest) && values(dest).size < 2)
  }
}

abstract class BotCommand {
  def isValid(world: World): Boolean = ???

  def execute(world: World): World = ???
}

case class ValueBotCommand(value: Int, target: String) extends BotCommand {
  override def isValid(world: World): Boolean = {
    world.canTakeIn(target)
  }

  override def execute(world: World): World = {
    if (world.values.contains(target))
      World(world.values + (target -> (value :: world.values(target))), world.compares)
    else
      World(world.values + (target -> (value :: Nil)), world.compares)
  }
}

case class GiveBotCommand(sourceBot: String, lowTarget: String, highTarget: String) extends BotCommand {
  override def isValid(world: World): Boolean = {
    world.canGiveOut(sourceBot) && world.canTakeIn(lowTarget) && world.canTakeIn(highTarget)
  }

  override def execute(world: World): World = {
    val vals = world.values(sourceBot)
    val lowValue = vals.min
    val highValue = vals.max

    val lc = ValueBotCommand(lowValue, lowTarget)
    val hc = ValueBotCommand(highValue, highTarget)

    hc.execute(lc.execute(World(world.values + (sourceBot -> List()), BotCompare(sourceBot, lowValue, highValue) :: world.compares)))
  }
}

class Day10BalanceBots(filename: String, search: (Int, Int)) extends DailyProblem[Int, Int] {
  private val valuePattern = "value ([0-9]+) goes to bot ([0-9]+)".r
  private val givePattern = "bot ([0-9]+) gives low to (bot|output) ([0-9]+) and high to (bot|output) ([0-9]+)".r

  def parser(line: String): BotCommand = {
    line match {
      case valuePattern(value, bot) => ValueBotCommand(value.toInt, "bot-" + bot)
      case givePattern(bot, lowType, lowTarget, highType, highTarget) => GiveBotCommand("bot-" + bot, lowType + "-" + lowTarget, highType + "-" + highTarget)
      case _ => throw new IllegalArgumentException("Unable to parse '" + line + "'")
    }
  }

  private val commands = FileUtilities.readFile(filename, parser)

  @tailrec
  private def process(pending: List[BotCommand], ignored: List[BotCommand], world: World): World = {
    if (pending.isEmpty && ignored.isEmpty) {
      world
    } else {
      if (pending.isEmpty) {
        process(ignored, pending, world)
      } else {
        if (pending.head.isValid(world)) {
          process(pending.tail, ignored, pending.head.execute(world))
        } else {
          process(pending.tail, pending.head :: ignored, world)
        }
      }
    }
  }

  private val world = process(commands, List(), World(Map(), List()))

  override val part1Answer: Int = world.compares.filter(entry => entry.low == search._1 && entry.high == search._2).head.bot.substring(4).toInt
  override val part2Answer: Int = world.values("output-0")(0) * world.values("output-1")(0) * world.values("output-2")(0)
}

