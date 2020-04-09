package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day20ParticleSwarmTest  extends FlatSpec with Matchers {
  "2017 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20ParticleSwarm("/twenty_seventeen/Day20-ParticleSwarm-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day20ParticleSwarm("/twenty_seventeen/Day20-ParticleSwarm-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
