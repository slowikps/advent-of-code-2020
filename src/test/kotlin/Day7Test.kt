import aoc.Day5
import aoc.Day6
import aoc.Day7
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun `day7 part1 provided example`() {
        val example = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        Day7.canContain("shiny gold", example.split('\n')) shouldBe 4
    }


    @Test
    fun `day7 part1 my input`() {
        Day7.canContain("shiny gold", readLines("Day7")) shouldBe 185
    }

    @Test
    fun `day7 part2 provided example`() {
        val example = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()
        Day7.haveToContain("shiny gold", example.split('\n')) shouldBe 126
    }

    @Test
    fun `day7 part2 my input`() {
        Day7.haveToContain("shiny gold", readLines("Day7")) shouldBe 185
    }
}