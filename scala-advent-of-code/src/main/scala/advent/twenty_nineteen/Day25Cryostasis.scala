package advent.twenty_nineteen

import advent.common.DailyProblem

class Day25Cryostasis(filename: String) extends DailyProblem[Int, Int] {

  private val program = IntComputer.loadProgram(filename)

  val state = IntComputerState.newState(program)

  def execute(commands : List[String]) : String = {
    var result = IntComputer.execute(state)
    println(result.output.map(_.toChar).mkString)
    for (command <- commands) {
      result = IntComputerState.copyState(result, command.map(_.toLong).toList)
      println("CMD> " + command)
      result = IntComputer.execute(result)
      println(result.output.map(_.toChar).mkString)
    }
    result.output.map(_.toChar).mkString
  }

  // Don't pick up
  // - infinite loop
  // - molten lava
  // - photons
  // - giant electromagnet
  // - escape pod

  val sequence =
    """north
      |take weather machine
      |north
      |take klein bottle
      |east
      |take spool of cat6
      |east
      |north
      |east
      |north
      |north
      |take tambourine
      |south
      |south
      |south
      |take shell
      |east
      |south
      |north
      |west
      |north
      |west
      |west
      |north
      |take cake
      |south
      |west
      |east
      |east
      |south
      |south
      |take mug
      |north
      |west
      |south
      |south
      |north
      |north
      |west
      |south
      |south
      |east
      |take antenna
      |west
      |north
      |north
      |east
      |south
      |south
      |drop shell
      |drop klein bottle
      |drop tambourine
      |drop cake
      |east
      |""".stripMargin

  val result = execute(sequence.split("\n").toList.map(line => line + "\n"))

  val password = result.substring(result.indexOf("typing ") + 7).split(" ")(0).toInt

  override val part1Answer: Int = password
  override val part2Answer: Int = 0
}
