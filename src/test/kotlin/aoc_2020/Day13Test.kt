package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day13Test {


    @Test
    fun `day13 part1 bus examples`() {
        Bus(7).nearestAfter(939) shouldBe 945
        Bus(13).nearestAfter(939) shouldBe 949
        Bus(59).nearestAfter(939) shouldBe 944
        Bus(31).nearestAfter(939) shouldBe 961
        Bus(19).nearestAfter(939) shouldBe 950
    }

    @Test
    fun `day13part1 provided example`() {
        val example = """
939
7,13,x,x,59,x,31,19
        """.trimIndent()

        val (departureTime, timeTable) = example.split('\n')
        Day13.solution1(departureTime, timeTable) shouldBe 295
    }

    @Test
    fun `day13 part1 my input`() {
        val (departureTime, timeTable) = readLines("Day13")
        Day13.solution1(departureTime, timeTable) shouldBe 4782
    }

    @Test
    fun `day13part2 provided example`() {
        val example = """
939
7,13,x,x,59,x,31,19
        """.trimIndent()

        val (_, timeTable) = example.split('\n')
        Day13.solution2(timeTable) shouldBe 1068781
    }

    @Test
    fun `day13part2 other examples`() {
        Day13.solution2("17,x,13,19") shouldBe 3417
        Day13.solution2("67,7,59,61") shouldBe 754018
        Day13.solution2("67,x,7,59,61") shouldBe 779210
        Day13.solution2("67,7,x,59,61") shouldBe 1261476
        Day13.solution2("1789,37,47,1889") shouldBe 1202161486
    }

    @Test
    fun `day13 part2 my input`() {
        val (_, timeTable) = readLines("Day13")
        Day13.solution2(timeTable) shouldBe 4782
    }
}