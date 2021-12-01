package aoc_2021

object Day1 {
    fun solution1(lines: List<Long>): Int {
        fun count(last: Long, iterator: Iterator<Long>, count: Int): Int =
            if (iterator.hasNext()) {
                val next = iterator.next()
                if (next > last) count(next, iterator, count + 1)
                else count(next, iterator, count)
            } else {
                count
            }


        return lines.iterator().let {
            count(it.next(), it, 0)
        }
    }

    fun solution2(lines: List<Long>): Int {
        return solution1(
            lines.mapIndexedNotNull { idx, nxt ->
                if (idx + 3 <= lines.size) {
                    nxt + lines[idx + 1] + lines[idx + 2]
                } else null
            }
        )
    }
}