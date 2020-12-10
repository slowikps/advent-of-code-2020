import aoc.Day10
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `day10 part1 provided example`() {
        val example = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        Day10.joltsDifferences(example.split('\n').map { it.toInt() }) shouldBe mapOf(
            1 to 7,
            3 to 5
        )
    }

    @Test
    fun `day10 part1 larger example`() {
        val example = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()
        Day10.joltsDifferences(example.split('\n').map { it.toInt() }) shouldBe mapOf(
            1 to 22,
            3 to 10
        )
    }

    @Test
    fun `day10 part1 my input`() {
        val map = Day10.joltsDifferences(readLines("Day10").map { it.toInt() })

        map[1]!! * map[3]!! shouldBe 2240
    }

    @Test
    fun `day10 part2 provided example sm all `() {
        val example = """
            1
            2
            3
            4
        """.trimIndent()

        Day10.totalNumberOfArrangements(example.split('\n').map { it.toInt() }) shouldBe 7
    }

    @Test
    fun `day10 part2 provided example`() {
        val example = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

        Day10.totalNumberOfArrangements(example.split('\n').map { it.toInt() }) shouldBe 8
    }

    @Test
    fun `day10 part2 larger example`() {
        val example = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()
        Day10.totalNumberOfArrangements(example.split('\n').map { it.toInt() }) shouldBe 19208
    }

    @Test
    fun `day10 part2 my input`() {
        Day10.totalNumberOfArrangements(readLines("Day10").map { it.toInt() }) shouldBe 99214346656768
    }
}