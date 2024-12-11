package pl.szymanski.hubert.day11

import pl.szymanski.hubert.runner.Runner

class PlutonianPebbles(rawInput: List<String>) : Runner {
    private val initialNumbers = rawInput.first().split(" ").map { it.toLong() }

    override fun runPartI(): Long {
        return initialNumbers.blink(25).entries.sumOf { it.value }
    }

    override fun runPartII(): Long {
        return initialNumbers.blink(75).entries.sumOf { it.value }
    }

    private fun List<Long>.blink(times: Int): Map<Long, Long> {
        var result = groupingBy { it }.eachCount().map { it.key to it.value.toLong() }.toMap()
        repeat(times) {
            result = result.blink()
        }
        return result
    }

    private fun Map<Long, Long>.blink(): Map<Long, Long> {
        return map {
            return@map when {
                it.key == 0L -> listOf(1L to it.value)
                it.key.toString().length % 2 == 0 -> it.key.split().map { num -> num to it.value }
                else -> listOf(it.key * 2024 to it.value)
            }
        }.flatten()
            .groupBy { it.first }
            .map { it.key to it.value.sumOf { it.second } }
            .toMap()
    }

    private fun Long.split(): List<Long> {
        return toString()
            .let {
                val mid = it.length / 2
                listOf(it.substring(0, mid), it.substring(mid, it.length))
            }.map { it.toLong() }
    }
}