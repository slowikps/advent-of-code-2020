package aoc

import java.lang.IllegalArgumentException


object Day16 {

    fun solution1(split: List<String>): Int {
        val (restrictions, nearbyTickets, _) = parseInput(split)

        return nearbyTickets.flatMap {
            invalidValues(it, restrictions)
        }.sum()
    }

    fun solution2(split: List<String>): Long {
        val (restrictions, nearbyTickets: List<List<Int>>, myticket) = parseInput(split)

        val validNearby: List<List<Int>> = nearbyTickets.filter {
            invalidValues(it, restrictions).isEmpty()
        }

        val pivot: List<MutableList<Int>> = validNearby.fold(
            (validNearby.first().indices).toList().map { mutableListOf() }
        ) { acc, next ->
            next.forEachIndexed { index, field ->
                acc[index].add(field)
            }
            acc
        }

        val columnToRestrictionsMet: List<Pair<Int, Set<String>>> = pivot.mapIndexed { idx, column ->
            idx to restrictions.mapNotNull { r ->
                if (column.all { r.isValid(it) }) r.name
                else null
            }.toSet()
        }.sortedBy { it.second.size }

        val used = mutableSetOf<String>()

        return (columnToRestrictionsMet.map { (idx, possible) ->
            possible.minus(used).let {
                if (it.size == 1) {
                    used.add(it.first())
                    idx to it.first()
                } else {
                    throw IllegalArgumentException("Code is not that smart to figure this out")
                }
            }
        }.filter { it.second.startsWith("departure") })
            .fold(1L) { acc, (idx, _) ->
                acc * myticket[idx]
            }
    }

    private fun parseInput(split: List<String>): Triple<List<Restriction>, List<List<Int>>, List<Int>> {
        val (rst, myT, otherTickets) = split.split { it.isBlank() }
        val restrictions = rst.map { Restriction(it) }
        val myTicket: List<Int> = myT.last().split(",").map { it.toInt() }
        val nearbyTickets = otherTickets.drop(1).map { it.split(",").map { it.toInt() } }
        return Triple(restrictions, nearbyTickets, myTicket)
    }


    private fun invalidValues(ticket: List<Int>, restrictions: List<Restriction>): List<Int> {
        return ticket.filter { field ->
            restrictions.none { restriction -> restriction.isValid(field) }
        }
    }

    class Restriction(line: String) {

        private val lineRegex = """(.*): (\d+)-(\d+) or (\d+)-(\d+)""".toRegex()

        val name: String
        private val from1: Int
        private val to1: Int

        private val from2: Int
        private val to2: Int

        init {
            val (name, f1, t1, f2, t2) = lineRegex.find(line)!!.destructured
            from1 = f1.toInt()
            to1 = t1.toInt()

            from2 = f2.toInt()
            to2 = t2.toInt()

            this.name = name
        }

        fun isValid(field: Int): Boolean = field in from1..to1 || field in from2..to2

        override fun toString(): String {
            return "Restriction(name='$name' [$from1-$to1] or [$from2-$to2])"
        }
    }

}

