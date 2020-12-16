import aoc.Day16
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {



    @Test
    fun `day16part1 provided example`() {
        val example = """
class: 1-3 or 5-7
row: 6-11 or 33-44
seat: 13-40 or 45-50

your ticket:
7,1,14

nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12
        """.trimIndent()

        Day16.solution1(example.split('\n')) shouldBe 71
    }

    @Test
    fun `day16 part1 my input`() {
        Day16.solution1(readLines("Day16")) shouldBe 20060
    }

    @Test
    fun `day16part2 provided example`() {
        val example = """
class: 0-1 or 4-19
row: 0-5 or 8-19
seat: 0-13 or 16-19

your ticket:
11,12,13

nearby tickets:
3,9,18
15,1,5
5,14,9
        """.trimIndent()

        Day16.solution2(example.split('\n')) shouldBe 71
    }

    @Test
    fun `day16 part2 my input`() {
        Day16.solution2(readLines("Day16")) shouldBe 20060
    }
}