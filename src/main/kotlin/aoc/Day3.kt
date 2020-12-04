package aoc

object Day3 {

    fun fixTraversing(lines: List<String>): Int = traversing(lines, 3, 1)

    fun traversing(lines: List<String>, right: Int, down: Int): Int {
        val length = lines.first().length
        fun internal(col: Int, row: Int): Int =
            when {
                row >= lines.size -> 0
                lines[row][col % length] == '#' -> 1 + internal(col + right, row + down)
                else -> internal(col + right, row + down)
            }
        return internal(0, 0)
    }
}
