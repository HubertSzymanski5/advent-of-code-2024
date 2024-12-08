package pl.szymanski.hubert.utils

data class Vector(val x: Int, val y: Int) {

    operator fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y)
    }

    operator fun times(scalar: Int): Vector {
        return Vector(x * scalar, y * scalar)
    }

    operator fun unaryMinus(): Vector {
        return Vector(-x, -y)
    }

    companion object {
        fun from(pair: Pair<Int, Int>): Vector {
            return Vector(pair.first, pair.second)
        }
    }
}