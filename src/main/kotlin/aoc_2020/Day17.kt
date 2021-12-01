package aoc_2020

import kotlin.math.max
import kotlin.math.min


object Day17 {


    fun solution1(split: List<String>, cycles: Int): Int {
        return Grid.from(split).also {
            it.printGrid()
            System.out.flush()
        }.let {
            (0 until cycles).fold(it) { acc, idx ->
                acc.nextRound().also { grid ->
                    println("Cycle: ${idx + 1} - active: ${grid.countActive()}")
                    grid.printGrid()
                }
            }.countActive()
        }
    }

    fun solution2(split: List<String>, cycles: Int): Int {
        return Grid4D.from(split).let {
            (0 until cycles).fold(it) { acc, _ ->
                acc.nextRound()
            }.countActive()
        }
    }

    class Grid(private val activeCubes: Set<Triple<Int, Int, Int>>) {
//        If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
//        If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.

        companion object {
            fun from(split: List<String>): Grid {
                val result = mutableSetOf<Triple<Int, Int, Int>>()
                split.forEachIndexed { y, line ->
                    line.forEachIndexed { x, cube ->
                        if (cube == '#') {
                            result.add(Triple(x, y, 0))
                        }
                    }
                }
                return Grid(result)
            }
        }

        fun nextRound(): Grid {
            val toProcess: MutableSet<Triple<Int, Int, Int>> = activeCubes.toMutableSet()
            val alreadyProcessed = mutableSetOf<Triple<Int, Int, Int>>()

            val result = mutableSetOf<Triple<Int, Int, Int>>()

            while (toProcess.isNotEmpty()) {
                val current: Triple<Int, Int, Int> = toProcess.first().also {
                    toProcess.remove(it)
                }
                if (current !in alreadyProcessed) {
                    val (activeN, inactiveN) = neighbours(current.first, current.second, current.third)
                    val currentActive = isActive(current.first, current.second, current.third)
                    if (currentActive) {
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    }

                    if (currentActive && (activeN.size == 2 || activeN.size == 3)) {
                        result.add(current)
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    } else if (!currentActive && activeN.size == 3) {
                        result.add(current)
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    }
                    alreadyProcessed.add(current)
                }
            }
            return Grid(result)
        }

        private fun isActive(x: Int, y: Int, z: Int): Boolean {
            return activeCubes.contains(Triple(x, y, z))
        }

        private fun neighbours(x: Int, y: Int, z: Int): Pair<Set<Triple<Int, Int, Int>>, Set<Triple<Int, Int, Int>>> {
            val active = mutableSetOf<Triple<Int, Int, Int>>()
            val inActive = mutableSetOf<Triple<Int, Int, Int>>()
            for (x1 in x - 1..x + 1) {
                for (y1 in y - 1..y + 1) {
                    for (z1 in z - 1..z + 1) {
                        if (x1 == x && y1 == y && z1 == z) continue
                        else {
                            if (isActive(x1, y1, z1)) active.add(Triple(x1, y1, z1))
                            else inActive.add(Triple(x1, y1, z1))
                        }
                    }
                }
            }
            return active to inActive
        }

        fun countActive(): Int = activeCubes.size

        fun printGrid() {
            val (min, max) = activeCubes.fold(Pair(0, 0)) { (min, max), next ->
                val newMin = if (min > minFrom(next)) minFrom(next) else min
                val newMax = if (max < maxFrom(next)) maxFrom(next) else max
                newMin to newMax
            }
            for (y in min..max) {
                for (x in min..max) {
                    print(if (isActive(x, y, 0)) "#" else ".")
                }
                println()
            }
        }

        private fun minFrom(elem: Triple<Int, Int, Int>) = min(min(elem.first, elem.second), elem.third)
        private fun maxFrom(elem: Triple<Int, Int, Int>) = max(max(elem.first, elem.second), elem.third)
    }

    class Grid4D(private val activeCubes: Set<List<Int>>) {

        companion object {
            fun from(split: List<String>): Grid4D {
                val result = mutableSetOf<List<Int>>()
                split.forEachIndexed { y, line ->
                    line.forEachIndexed { x, cube ->
                        if (cube == '#') {
                            result.add(listOf(x, y, 0, 0))
                        }
                    }
                }
                return Grid4D(result)
            }
        }

        fun nextRound(): Grid4D {
            val toProcess: MutableSet<List<Int>> = activeCubes.toMutableSet()
            val alreadyProcessed = mutableSetOf<List<Int>>()

            val result = mutableSetOf<List<Int>>()

            while (toProcess.isNotEmpty()) {
                val current: List<Int> = toProcess.first().also {
                    toProcess.remove(it)
                }
                if (current !in alreadyProcessed) {
                    val (activeN, inactiveN) = neighbours(current[0], current[1], current[2], current[3])
                    val currentActive = isActive(current)
                    if (currentActive) {
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    }

                    if (currentActive && (activeN.size == 2 || activeN.size == 3)) {
                        result.add(current)
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    } else if (!currentActive && activeN.size == 3) {
                        result.add(current)
                        toProcess.addAll(
                            activeN.minus(alreadyProcessed)
                        )
                        toProcess.addAll(
                            inactiveN.minus(alreadyProcessed)
                        )
                    }
                    alreadyProcessed.add(current)
                }
            }
            return Grid4D(result)
        }

        private fun isActive(elem: List<Int>): Boolean {
            return activeCubes.contains(elem)
        }

        private fun neighbours(x: Int, y: Int, z: Int, w: Int): Pair<Set<List<Int>>, Set<List<Int>>> {
            val active = mutableSetOf<List<Int>>()
            val inActive = mutableSetOf<List<Int>>()
            for (x1 in x - 1..x + 1) {
                for (y1 in y - 1..y + 1) {
                    for (z1 in z - 1..z + 1) {
                        for (w1 in w - 1..w + 1) {
                            if (x1 == x && y1 == y && z1 == z && w1 == w) continue
                            else {
                                if (isActive(listOf(x1, y1, z1, w1))) active.add(listOf(x1, y1, z1, w1))
                                else inActive.add(listOf(x1, y1, z1, w1))
                            }
                        }
                    }
                }
            }
            return active to inActive
        }

        fun countActive(): Int = activeCubes.size
    }

}

