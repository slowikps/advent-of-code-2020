package aoc

import java.lang.IllegalStateException


object Day13 {

    fun solution1(departureTimeString: String, timeTableString: String): Int {
        val arrivingTime = departureTimeString.toInt()
        val timeTable = timeTableString.split(",").filter { it != "x" }.map { Bus(it.toInt()) }

        val (bus, departure) = timeTable.map { it to it.nearestAfter(arrivingTime) }
            .minByOrNull { it.second }!!

        return bus.number * (departure - arrivingTime)
    }

    fun solution2(timeTable: String): Long {
        println(timeTable)
        var firstOffset = -9999999
        val idxFromHighestNumber = timeTable.split(",").mapIndexedNotNull { idx, nxt ->
            if (nxt == "x") null
            else nxt.toInt() to idx
        }.sortedByDescending { it.first }.let {
            firstOffset = it.first().second
            it.map { (number, offset) ->
                Bus(number, offset - firstOffset)
            }
        }


        val tail: List<Bus> = idxFromHighestNumber.subList(1, idxFromHighestNumber.size)

        val departuresGenerator: Sequence<Long> = 84025614202989.let { frequency ->
            generateSequence(frequency) {
                it + frequency
            }
        }
        return departuresGenerator.find { nextDeparture ->
            tail.all { it.canDepartureAtWithOffset(nextDeparture) }
        }!! - firstOffset
    }
}


data class Bus(val number: Int, val offset: Int = 0) {

    fun nearestAfter(time: Int): Int {
        return (time / number * number) + number
    }

    fun canDepartureAtWithOffset(nextDeparture: Long): Boolean =  (nextDeparture + offset) % number == 0L
}