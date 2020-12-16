import aoc.Day15
import aoc.Day16
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day15Test {



    @Test
    fun `day15 part1 provided example`() {
        Day15.solution1("0,3,6", 10) shouldBe 0
    }

    @Test
    fun `day15 part1 another example`() {
        Day15.solution1("1,3,2", 2020) shouldBe 1
        Day15.solution1("2,1,3", 2020) shouldBe 10
        Day15.solution1("1,2,3", 2020) shouldBe 27
        Day15.solution1("2,3,1", 2020) shouldBe 78
        Day15.solution1("3,2,1", 2020) shouldBe 438
        Day15.solution1("3,1,2", 2020) shouldBe 1836
    }

    @Test
    fun `day15 part1 my input`() {
        Day15.solution1("20,9,11,0,1,2", 2020) shouldBe 1111
    }

    @Test
    fun `day15 part2 my input`() {
        Day15.solution1("20,9,11,0,1,2", 30000000) shouldBe 48568
    }

}