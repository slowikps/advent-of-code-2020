package aoc

import java.lang.IllegalArgumentException

object Day8 {
    //acc +48
    private val lineRegex = """(.{3}) ([-+]\d+)""".toRegex()

    fun brokenInstructions(lines: List<String>): Int {
        return executeInstructions(lines) { it }!!
    }

    fun instructionsWithFix(lines: List<String>): Int {
        fun internal(changeAfter: Int, amended: List<String>): Int {
            val result = executeInstructions(amended) { null }
            return if (result == null) {
                val (newIdx, newAmended) = generateNewInstructions(changeAfter, lines)
                internal(newIdx, newAmended)
            } else result
        }
        return internal(0, lines)
    }

    private fun executeInstructions(lines: List<String>, loopHandler: (Int) -> Int?): Int? {
        val alreadyExecutedInstructions = mutableSetOf<Int>()
        tailrec fun internal(idx: Int, acc: Int): Int? {
            return when (idx) {
                in alreadyExecutedInstructions -> loopHandler(acc)
                lines.size -> acc
                else -> {
                    alreadyExecutedInstructions += idx
                    val (instruction, value) = lineRegex.find(lines[idx])!!.destructured
                    when (instruction) {
                        "acc" -> internal(idx + 1, acc + value.toInt())
                        "jmp" -> internal(idx + value.toInt(), acc)
                        "nop" -> internal(idx + 1, acc)
                        else -> throw IllegalArgumentException("Unknown instruction [idx: $idx, instruction: $instruction, value: $value]")
                    }
                }
            }
        }
        return internal(0, 0)
    }

    private fun generateNewInstructions(changeAfter: Int, lines: List<String>): Pair<Int, List<String>> {
        val result = lines.toMutableList()
        for (i in changeAfter..lines.size) {
            val line = result[i]
            if (line.startsWith("nop")) {
                result[i] = line.replace("nop", "jmp")
                return (i + 1) to result
            } else if (line.startsWith("jmp")) {
                result[i] = line.replace("jmp", "nop")
                return (i + 1) to result
            }
        }
        throw IllegalArgumentException("Something is seriously broken if you get here")
    }
}
