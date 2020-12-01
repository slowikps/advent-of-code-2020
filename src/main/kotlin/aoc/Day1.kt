package aoc

import java.lang.IllegalArgumentException

object Day1 {
    private fun entriesThatSumsUpTo(entryToEntry: Map<Int, Int>, sum: Int = 2020): Pair<Int, Int>? {
        val firstNumber = entryToEntry.keys.find {
            entryToEntry.containsKey(sum - it)
        }

        return firstNumber?.let {
            it to sum - firstNumber
        }
    }

    fun solution1(input: List<String>): Long {
        val entryToEntry: Map<Int, Int> = createMap(input)
        return entriesThatSumsUpTo(entryToEntry)!!.let { it.first.toLong() * it.second.toLong() }
    }

    fun solution2(input: List<String>): Long {
        val entryToEntry: Map<Int, Int> = createMap(input)
        return entryToEntry.keys.fold(null) { acc: Long?, next ->
            if (acc == null) {//Not fully correct - as we use the same number many times. Did the job though
                val result = entriesThatSumsUpTo(entryToEntry, 2020 - next)
                if (result != null) {
                    next.toLong() * result.first * result.second
                } else acc
            } else acc
        }!!
    }

    private fun createMap(input: List<String>): Map<Int, Int> =
        input.groupBy { it.toInt() }.mapValues { (_, v) ->
            if (v.size != 1) throw IllegalArgumentException("Something wrong with you input I think")
            v.first().toInt()
        }
}