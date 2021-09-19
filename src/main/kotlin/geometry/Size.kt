package geometry

data class Size(
    val width: Double,
    val height: Double
) {
    companion object {
        val ZERO = Size(0.0, 0.0)
    }

    constructor(width: Int, height: Int) : this(width.toDouble(), height.toDouble())
    constructor(size: Double) : this(size, size)
    constructor(size: Int) : this(size, size)

    operator fun times(scalar: Double) : Size {
        return Size(scalar * width, scalar * height)
    }

    operator fun times(scalar: Int) : Size {
        return times(scalar.toDouble())
    }

    operator fun div(scalar: Double) : Size {
        return Size(width / scalar, height / scalar)
    }

    operator fun div(scalar: Int) : Size {
        return div(scalar.toDouble())
    }
}