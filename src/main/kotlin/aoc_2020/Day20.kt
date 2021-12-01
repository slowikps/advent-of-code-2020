package aoc_2020

import java.lang.IllegalStateException


object Day20 {

    private val titleRegex = """Tile (\d+):""".toRegex()
    fun solution1(lines: List<String>): Long {
        val grouped: List<List<String>> = lines.split(true) {
            it.startsWith("Tile")
        }

        val images: List<Image> = grouped.drop(1).map { Image.from(it) }

        val sidesMap: Map<String, Grid> = images.fold(mutableMapOf<String, MutableSet<Image>>()) { acc, img ->
            listOf(img.edges.left, img.edges.right, img.rotatedEdges.left, img.rotatedEdges.right)
                .forEach {
                    acc.compute(it) { _, v ->
                        if (v == null) mutableSetOf(img)
                        else {
                            v.add(img)
                            v
                        }
                    }
                }
            acc
        }.mapNotNull {
            when {
                it.value.size == 1 -> {
                    null
                }
                it.value.size > 2 -> {
                    println("Not sure here")
                    throw IllegalStateException("Blah")
                }
                else -> {
                    it.key to Grid.from(it.value.first(), it.value.last())
                }
            }
        }.toMap()

        TODO("Not yet implemented")
    }

    data class Grid(val list: MutableList<Image>) {
        companion object {
            fun from(first: Image, second: Image): Grid {
                return if (first.edges.left == second.edges.right) {
                    Grid(mutableListOf(second, first))
                } else if (first.edges.left == second.rotatedEdges.right) {
                    second.roated = true
                    Grid(mutableListOf(second, first))
                } else if (first.edges.right == second.edges.left) {
                    Grid(mutableListOf(first, second))
                } else if (first.edges.right == second.rotatedEdges.left) {
                    second.roated = true
                    Grid(mutableListOf(first, second))
                } else {
                    throw IllegalStateException("Not sure")
                }
            }
        }

    }

    data class Edges(val top: String, val bottom: String, val left: String, val right: String) {
        companion object {
            fun from(content: List<String>): Pair<Edges, Edges> {
                val top = content.first()
                val bottom = content.last()
                val left = content.map { it.first() }.joinToString("")
                val right = content.map { it.last() }.joinToString("")

                return Edges(top, bottom, left, right) to Edges(
                    bottom.reversed(),
                    top.reversed(),
                    right.reversed(),
                    left.reversed()
                )
            }
        }
    }

    data class Image(
        val id: Long,
        val edges: Edges,
        val rotatedEdges: Edges,
        val content: List<String>,
        var roated: Boolean = false
    ) {
        companion object {
            fun from(lines: List<String>): Image {
                val (id: String) = titleRegex.find(lines.first())!!.destructured
                val content = lines.drop(1).dropLast(1)
                val (edges, rotatedEdges) = aoc_2020.Day20.Edges.Companion.from(content)
                return Image(id.toLong(), edges, rotatedEdges, content)
            }
        }
    }
}

