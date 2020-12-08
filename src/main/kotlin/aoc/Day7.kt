package aoc

object Day7 {
    //light red bags contain 1 bright white bag, 2 muted yellow bags.
    private val holdsRegex = """[ ]?(\d+) (.+) bag[s]?[\\.]?""".toRegex()

    fun canContain(color: String, bagDescription: List<String>): Int {
        val bagToContainer: Map<String, List<String>> = bagToContainer(processDescriptions(bagDescription))
        val visited: MutableSet<String> = mutableSetOf()
        fun internal(color: String): Int {
            visited.add(color)

            val containers = bagToContainer[color] ?: emptyList()

            return containers.map {
                if (visited.contains(it)) 0
                else 1 + internal(it)
            }.sum()
        }
        return internal(color)
    }

    fun haveToContain(color: String, bagDescription: List<String>): Int {
        val bagCapacity: Map<String, List<Capacity>> =
            processDescriptions(bagDescription).groupBy { it.containerColor }.mapValues { it.value }

        fun internal(color: String): Int {
            return bagCapacity[color]!!.map {
                it.capacity *
                        if (bagCapacity.containsKey(it.bagColor)) {
                            //this bag + all inside
                            1 + internal(it.bagColor)
                        } else 1 //no other bags
            }.sum()
        }
        return internal(color)
    }

    private fun processDescriptions(lines: List<String>): List<Capacity> {
        return lines.flatMap { bagDescription ->
            val (bag, capacityString) = bagDescription.split(" bags contain")
            capacityString.split(",").mapNotNull {
                if (it == " no other bags.") {
                    null
                } else {
                    val (capacity, color) = holdsRegex.find(it)!!.destructured
                    Capacity(bag, color, capacity.toInt())
                }
            }
        }
    }

    private fun bagToContainer(capacities: List<Capacity>): Map<String, List<String>> {
        return capacities.groupBy { it.bagColor }.mapValues { it.value.map { it.containerColor } }
    }

    data class Capacity(val containerColor: String, val bagColor: String, val capacity: Int)
}
