package pl.szymanski.hubert.day07

data class CalibrationEquation(
    val sum: Long,
    val elements: List<Long>
) {
    fun solve(operators: Set<Operator>): Boolean {
        if (sum < elements[0]) return false
        if (elements.size == 1) return (sum == elements.first())
        val subElements = elements.subList(2, elements.size)
        return operators.asSequence()
            .map { it ->
                copy(elements = listOf(it.calculate(elements[0], elements[1])) + subElements).solve(operators)
            }.any { it == true }
    }
}