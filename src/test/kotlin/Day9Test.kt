import aoc.Day9
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun `day9 part1 provided example`() {
        val example = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
        Day9.firstInvalid(example.split('\n').map { it.toLong() }, 5) shouldBe 127
    }

    @Test
    fun `day9 part1 my input`() {
        Day9.firstInvalid(readLines("Day9").map { it.toLong() }, 25) shouldBe 542529149
    }

    @Test
    fun `day9 part2 provided example`() {
        val example = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()
        Day9.contiguousSet(example.split('\n').map { it.toLong() }, 127) shouldBe 62
    }

    @Test
    fun `day9 part2 my input`() {
        Day9.contiguousSet(readLines("Day9").map { it.toLong() }, 542529149) shouldBe 75678618
    }

}