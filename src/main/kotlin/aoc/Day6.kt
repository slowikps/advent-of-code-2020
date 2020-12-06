package aoc

object Day6 {

    fun countOfYesAnswers(lines: List<String>): Int =
        countInGroup(lines) { first, second -> first.union(second) }


    fun allWithYesAnswers(lines: List<String>): Int =
        countInGroup(lines) { first, second -> first.intersect(second) }

    private fun countInGroup(lines: List<String>, reducer: (Set<Char>, Set<Char>) -> Set<Char>) =
        lines.split { it.isBlank() }.fold(0) { acc, list ->
            acc +
                    list.map { it.toCharArray().toSet() }.reduce(reducer)
                        .size
        }


}
