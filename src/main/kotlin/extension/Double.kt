package extension

import geometry.Point

operator fun Double.times(point: Point) : Point {
    return point * this
}