package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day19Test {


    @Test
    fun `day19 part1 provided example`() {
        val input = """
            0: 4 1 5
            1: 2 3 | 3 2
            2: 4 4 | 5 5
            3: 4 5 | 5 4
            4: "a"
            5: "b"

            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
        """.trimIndent()
        Day19.matches(input.split("\n")) shouldBe 2
    }

    @Test
    fun `day19 part1 other example`() {
        Day19.matches(readLines("Day19")) shouldBe 151
    }

    @Test
    fun `day19 part2 other example`() {
        Day19.matches(readLines("Day19_2")) shouldBe 386
    }
}