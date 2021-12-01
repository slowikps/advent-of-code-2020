package aoc_2021

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import readLines

class Day1Test {

    @Test
    fun `day1 part1 - provided example`() {
        val input =
            """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
            """.trimIndent()

        Day1.solution1(input.split('\n').map { it.toLong() }) shouldBe 7
    }

    @Test
    fun `day1 part1 - my input`() {
        Day1.solution1(readLines("2021/Day1").map { it.toLong() }) shouldBe 1226
    }

    @Test
    fun `day1 part2 - provided example`() {
        val input =
            """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
            """.trimIndent()

        Day1.solution2(input.split('\n').map { it.toLong() }) shouldBe 5
    }

    @Test
    fun `day1 part2 - my input`() {
        Day1.solution2(readLines("2021/Day1").map { it.toLong() }) shouldBe 1252
    }
}