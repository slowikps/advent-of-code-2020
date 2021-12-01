package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day14Test {


    @Test
    fun `day14 part1 mask test`() {
        Day14.applyMask(
            "11",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        ) shouldBe "000000000000000000000000000001001001"

        Day14.applyMask(
            number = "101",
            mask   ="XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        ) shouldBe "000000000000000000000000000001100101"

        Day14.applyMask(
            "0",
            "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        ) shouldBe "000000000000000000000000000001000000"
    }

    @Test
    fun `day14 part1 provided example`() {
        val example = """
mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
mem[8] = 11
mem[7] = 101
mem[8] = 0
        """.trimIndent()

        Day14.solution1(example.split("\n")) shouldBe 165
    }

    @Test
    fun `day14part1 my input`() {
        Day14.solution1(readLines("Day14")) shouldBe 5902420735773L
    }

    @Test
    fun `day14 part2 mask test`() {
        Day14_2.applyMask(
            "42",
             "000000000000000000000000000000X1001X"
        ) shouldBe "000000000000000000000000000000X1101X"
        Day14_2.applyMask(
            "26",
            "00000000000000000000000000000000X0XX"
        ) shouldBe "00000000000000000000000000000001X0XX"
    }

    @Test
    fun `day14 part2 provided example`() {
        val example = """
mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1
        """.trimIndent()

        Day14_2.solution1(example.split("\n")) shouldBe 208
    }

    @Test
    fun `day14 part2 my input`() {
        Day14_2.solution1(readLines("Day14")) shouldBe 3801988250775L
    }
}