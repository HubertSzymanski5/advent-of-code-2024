package pl.szymanski.hubert.day07

enum class Operator(val calculate: (Long, Long) -> Long) {
    ADD({ a, b -> a + b }),
    MULTIPLY({ a, b -> a * b }),
    CONCAT({ a, b -> "$a$b".toLong() })
}