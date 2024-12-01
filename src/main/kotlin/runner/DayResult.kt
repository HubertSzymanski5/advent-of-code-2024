package pl.szymanski.hubert.runner

data class DayResult(val day: Day, val partI: Long, val partII: Long) {
    fun print() {
        println(day.printableName())
        println("Part I: $partI")
        println("Part II: $partII")
    }

    private fun Day.printableName(): String =
        this.name.lowercase().replaceFirstChar { it.uppercase() }
}