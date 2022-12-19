package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day19NotEnoughMinerals(filename: String) extends DailyProblem[Int, Int] {

  private val entry = "Blueprint ([0-9]+): Each ore robot costs ([0-9]+) ore\\. Each clay robot costs ([0-9]+) ore\\. Each obsidian robot costs ([0-9]+) ore and ([0-9]+) clay\\. Each geode robot costs ([0-9]+) ore and ([0-9]+) obsidian\\.".r

  case class Blueprint(id: Int, oreCostOre: Int, clayCostOre: Int, obsidianCostOre: Int, obsidianCostClay: Int, geodeCostOre: Int, geodeCostObsidian: Int, maxOreCost : Int, maxClayCost : Int, maxObsidianCost : Int)

  private val input = FileUtilities.readFile(filename).map {
    case entry(id, oreCostOre, clayCostOre, obsidianCostOre, obsidianCostClay, geodeCostOre, geodeCostObsidian) =>
      Blueprint(id.toInt, oreCostOre.toInt, clayCostOre.toInt, obsidianCostOre.toInt, obsidianCostClay.toInt, geodeCostOre.toInt, geodeCostObsidian.toInt,
        List(oreCostOre.toInt, clayCostOre.toInt, obsidianCostOre.toInt, geodeCostOre.toInt).max,
        obsidianCostClay.toInt,
        geodeCostObsidian.toInt
      )
  }

  case class State(minute: Int, stockPileOre: Int, stockPileClay: Int, stockpileObsidian: Int, stockpileGeode: Int, robotOre: Int, robotClay: Int, robotObsidian: Int, robotGeode: Int)

  @tailrec
  private def run(maxMin : Int, blueprint: Blueprint, states: Set[State], best: Map[Int, Int]): Int = {
    if (states.isEmpty) {
      println("Blueprint " + blueprint.id + " " + best)
      best.getOrElse(maxMin, 0)
    } else {
      val state = states.head
      if (best.getOrElse(state.minute, 0) > state.stockpileGeode) {
        run(maxMin, blueprint, states.tail, best)
      } else if (state.minute > maxMin) {
        run(maxMin, blueprint, states.tail, best)
      } else {
        val newBest = if (state.stockpileGeode > 0) best + (state.minute -> state.stockpileGeode) else best

        val numBuildRobotOre = if (state.robotOre <= blueprint.maxOreCost && state.stockPileOre >= blueprint.oreCostOre) 1 else 0
        val numBuildRobotClay = if (state.robotClay <= blueprint.maxClayCost && state.stockPileOre >= blueprint.clayCostOre ) 1 else 0
        val numBuildRobotObsidian = if (state.robotObsidian <= blueprint.maxObsidianCost && state.stockPileOre >= blueprint.obsidianCostOre && state.stockPileClay >= blueprint.obsidianCostClay) 1 else 0
        val numBuildRobotGeode = if (state.stockPileOre >= blueprint.geodeCostOre && state.stockpileObsidian >= blueprint.geodeCostObsidian) 1 else 0

        val newStockPileOre = state.stockPileOre + state.robotOre
        val newStockPileClay = state.stockPileClay + state.robotClay
        val newStockPileObsidian = state.stockpileObsidian + state.robotObsidian
        val newStockPileGeode = state.stockpileGeode + state.robotGeode

        val newStates = Set(State(state.minute + 1, newStockPileOre, newStockPileClay, newStockPileObsidian, newStockPileGeode, state.robotOre, state.robotClay, state.robotObsidian, state.robotGeode))

        val buildOre = if (numBuildRobotOre == 0)
          newStates
        else
          newStates + State(state.minute + 1, newStockPileOre - numBuildRobotOre * blueprint.oreCostOre, newStockPileClay, newStockPileObsidian, newStockPileGeode, state.robotOre + numBuildRobotOre, state.robotClay, state.robotObsidian, state.robotGeode)

        val buildClay = if (numBuildRobotClay == 0)
          buildOre
        else
          buildOre + State(state.minute + 1, newStockPileOre - numBuildRobotClay * blueprint.clayCostOre, newStockPileClay, newStockPileObsidian, newStockPileGeode, state.robotOre, state.robotClay + numBuildRobotClay, state.robotObsidian, state.robotGeode)

        val buildObsidian = if (numBuildRobotObsidian == 0)
          buildClay
        else
          buildClay + State(state.minute + 1, newStockPileOre - numBuildRobotObsidian * blueprint.obsidianCostOre, newStockPileClay - numBuildRobotObsidian * blueprint.obsidianCostClay, newStockPileObsidian, newStockPileGeode, state.robotOre, state.robotClay, state.robotObsidian + numBuildRobotObsidian, state.robotGeode)

        val buildGeode = if (numBuildRobotGeode == 0)
          buildObsidian
        else
          buildObsidian + State(state.minute + 1, newStockPileOre - numBuildRobotGeode * blueprint.geodeCostOre, newStockPileClay, newStockPileObsidian - numBuildRobotGeode * blueprint.geodeCostObsidian, newStockPileGeode, state.robotOre, state.robotClay, state.robotObsidian, state.robotGeode + numBuildRobotGeode)

        run(maxMin, blueprint, states.tail ++ buildGeode, newBest)
      }
    }
  }

  private val StartingState: State = State(minute = 0, stockPileOre = 0, stockPileClay = 0, stockpileGeode = 0, stockpileObsidian = 0, robotOre = 1, robotClay = 0, robotObsidian = 0, robotGeode = 0)

  override val part1Answer: Int = input.map(bp => bp.id * run(24, bp, Set(StartingState), Map())).sum
  override val part2Answer: Int = input.take(3).map(bp => run(32, bp, Set(StartingState), Map())).product
}
