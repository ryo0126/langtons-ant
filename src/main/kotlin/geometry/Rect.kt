package geometry

/**
 * 座標付きの[Size]を表す
 * @property point 座標
 * @property size  大きさ
 */
data class Rect(
    val point: Point,
    val size: Size
) {
    /**
     * 座標[point]に長さ[size]の正方形を配置する
     */
    constructor(point: Point, size: Double) : this(point, Size(size))
    /**
     * 座標[point]に長さ[size]の正方形を配置する
     */
    constructor(point: Point, size: Int) : this(point, Size(size))
    /**
     * 座標([x], [y])に幅[width], 高さ[height]の長方形を配置する
     */
    constructor(x: Double, y: Double, width: Double, height: Double) : this(Point(x, y), Size(width, height))
    /**
     * 座標([x], [y])に長さ[size]の正方形を配置する
     */
    constructor(x: Double, y: Double, size: Double) : this(Point(x, y), Size(size))
    /**
     * 座標([x], [y])に幅[width], 高さ[height]の長方形を配置する
     */
    constructor(x: Int, y: Int, width: Int, height: Int) : this(Point(x, y), Size(width, height))
    /**
     * 座標([x], [y])に長さ[size]の正方形を配置する
     */
    constructor(x: Int, y: Int, size: Double) : this(Point(x, y), Size(size))

    /** この長方形の左上の頂点のX座標 */
    val minX: Double
        get() = point.x
    /** この長方形の左上の頂点のY座標 */
    val minY: Double
        get() = point.y
    /** この長方形の中央のX座標 */
    val centerX: Double
        get() = point.x + size.width / 2.0
    /** この長方形の中央のY座標 */
    val centerY: Double
        get() = point.y + size.height / 2.0
    /** この長方形の右下の頂点のX座標 */
    val maxX: Double
        get() = point.x + size.width
    /** この長方形の右下の頂点のY座標 */
    val maxY: Double
        get() = point.y + size.height

    /** この長方形の左上の座標 */
    val topLeft: Point
        get() = point
    /** この長方形の上辺中央の座標 */
    val topCenter: Point
        get() = Point(centerX, point.y)
    /** この長方形の右上の座標 */
    val topRight: Point
        get() = Point(maxX, point.y)
    /** この長方形の左辺中央の座標 */
    val centerLeft: Point
        get() = Point(point.x, centerY)
    /** この長方形の中央の座標 */
    val center: Point
        get() = Point(centerX, centerY)
    /** この長方形の右辺中央の座標 */
    val centerRight: Point
        get() = Point(maxX, centerY)
    /** この長方形の左下の座標 */
    val bottomLeft: Point
        get() = Point(point.x, maxY)
    /** この長方形の下辺中央の座標 */
    val bottomCenter: Point
        get() = Point(centerX, maxY)
    /** この長方形の右下の座標 */
    val bottomRight: Point
        get() = Point(maxX, maxY)

    /**
     * [x], [y]でオフセットした[Rect]を返す
     */
    fun offsetBy(x: Double = 0.0, y: Double = 0.0) : Rect {
        return this.copy(point = Point(minX + x, minY + y))
    }

    /**
     * [x], [y]でオフセットした[Rect]を返す
     */
    fun offsetBy(x: Int = 0, y: Int = 0) : Rect {
        return offsetBy(x.toDouble(), y.toDouble())
    }
}