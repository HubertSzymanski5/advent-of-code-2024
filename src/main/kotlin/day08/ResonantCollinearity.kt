package pl.szymanski.hubert.day08

import pl.szymanski.hubert.runner.Runner
import pl.szymanski.hubert.utils.Map2D
import pl.szymanski.hubert.utils.Vector

class ResonantCollinearity(rawInput: List<String>) : Runner {
    private val map: Map2D<Char> = Map2D.from(rawInput) { it.toCharArray().toList() }
    private val antennasGroups = map.groupAntennas()

    override fun runPartI(): Long =
        antennasGroups
            .map {
                findAntiNodesFor(it.value) { start, diff -> findAntiNodesInDir(start, diff) }
            }
            .reduce { set, points -> set + points }
            .count()
            .toLong()

    override fun runPartII(): Long =
        antennasGroups
            .map {
                findAntiNodesFor(it.value) { start, diff ->
                    findHarmonicsAntiNodesInDir(start, diff) + findHarmonicsAntiNodesInDir(start, -diff)
                }
            }
            .reduce { set, points -> set + points }
            .count()
            .toLong()

    private fun findAntiNodesFor(
        antennas: List<Pair<Int, Int>>,
        runStrategy: (Vector, Vector) -> Set<Pair<Int, Int>>
    ): Set<Pair<Int, Int>> {
        val result = mutableSetOf<Pair<Int, Int>>()
        for (i in 0 until antennas.size) {
            for (j in i + 1 until antennas.size) {
                val pi = Vector.from(antennas[i])
                val pj = Vector.from(antennas[j])
                val diff = pj - pi

                result += runStrategy(pi, diff)
            }
        }
        return result
    }

    fun findAntiNodesInDir(start: Vector, diff: Vector): Set<Pair<Int, Int>> {
        val result = mutableSetOf<Pair<Int, Int>>()
        val first = start - diff
        val second = start + 2 * diff
        if (map.getIfExists(first) != null) result.add(first.x to first.y)
        if (map.getIfExists(second) != null) result.add(second.x to second.y)

        return result
    }

    fun findHarmonicsAntiNodesInDir(start: Vector, diff: Vector): Set<Pair<Int, Int>> {
        val result = mutableSetOf<Pair<Int, Int>>()
        var point = start
        while (map.getIfExists(point) != null) {
            result.add(point.x to point.y)
            point = point + diff
        }
        return result
    }

    operator fun Int.times(other: Vector): Vector {
        return other * this
    }

    private fun Map2D<Char>.groupAntennas(): Map<Char, List<Pair<Int, Int>>> {
        val result = mutableMapOf<Char, List<Pair<Int, Int>>>()
        elementsWithCoords().forEach {
            if (it.second != '.') {
                result[it.second] = result.getOrDefault(it.second, emptyList()).plus(it.first)
            }
        }
        return result.toMap()
    }
}