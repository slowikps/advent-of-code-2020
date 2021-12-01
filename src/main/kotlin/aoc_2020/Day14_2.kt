package aoc_2020

object Day14_2 {

    fun applyMask(number: String, mask: String): String {
        return Mask(mask).apply(number)
    }

    //mask = 00X1011010111X01X1X010X01X1111X11100
    //mem[13879] = 56974
    private val maskRegex = """mask = (.+)""".toRegex()
    private val memUpdateRegex = """mem\[(\d+)] = (\d+)""".toRegex()

    private data class ValueWithTime(val value: Long, val time: Int)

    fun solution1(inputData: List<String>): Long {
        val memory = linkedMapOf<String, ValueWithTime>()
        fun internal(mask: Mask, idx: Int): Unit {
            if (idx == inputData.size) return

            val next = inputData[idx]
            if (maskRegex.matches(next)) {
                internal(Mask(maskRegex.find(next)!!.destructured.component1()), idx + 1)
            } else {
                val (address, value) = memUpdateRegex.find(next)!!.destructured
                memory[mask.apply(address)] = ValueWithTime(value.toLong(), idx)
                internal(mask, idx + 1)
            }
        }
        internal(Mask(""), 0)

        val visited = mutableSetOf<String>()
        return memory.keys.toList().foldRight(0L) { k: String, acc ->
            val multiplier: Int = multiplier(k).let {
                val new = it.minus(visited)
                visited.addAll(new)
                new.size
            }
            val v: ValueWithTime = memory.getValue(k)
            //            BigInteger(v.value, 2).toLong() * multiplier
            acc + v.value * multiplier
        }
    }

    private fun multiplier(k: String): Set<String> {
        return k.fold(listOf("")) { acc, k: Char ->
            acc.flatMap {
                if (k == 'X') listOf(it + '0', it + '1')
                else listOf(it + k)
            }
        }.toSet()
    }


    /**
     * If the bitmask bit is 0, the corresponding memory address bit is unchanged.
     * If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
     * If the bitmask bit is X, the corresponding memory address bit is floating.
     */
    class Mask(maskStr: String) {
        private val mask: Map<Int, Char> = maskStr.mapIndexedNotNull { index, c ->
            if (c == '1' || c == 'X') {
                index to c
            } else null
        }.toMap()


        fun apply(number: String): String {
            return StringBuilder(number.toLong().toString(2).padStart(36, '0')).also {
                mask.forEach { (idx: Int, char: Char) ->
                    it.setCharAt(idx, char)
                }
            }.toString()
        }
    }
}