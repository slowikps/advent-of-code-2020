package aoc_2020

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readLines

class Day5Test {

    @Test
    fun `day5 part1 provided example - decode`() {
        Day5.decodeSeatNumber("FBFBBFFRLR") shouldBe (44 to 5)
        Day5.decodeSeatNumber("BFFFBBFRRR") shouldBe (70 to 7)
        Day5.decodeSeatNumber("FFFBBBFRRR") shouldBe (14 to 7)
        Day5.decodeSeatNumber("BBFFBBFRLL") shouldBe (102 to 4)
    }

    @Test
    fun `day5 part1 provided example - seat id`() {
        Day5.seatId("FBFBBFFRLR") shouldBe 357
        Day5.seatId("BFFFBBFRRR") shouldBe 567
        Day5.seatId("FFFBBBFRRR") shouldBe 119
        Day5.seatId("BBFFBBFRLL") shouldBe 820
    }

    @Test
    fun `day5 part1 my input`() {
        readLines("Day5").map { Day5.seatId(it) }
            .maxOrNull() shouldBe 813
    }

    @Test
    fun `day5 part2 my input`() {
        Day5.findMissingSitId(
            readLines("Day5").map { Day5.seatId(it) }
                .sorted()
        ) shouldBe 612
    }


}