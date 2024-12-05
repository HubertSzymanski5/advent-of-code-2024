package pl.szymanski.hubert.day05

import pl.szymanski.hubert.runner.Runner

class PrintQueue(rawInput: List<String>) : Runner {
    private val input =
        rawInput.splitInput()
            .let {
                Input(
                    rules = it.first.toRules(),
                    updates = it.second.toUpdates()
                )
            }
    private val rules by lazy { input.rules }
    private val updates by lazy { input.updates }

    override fun runPartI(): Long {
        return updates.filter {
            rules.abidesAll(it)
        }.sumOf { it.middle() }
    }

    private fun List<Int>.middle() = this[size / 2].toLong()

    override fun runPartII(): Long {
        return updates.filter { !rules.abidesAll(it) }
            .map { it.fix() }
            .sumOf { it.middle() }
    }

    private fun List<Int>.fix(): List<Int> {
        val firstBroken = rules.firstBrokenFor(this) ?: return this
        return swap(firstBroken.expectedAfter, firstBroken.number).fix()
    }

    private fun List<Int>.swap(num1: Int, num2: Int): List<Int> {
        return toMutableList()
            .let {
                val i1 = indexOf(num1)
                val i2 = indexOf(num2)
                it[i1] = num2
                it[i2] = num1
                return@let it
            }.toList()
    }

    private fun List<String>.splitInput(): Pair<List<String>, List<String>> {
        val emptyLineAt = this.indexOf("")
        return this.subList(0, emptyLineAt) to this.subList(emptyLineAt + 1, size)
    }

    private fun List<Rule>.firstBrokenFor(update: List<Int>): Rule? {
        val numbersBefore = mutableSetOf<Int>()
        for (num in update) {
            val rule = this.firstOrNull { it.violates(num, numbersBefore) }
            if (rule != null) {
                return rule
            }
            numbersBefore.add(num)
        }
        return null
    }

    private fun List<Rule>.abidesAll(update: List<Int>): Boolean {
        val numbersBefore = mutableSetOf<Int>()
        for (num in update) {
            if (this.any { it.violates(num, numbersBefore) }) return false
            numbersBefore.add(num)
        }
        return true
    }

    private fun List<String>.toRules() = map { Rule.from(it) }

    private fun List<String>.toUpdates() = map { it.split(",").map { it.toInt() } }

    private data class Input(val rules: List<Rule>, val updates: List<List<Int>>)
}

data class Rule(val number: Int, val expectedAfter: Int) {
    fun violates(currentNumber: Int, numbersBefore: Set<Int>): Boolean {
        return when {
            number != currentNumber -> false
            numbersBefore.contains(expectedAfter) -> true
            else -> false
        }
    }

    companion object {
        fun from(string: String): Rule {
            return string.split("|")
                .let { Rule(it[0].toInt(), it[1].toInt()) }
        }
    }
}