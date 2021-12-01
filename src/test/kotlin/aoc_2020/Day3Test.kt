package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day3Test {

    @Test
    fun `day3 part1 provided example`() {
        val input =
            """
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
            """.trimIndent()

        Day3.fixTraversing(input.split('\n')) shouldBe 7
    }

    @Test
    fun `day3 part1 my input`() {
        Day3.fixTraversing(readLines("Day3")) shouldBe 148
    }


    @Test
    fun `day3 part2 provided example`() {
        val input =
            """
                ..##.......
                #...#...#..
                .#....#..#.
                ..#.#...#.#
                .#...##..#.
                ..#.##.....
                .#.#.#....#
                .#........#
                #.##...#...
                #...##....#
                .#..#...#.#
            """.trimIndent()

        Day3.traversing(input.split('\n'), 1, 1) shouldBe 2
        Day3.traversing(input.split('\n'), 3, 1) shouldBe 7
        Day3.traversing(input.split('\n'), 5, 1) shouldBe 3
        Day3.traversing(input.split('\n'), 7, 1) shouldBe 4
        Day3.traversing(input.split('\n'), 1, 2) shouldBe 2
    }

    @Test
    fun `day3 part2 my input`() {
        Day3.traversing(readLines("Day3"), 1, 1) *
                Day3.traversing(readLines("Day3"), 3, 1) *
                Day3.traversing(readLines("Day3"), 5, 1) *
                Day3.traversing(readLines("Day3"), 7, 1) *
                Day3.traversing(readLines("Day3"), 1, 2) shouldBe 727923200
    }

}