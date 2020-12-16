package aoc

import java.lang.IllegalArgumentException
import kotlin.math.abs

sealed class Direction {

}

sealed class GeographicDirection : Direction()

object N : GeographicDirection()
object S : GeographicDirection()

object E : GeographicDirection()
object W : GeographicDirection()

object L : Direction()
object R : Direction()

object F : Direction()

object Day12 {

    private val lineRegex = """([A-Z])(\d+)""".toRegex()
    private val rightOrder: List<String> = listOf("E", "S", "W", "N")
    private val leftOrder: List<String> = listOf("E", "N", "W", "S")


    fun manhattanDistance(split: List<String>): Int {
        val moves: List<Pair<String, Int>> = split.map {
            val (direction, step) = lineRegex.find(it)!!.destructured
            direction to step.toInt()
        }

        return moves.fold(Ship(0, 0, "E")) { ship, (direction, step) ->
//            println(ship)
            ship.move(direction, step)
        }.let {
            abs(it.x) + abs(it.y)
        }
    }


    data class Ship(val x: Int, val y: Int, val facing: String) {

        fun move(direction: String, step: Int): Ship {
            return when (direction) {
                "N" -> Ship(x, y + step, facing)
                "S" -> Ship(x, y - step, facing)
                "W" -> Ship(x - step, y, facing)
                "E" -> Ship(x + step, y, facing)

                "F" -> move(facing, step)

                "R" -> Ship(x, y, newFacing(step, rightOrder))
                "L" -> Ship(x, y, newFacing(step, leftOrder))

                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
        }

        private fun newFacing(step: Int, order: List<String>): String {
            val move = step / 90
            return order[(order.indexOf(facing) + move) % 4]
        }

        override fun toString(): String {
            val yS =
                if (y > 0) "N"
                else "S"
            val xS =
                if (x > 0) "E"
                else "W"
            return "($x[$xS], $y[$yS]) facing: $facing"
        }
    }
}