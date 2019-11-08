package advent.twenty_sixteen

import advent.common.DailyProblem
import kotlin.math.abs

data class Position(val x: Int, val y: Int)

enum class Facing {
    North {
        override fun left(): Facing = West
        override fun right(): Facing = East
        override fun move(pos: Position, steps: Int): Position = Position(pos.x, pos.y + steps)
    },
    South {
        override fun left(): Facing = East
        override fun right(): Facing = West
        override fun move(pos: Position, steps: Int): Position = Position(pos.x, pos.y - steps)
    },
    East {
        override fun left(): Facing = North
        override fun right(): Facing = South
        override fun move(pos: Position, steps: Int): Position = Position(pos.x + steps, pos.y)
    },
    West {
        override fun left(): Facing = South
        override fun right(): Facing = North
        override fun move(pos: Position, steps: Int): Position = Position(pos.x - steps, pos.y)
    };

    abstract fun left(): Facing
    abstract fun right(): Facing

    abstract fun move(pos: Position, steps: Int): Position;
}

data class Location(val facing: Facing, val position: Position)

class Day01NoTimeForATaxicab : DailyProblem<Int, Int> {
    override var part1Answer: Int = -1
    override var part2Answer: Int = -1

    private fun move(location: Location, stepsSoFar: List<Position>, command: String): Pair<Location, List<Position>> {
        val steps = Integer.parseInt(command.substring(1))
        val newFacing = when (command[0]) {
            'L' -> location.facing.left()
            'R' -> location.facing.right()
            else -> location.facing
        }
        var newSteps = stepsSoFar
        var newPosition = location.position
        for (i in 0 until steps) {
            newPosition = newFacing.move(newPosition, 1)
            newSteps += newPosition
        }
        return Pair(Location(newFacing, newPosition), newSteps)
    }

    constructor (filename: String) {
        val input = Day01NoTimeForATaxicab::class.java.getResource(filename).readText().split(",")

        val (endLoc, steps) = input.fold(Pair(Location(Facing.North, Position(0, 0)), listOf<Position>())) { acc, cmd ->
            move(acc.first, acc.second, cmd.trim())
        }

        part1Answer = manhattenDistance(Position(0,0), endLoc.position)
        part2Answer = manhattenDistance(Position(0,0), search(steps[0], 0, steps.drop(1)))
    }

    private fun manhattenDistance(lhs : Position, rhs : Position) : Int {
        return abs(lhs.x - rhs.x) + abs(lhs.y - rhs.y)
    }

    private tailrec fun search(position: Position, index: Int, rest: List<Position>): Position {
        return when {
            rest.contains(position) -> position
            rest.isEmpty() -> Position(0,0)
            else -> search(rest[0], index + 1, rest.drop(1))
        }
    }
}