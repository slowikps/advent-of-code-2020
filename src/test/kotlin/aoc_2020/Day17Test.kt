package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day17Test {


    @Test
    fun `day17part1 provided example`() {
        val example = """
.#.
..#
###
        """.trimIndent()

        Day17.solution1(example.split('\n'), 6) shouldBe 112
    }

    @Test
    fun `day17 part1 my input`() {
        Day17.solution1(readLines("Day17"), 6) shouldBe 348
    }

    @Test
    fun `day17 part2 provided example`() {
        val example = """
.#.
..#
###
        """.trimIndent()

        Day17.solution2(example.split('\n'), 6) shouldBe 848
    }

    @Test
    fun `day17 part2 my input`() {
        Day17.solution2(readLines("Day17"), 6) shouldBe 2236
    }

}