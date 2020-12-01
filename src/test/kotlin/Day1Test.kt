import aoc.Day1
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `day1 part1`() {
        Assertions.assertEquals(
            444019,
            Day1.solution1(readLines("Day1"))
        )
    }

    @Test
    fun `day1 part2`() {
        Assertions.assertEquals(
            29212176,
            Day1.solution2(readLines("Day1"))
        )
    }
}