package aoc_2020

import java.math.BigInteger

object Day14 {

    fun applyMask(number: String, mask: String): String {
        return Mask(mask).apply(number)
    }

    //mask = 00X1011010111X01X1X010X01X1111X11100
    //mem[13879] = 56974
    private val maskRegex = """mask = (.+)""".toRegex()
    private val memUpdateRegex = """mem\[(\d+)] = (\d+)""".toRegex()

    fun solution1(inputData: List<String>): Long {
        val memory = mutableMapOf<String, String>()
        fun internal(mask: Mask, idx: Int): Unit {
            if (idx == inputData.size) return

            val next = inputData[idx]
            if (maskRegex.matches(next)) {
                internal(Mask(maskRegex.find(next)!!.destructured.component1()), idx + 1)
            } else {
                val (address, value) = memUpdateRegex.find(next)!!.destructured
                memory[address] = mask.apply(value)
                internal(mask, idx + 1)
            }
        }
        internal(Mask(""), 0)
        return memory.values.map {
            BigInteger(it, 2).toLong()
        }.sum()
    }


    class Mask(maskStr: String) {
        private val mask: Map<Int, Char> = maskStr.mapIndexedNotNull { index, c ->
            if (c == '0' || c == '1') {
                index to c
            } else null
        }.toMap()


        fun apply(number: String): String {
            return StringBuilder(number.toLong().toString(2).padStart(36,'0')).also {
                mask.forEach { (idx: Int, char: Char) ->
                    it.setCharAt(idx, char)
                }
            }.toString()
        }
    }
}