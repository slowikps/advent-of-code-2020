package aoc

object Day5 {


    fun seatId(s: String): Int {
        val (row, column) = decodeSeatNumber(s)
        return row * 8 + column
    }

    fun decodeSeatNumber(encoded: String): Pair<Int, Int> {
        val (rowEncoded, columnEncoded) = encoded.chunked(7)
        return binarySearch(0, 127, rowEncoded.iterator()) to
                binarySearch(0, 7, columnEncoded.iterator())
    }

    private tailrec fun binarySearch(min: Int, max: Int, steps: Iterator<Char>): Int {
        return if (steps.hasNext()) {
            val middle = (max - min) / 2 + min
            if (steps.next() in setOf('F', 'L')) binarySearch(min, middle, steps)
            else binarySearch(middle + 1, max, steps)
        } else min
    }

    fun findMissingSitId(sorted: List<Int>): Int {
        tailrec fun internal(idx: Int): Int {
            return if (sorted[idx] - sorted[idx - 1] == 2) sorted[idx] - 1
            else internal(idx + 1)
        }
        return internal(1)
    }
}
