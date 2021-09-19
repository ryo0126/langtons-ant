package extension

import geometry.Point

operator fun Int.times(point: Point) : Point {
    return point * this
}