package geometry

/**
 * 2次元座標を表すオブジェクト
 * @property x X座標
 * @property y Y座標
 */
data class Point(
    val x: Double,
    val y: Double
) {
    companion object {
        /** 原点(0, 0) */
        val ORIGIN = Point(0.0, 0.0)
    }

    /**
     * 整数値([x], [y])から作成する
     */
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    operator fun plus(other: Point) : Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point) : Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(scalar: Double) : Point {
        return Point(scalar * x, scalar * y)
    }

    operator fun times(scalar: Int) : Point {
        return this * scalar.toDouble()
    }
}