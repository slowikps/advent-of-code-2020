package aoc_2020


object Day15 {

    fun solution1(puzzleInput: String, numberSpoken: Int): Int {
        val numberToLastOccurrence: MutableMap<Int, Int> =
            puzzleInput.split(",")
                .dropLast(1)
                .withIndex()
                .groupBy { it.value.toInt() }
                .mapValues { it.value.first().index + 1 }
                .toMutableMap()

        tailrec fun internal(idx: Int, last: Int, first: Boolean): Int {
            val next = when {
                numberToLastOccurrence.containsKey(last) -> {
                    val res = idx - 1 - numberToLastOccurrence.getValue(last)
                    numberToLastOccurrence[last] = idx - 1
                    res
                }
                first -> last
                else -> {
                    numberToLastOccurrence[last] = idx - 1
                    0
                }
            }
            return if (idx == numberSpoken) next
            else internal(idx + 1, next, false)
        }
        return internal(numberToLastOccurrence.size + 1, puzzleInput.split(",").last().toInt(), true)
    }


}

