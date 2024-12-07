package pl.szymanski.hubert.day07

import pl.szymanski.hubert.runner.Runner

class BridgeRepair(rawInput: List<String>) : Runner {
    private val equations = rawInput.map { line ->
        line.split(": ")
            .let {
                CalibrationEquation(
                    sum = it[0].toLong(),
                    elements = it[1].split(" ").map { it.toLong() })
            }
    }

    override fun runPartI(): Long =
        equations
            .filter { it.solve(setOf(Operator.ADD, Operator.MULTIPLY)) }
            .sumOf { it.sum }


    override fun runPartII(): Long =
        equations
            .filter { it.solve(setOf(Operator.ADD, Operator.MULTIPLY, Operator.CONCAT)) }
            .sumOf { it.sum }
}
