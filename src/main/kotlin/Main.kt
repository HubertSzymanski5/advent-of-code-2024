package pl.szymanski.hubert

import pl.szymanski.hubert.runner.Day.DAY11
import pl.szymanski.hubert.runner.DayRunner.Companion.run
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        run(DAY11).print()
    }
    println("------------------------------")
    println("Time: ${ms / 1000}s ${ms % 1000}ms")
}