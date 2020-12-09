package aoc

object Day9 {


    fun firstInvalid(lines: List<Long>, preamble: Int): Long {
        val previous: LinkedHashSet<Long> = LinkedHashSet(lines.take(preamble))

        lines.drop(preamble).forEach {
            if (sumOfPrevious(it, previous)) {
                previous.remove(previous.first())
                previous += it
            } else {
                return it
            }
        }

        throw IllegalArgumentException("All numbers are valid")
    }

    fun contiguousSet(lines: List<Long>, number: Long): Long {
        val previous = mutableListOf<Long>()
        lines.forEach {
            previous.add(it)
            do {
                val sum = previous.sum()
                if (sum == number && previous.size > 1) {
                    return previous.minOrNull()!! + previous.maxOrNull()!!
                } else if(sum > number) {
                    previous.removeFirst()
                }
            } while (sum > number)

        }

        throw IllegalArgumentException("There are not two numbers that sums up to: $number")
    }

    private fun sumOfPrevious(number: Long, previous: LinkedHashSet<Long>): Boolean =
        previous.any {
            val diff = number - it
            previous.contains(diff) && diff != it
        }


}
