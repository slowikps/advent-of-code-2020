package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day8Test {

    @Test
    fun `day8 part1 provided example`() {
        val example = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
        Day8.brokenInstructions(example.split('\n')) shouldBe 5
    }


    @Test
    fun `day8 part1 my input`() {
        Day8.brokenInstructions(readLines("Day8")) shouldBe 1317
    }

    @Test
    fun `day8 part2 provided example`() {
        val example = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
        Day8.instructionsWithFix(example.split('\n')) shouldBe 8
    }

    @Test
    fun `day8 part2 my input`() {
        Day8.instructionsWithFix(readLines("Day8")) shouldBe 1033
    }

}