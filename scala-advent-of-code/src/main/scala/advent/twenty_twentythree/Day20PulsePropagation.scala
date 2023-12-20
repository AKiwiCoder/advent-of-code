package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec
import scala.collection.mutable

class Day20PulsePropagation(filename : String, outputName : String, watch : Set[String]) extends DailyProblem[Int, Long] {
  case class Signal(source : String, destination : String, level : Boolean)

  sealed trait Module {
    def name() : String

    def outputs() : List[String]

    def pulse(signal : Signal) : List[Signal]
  }

  case class BroadcastModule(name: String, outputs : List[String]) extends Module {
    override def pulse(signal: Signal): List[Signal] = {
      outputs.map(target => Signal(name, target, signal.level))
    }
  }

  case class OutputModule(name: String) extends Module {
    override def pulse(signal: Signal): List[Signal] = List()
    override def outputs(): List[String] = List()
  }

  case class FlipFlopModule(name: String, outputs : List[String], flipFlops : mutable.Map[String, Boolean]) extends Module {
    override def pulse(signal: Signal): List[Signal] = {
      if (signal.level) {
        // Ignore High Level
        Nil
      } else {
        val currentState = flipFlops.getOrElse(name, false)
        if (currentState) {
          flipFlops(name) = false
          outputs.map(target => Signal(name, target, level = false))
        } else {
          flipFlops(name) = true
          outputs.map(target => Signal(name, target, level = true))
        }
      }
    }
  }

  case class ConjunctionModule(name: String, outputs : List[String], conjunctions : mutable.Map[String, mutable.Map[String, Boolean]]) extends Module {
    override def pulse(signal: Signal): List[Signal] = {
      conjunctions(name)(signal.source) = signal.level

      if (conjunctions(name).values.exists(_ == false)) {
        outputs.map(target => Signal(name, target, level = true))
      } else {
        outputs.map(target => Signal(name, target, level = false))
      }
    }
  }

  private val flipFlops = mutable.Map[String, Boolean]()
  private val conjunctions = mutable.Map[String, mutable.Map[String, Boolean]]()

  def parse(line : String) : List[Module] = {
    val split = line.split(" -> ");
    val outputs = split(1).trim().split(",").map(_.trim).toList

    val others = if (outputs.contains(outputName)) {
      List(OutputModule(outputName))
    } else {
      List()
    }

    if (line.startsWith("broadcaster")) {
      BroadcastModule("broadcaster", outputs) :: others
    } else if (line.startsWith("%")) {
      FlipFlopModule(split(0).drop(1).trim, outputs, flipFlops)  :: others
    } else if (line.startsWith("&")) {
      val name = split(0).drop(1).trim;
      conjunctions(name) = mutable.Map[String,Boolean]()
      ConjunctionModule(name, outputs, conjunctions) :: others
    } else {
      println("Unparsed: " + line)
      throw new IllegalStateException()
    }
  }

  private val moduleMap = FileUtilities.readFile(filename).flatMap(parse).map(e => e.name -> e).toMap

  @tailrec
  private def processSignals(signals : List[Signal], acc : (Int,Int)): (Int,Int) = {
    if (signals.isEmpty) {
      acc
    } else {
      val signal = signals.head
      val module = moduleMap(signal.destination)
      val newSignals = module.pulse(signal)
      processSignals(signals.tail ::: newSignals, if (signal.level) (acc._1, acc._2 + 1) else (acc._1 + 1, acc._2))
    }
  }

  @tailrec
  private def processSignalsPart2(signals : List[Signal], acc : Long, found : Map[String, Long]): Map[String, Long] = {
    if (signals.isEmpty) {
      found
    } else {
      val signal = signals.head

      val newFound = if (watch.contains(signal.destination) && !signal.level) {
        found + (signal.destination ->  acc)
      } else {
        found
      }

      val module = moduleMap(signal.destination)
      val newSignals = module.pulse(signal)
      processSignalsPart2(signals.tail ::: newSignals, acc, newFound)
    }
  }

  private def runPartOne() : Int = {
    val total = (0 until 1000).map(_ => processSignals(List(Signal("button", "broadcaster", level = false)), (0, 0))).foldLeft((0, 0))((acc, p) => (acc._1 + p._1, acc._2 + p._2))
    total._1 * total._2
  }


  @tailrec
  private def runPartTwo(idx : Long, found : Map[String, Long]): Long = {
    val newFound = processSignalsPart2(List(Signal("button", "broadcaster", level = false)), idx, found)
    if (newFound.size == watch.size) {
      println(newFound)
      newFound.values.product
    } else runPartTwo(idx+1, newFound);
  }

  private def resetMemories(): Unit = {
    flipFlops.keys.foreach(k => flipFlops(k) = false)

    moduleMap.values.foreach(module => module.outputs().foreach(output => moduleMap(output) match {
      case ConjunctionModule(name, _, _) => conjunctions(name)(module.name()) = false
      case _ => ""
    }))
  }

  resetMemories()
  private val partOne = runPartOne()
  resetMemories()
  private val partTwo = if (watch.isEmpty) 0 else runPartTwo(1, Map());

  override val part1Answer: Int = partOne
  override val part2Answer: Long = partTwo
}
