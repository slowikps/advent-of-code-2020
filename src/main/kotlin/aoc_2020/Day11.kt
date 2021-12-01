package aoc_2020

import aoc_2020.Day11.Grid.Direction.L
import aoc_2020.Day11.Grid.Direction.R

object Day11 {

    /**
     * (.) floor
     * (L) an empty seat
     * (#) an occupied seat
     */
    fun simulation(split: List<String>, maxNeighbours: Int, partOne: Boolean = true): Any {
        /**
         * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
         * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
         * Otherwise, the seat's state does not change.
         */
        fun internal(c: Grid, next: Grid): Grid {
//            println(c.toString())
//            System.out.flush()
//            TimeUnit.MILLISECONDS.sleep(100)
            return if (c == next) c
            else internal(next, next.nextRound())
        }

        val initial = Grid(split, maxNeighbours, partOne)

        return internal(initial, initial.nextRound()).occupiedSeats()
    }


    data class Grid(private val data: List<String>, private val maxNeighbours: Int, private val partOne: Boolean = true) {
        private val height = data.size
        private val width = data[0].length

        private val neighboursFunction =
            if (partOne) ::neighbours
            else ::visibleNeighbours

        fun get(row: Int, column: Int): Char = data[row][column]

        private fun neighbours(row: Int, column: Int): Int {
            val result = mutableListOf<Char>()
            for (r in 0.coerceAtLeast(row - 1)..(height - 1).coerceAtMost(row + 1)) {
                for (c in 0.coerceAtLeast(column - 1)..(width - 1).coerceAtMost(column + 1)) {
                    if (get(r, c) == '#') {
                        result.add('#')
                    }
                }
            }
            result -= get(row, column) //Remove self
            return result.count { it == '#' }
        }

        fun visibleNeighbours(row: Int, column: Int): Int {
            val p = Point(row, column)

            return listOf(
                p.canSeePerson(L, L), p.canSeePerson(L, null), p.canSeePerson(L, R),
                p.canSeePerson(null, L)/*, p.canSeePerson(null, null)*/, p.canSeePerson(null, R),
                p.canSeePerson(R, L), p.canSeePerson(R, null), p.canSeePerson(R, R)
            ).count { it }
        }

        fun nextRound(): Grid {
            val result: List<String> = (0 until height).map { row ->
                (0 until width).map { col ->
                    when (get(row, col)) {
                        'L' ->
                            if (neighboursFunction(row, col) == 0) '#'
                            else 'L'
                        '#' ->
                            if (neighboursFunction(row, col) >= maxNeighbours) 'L'
                            else '#'
                        else -> '.'
                    }
                }.joinToString("")
            }
            return Grid(result, maxNeighbours, partOne)
        }

        override fun toString(): String {
            return data.joinToString("\n")
        }

        fun occupiedSeats(): Int = data.fold(0) { acc, next ->
            acc + next.count { it == '#' }
        }


        enum class Direction {
            L, R
        }

        inner class Point(private val row: Int, private val col: Int) {
            fun canSeePerson(rowDirection: Direction?, colDirection: Direction?): Boolean {
                val next = next(rowDirection, colDirection)
                return if (next.isValid()) {
                    when {
                        this@Grid.get(next.row, next.col) == '#' -> true
                        this@Grid.get(next.row, next.col) == '.' -> next.canSeePerson(rowDirection, colDirection)
                        else -> false
                    }
                } else false
            }

            private fun next(rowDirection: Direction?, colDirection: Direction?): Point {
                val newRow =
                    when (rowDirection) {
                        L -> row - 1
                        R -> row + 1
                        else -> row
                    }

                val newCol =
                    when (colDirection) {
                        L -> col - 1
                        R -> col + 1
                        else -> col
                    }
                return Point(newRow, newCol)
            }

            private fun isValid(): Boolean = row in 0 until height
                    && col in 0 until width
        }
    }
}