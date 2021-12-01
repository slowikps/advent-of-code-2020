package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day6Test {

    @Test
    fun `day6 part1 provided example`() {
        val example = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        Day6.countOfYesAnswers(example.split('\n')) shouldBe 11
    }


    @Test
    fun `day6 part1 my input`() {
        Day6.countOfYesAnswers(readLines("Day6")) shouldBe 7120
    }

    @Test
    fun `day6 part2 provided example`() {
        val example = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

        Day6.allWithYesAnswers(example.split('\n')) shouldBe 6
    }

    @Test
    fun `day6 part2 my input`() {
        Day6.allWithYesAnswers(readLines("Day6")) shouldBe 3570
    }
}