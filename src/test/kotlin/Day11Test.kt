import aoc.Day11
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `day11 part1 provided example`() {
        val example = """
L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
        """.trimIndent()

        Day11.simulation(example.split('\n'), 4) shouldBe 37
    }

    @Test
    fun `day11 part1 my input`() {
        Day11.simulation(readLines("Day11"), 4) shouldBe 2310
    }

    @Test
    fun `day11 part2 provided example`() {
        val example = """
.......#.
...#.....
.#.......
.........
..#L....#
....#....
.........
#........
...#.....
        """.trimIndent()

        Day11.Grid(example.split('\n'), 4).visibleNeighbours(4, 3) shouldBe 8
    }

    @Test
    fun `day11 part2 provided example2`() {
        val example = """
.............
.L.L.#.#.#.#.
.............
        """.trimIndent()

        Day11.Grid(example.split('\n'), 4).visibleNeighbours(1, 1) shouldBe 0
    }

    @Test
    fun `day11 part2 provided example3`() {
        val example = """
.##.##.
#.#.#.#
##...##
...L...
##...##
#.#.#.#
.##.##.
        """.trimIndent()

        Day11.Grid(example.split('\n'), 4).visibleNeighbours(3, 3) shouldBe 0
    }

    @Test
    fun `day11 part2 my input`() {
        Day11.simulation(readLines("Day11"), 5, false) shouldBe 2310
    }

}