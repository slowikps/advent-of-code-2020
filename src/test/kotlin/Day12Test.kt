import aoc.Day12
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `day12 part1 provided example`() {
        val example = """
F10
N3
F7
R90
F11
        """.trimIndent()

        Day12.manhattanDistance(example.split('\n')) shouldBe 25
    }

    @Test
    fun `day12 part1 Left test from E`() {
        val eastTest = Day12.Ship(0, 0, "E")

        eastTest.move("L", 90) shouldBe Day12.Ship(0, 0, "N")
        eastTest.move("L", 180) shouldBe Day12.Ship(0, 0, "W")
        eastTest.move("L", 270) shouldBe Day12.Ship(0, 0, "S")
    }

    @Test
    fun `day12 part1 Left test from N`() {
        val eastTest = Day12.Ship(0, 0, "N")

        eastTest.move("L", 90) shouldBe Day12.Ship(0, 0, "W")
        eastTest.move("L", 180) shouldBe Day12.Ship(0, 0, "S")
        eastTest.move("L", 270) shouldBe Day12.Ship(0, 0, "E")
    }

    @Test
    fun `day12 part1 Right test from W`() {
        val eastTest = Day12.Ship(0, 0, "W")

        eastTest.move("R", 90) shouldBe Day12.Ship(0, 0, "N")
        eastTest.move("R", 180) shouldBe Day12.Ship(0, 0, "E")
        eastTest.move("R", 270) shouldBe Day12.Ship(0, 0, "S")
    }

    @Test
    fun `day12 part1 Right test from S`() {
        val eastTest = Day12.Ship(0, 0, "S")

        eastTest.move("R", 90) shouldBe Day12.Ship(0, 0, "W")
        eastTest.move("R", 180) shouldBe Day12.Ship(0, 0, "N")
        eastTest.move("R", 270) shouldBe Day12.Ship(0, 0, "E")
    }

    @Test
    fun `day12 part1 my input`() {
        Day12.manhattanDistance(readLines("Day12")) shouldBe 2310
    }


}