package pl.szymanski.hubert.day01

import pl.szymanski.hubert.runner.Runner
import kotlin.math.abs

class HistorianHysteria(private val input: List<String>) : Runner {

    override fun runPartI(): Long {
        val (first, second) = mapData()
        first.sort()
        second.sort()
        return first.zip(second).sumOf { (f, s) -> abs(s - f) }
    }

    override fun runPartII(): Long {
        val (first, second) = mapData()
        val histogram = second.toHistogram()
        return first.sumOf {
            (histogram[it] ?: 0) * it
        }
    }

    private fun mapData(): Pair<MutableList<Long>, MutableList<Long>> {
        val first = mutableListOf<Long>()
        val second = mutableListOf<Long>()
        input.forEach { line ->
            first.add(line.substringBefore(" ").toLong())
            second.add(line.substringAfterLast(" ").toLong())
        }
        return first to second
    }

    private fun List<Long>.toHistogram(): Map<Long, Int> {
        return this.groupingBy { it }.eachCount()
    }
}