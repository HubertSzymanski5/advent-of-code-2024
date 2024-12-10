package pl.szymanski.hubert.utils

enum class WordDirection(val vector: Vector) {
    N(Vector(0, -1)),
    S(Vector(0, 1)),
    W(Vector(-1, 0)),
    E(Vector(1, 0)),
    NW(Vector(-1, -1)),
    NE(Vector(1, -1)),
    SW(Vector(-1, 1)),
    SE(Vector(1, 1));

    companion object {
        fun mainDirections() = setOf(N, S, W, E)
    }
}
