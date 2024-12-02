package pl.szymanski.hubert.day02

import pl.szymanski.hubert.runner.Runner
import kotlin.math.abs

class RedNosedReports(private val rawInput: List<String>) : Runner {
    private val input by lazy { mapInput() }

    override fun runPartI(): Long =
        input.count { it.isSafe() }.toLong()

    override fun runPartII(): Long =
        input.count { it.isSafeWithDamper() }.toLong()

    private fun List<Int>.isSafe(): Boolean {
        val diffs = this.zipWithNext { x1, x2 -> x2 - x1 }
        return diffs.areInBound() && diffs.haveOneSign()
    }

    private fun List<Int>.isSafeWithDamper(): Boolean {
        if (isSafe()) return true
        return List(size) { index -> copyListSkipping(index).isSafe() }
            .any { it }
    }

    private fun List<Int>.copyListSkipping(index: Int): List<Int> {
        return toMutableList()
            .also { it.removeAt(index) }
    }

    private fun List<Int>.haveOneSign(): Boolean {
        if (first() == 0) return false
        val sign = first() / abs(first())
        return drop(1)
            .map { it * sign > 0 }
            .all { it }
    }

    private fun List<Int>.areInBound(): Boolean {
        return this.map { abs(it) in 1..3 }
            .all { it }
    }

    private fun mapInput() =
        this.rawInput.map { line ->
            line.split(" ")
                .map { it.toInt() }
        }
}