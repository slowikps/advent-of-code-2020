package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines
import java.util.*

class Day18Test {


    @Test
    fun `day18 part1 provided example`() {
        Day18.solution1(asStack("1 + 2 * 3 + 4 * 5 + 6")) shouldBe 71
    }

    @Test
    fun `day18 part1 other example`() {
        Day18.solution1(asStack("1 + (2 * 3) + (4 * (5 + 6))")) shouldBe 51
    }

    @Test
    fun `day18 part1 other examples`() {
        Day18.solution1(asStack("2 * 3 + (4 * 5)")) shouldBe 26
        Day18.solution1(asStack("5 + (8 * 3 + 9 + 3 * 4 * 3)")) shouldBe 437
        Day18.solution1(asStack("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")) shouldBe 12240
        Day18.solution1(asStack("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")) shouldBe 13632
    }

    @Test
    fun `day18 part1 my input`() {
        readLines("Day18").map {
            asStack(it)
        }.map {
            Day18.solution1(it)
        }.sum() shouldBe 98621258158412
    }

    @Test
    fun `day18 part2 provided example`() {
        Day18.solution3("1 + 2 * 3 + 4 * 5 + 6".map { "$it" }.filter { it != " " }) shouldBe 231
    }

    @Test
    fun `day18 part2 provided extended`() {
        Day18.solution2(asStack("1 + 2 * 3 + 4 * 5 + 6")) shouldBe 231
    }

    @Test
    fun `day18 part2 provided examples`() {
        Day18.solution2(asStack("1 + (2 * 3) + (4 * (5 + 6))")) shouldBe 51
        Day18.solution2(asStack("2 * 3 + (4 * 5)")) shouldBe 46
        Day18.solution2(asStack("5 + (8 * 3 + 9 + 3 * 4 * 3)")) shouldBe 1445
        Day18.solution2(asStack("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")) shouldBe 669060
        Day18.solution2(asStack("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")) shouldBe 23340
    }

    @Test
    fun `day18 part2 my input`() {
        readLines("Day18").map {
            asStack(it)
        }.map {
            Day18.solution2(it)
        }.sum() shouldBe 241216538527890L
    }

    private fun asStack(input: String) = input.map { "$it" }.filter { it != " " }
        .foldRight(Stack<String>()) { next, acc ->
            acc.push(next)
            acc
        }

}