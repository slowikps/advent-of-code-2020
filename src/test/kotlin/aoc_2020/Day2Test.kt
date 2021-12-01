package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day2Test {

    @Test
    fun `day1 part1 process line`() {
        Day2.parseLine("1-3 a: abcde") shouldBe ParsedLine(
            letter = 'a',
            minOcc = 1,
            maxOcc = 3,
            password = "abcde"
        )
    }

    @Test
    fun `day1 part1 provided examples`() {
        Day2.isLineValid("1-3 a: abcde") shouldBe true
        Day2.isLineValid("1-3 b: cdefg") shouldBe false
        Day2.isLineValid("2-9 c: ccccccccc") shouldBe true
    }

    @Test
    fun `day1 part1 provided input`() {
        readLines("Day2").filter { Day2.isLineValid(it) }.size shouldBe 538
    }

    @Test
    fun `day1 part2 provided examples`() {
        Day2.isLineValid2("1-3 a: abcde") shouldBe true
        Day2.isLineValid2("1-3 b: cdefg") shouldBe false
        Day2.isLineValid2("2-9 c: ccccccccc") shouldBe false
    }

    @Test
    fun `day1 part2 provided input`() {
        readLines("Day2").filter { Day2.isLineValid2(it) }.size shouldBe 489
    }

}