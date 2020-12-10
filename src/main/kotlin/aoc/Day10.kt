package aoc

object Day10 {

    private const val chargingOutlet = 0

    fun joltsDifferences(sockets: List<Int>): Map<Int, Int> {
        val sorted = (sockets).sorted()

        return sorted.fold(chargingOutlet to mutableMapOf(3 to 1)) { (prev, occ), next ->
            occ.compute(next - prev) { _, v ->
                if (v == null) 1
                else v + 1
            }
            next to occ
        }.second
    }

    fun totalNumberOfArrangements(raw: List<Int>): Long {
        val sorted = (raw + 0 + (raw.maxOrNull()!! + 3)).sorted()
        val memory = mutableMapOf<Int, Long>()

        fun canGoBackBy(idx: Int, step: Int) = idx - step > -1 && sorted[idx] - sorted[idx - step] < 4

        fun internal(idx: Int): Long {
            return when {
                idx == 0 -> 1
                memory.containsKey(idx) -> memory[idx]!!
                else -> {
                    (1..3).mapNotNull { step ->
                        if (canGoBackBy(idx, step)) {
                            internal(idx - step)
                        } else null
                    }.sum().also {
                        memory[idx] = it
                    }
                }
            }
        }
        return internal(sorted.size - 1)
    }
}