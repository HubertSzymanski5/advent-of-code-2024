package pl.szymanski.hubert.day10

import pl.szymanski.hubert.runner.Runner
import pl.szymanski.hubert.utils.Map2D
import pl.szymanski.hubert.utils.WordDirection

import pl.szymanski.hubert.utils.Vector as Point

class HoofIt(rawInput: List<String>) : Runner {
    val map = Map2D.from(rawInput) { it.toCharArray().map { it.digitToInt() } }

    override fun runPartI(): Long {
        return map.findTrialStarts().sumOf { map.findDistinctTrials(it) }
    }

    override fun runPartII(): Long {
        return map.findTrialStarts().sumOf { map.findTrialsFrom(it) }
    }

    private fun Map2D<Int>.findTrialStarts(): Set<Point> {
        return elementsWithCoords()
            .filter { it.second == 0 }
            .map { Point.from(it.first) }
            .toSet()
    }

    private fun Map2D<Int>.findTrialsFrom(start: Point): Long {
        return lookForTrail(start)
            ?.count()
            ?.toLong() ?: 0L
    }

    private fun Map2D<Int>.findDistinctTrials(start: Point): Long {
        return lookForTrail(start)
            ?.toSet()
            ?.count()
            ?.toLong() ?: 0L
    }

    private fun Map2D<Int>.lookForTrail(from: Point, prevHeight: Int = -1): List<Point>? {
        val height = getIfExists(from) ?: return null
        if (height != prevHeight + 1) return null
        if (height == 9) return listOf(from)

        return WordDirection.mainDirections()
            .mapNotNull { lookForTrail(from + it.vector, height) }
            .flatten()
    }
}