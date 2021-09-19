package langtonsant

import geometry.Point
import geometry.Size

/**
 * ラングトンのアリを操作するインターフェース
 */
interface LangtonsAntController {
    companion object {
        /**
         * ラングトンのアリを操作できるインスタンスをサイズ[Size]で作成する
         */
        fun create(size: Size) : LangtonsAntController = LangtonsAnt(size)
    }

    /** 現在のマスの状態 */
    val boxes: List<List<BoxState>>
    /** 現在のアリの場所 */
    val currentAntPosition: Point
    /** 現在のアリの向き */
    val currentAntOrientation: Orientation

    /**
     * 現在の状態から1ステップ進める
     */
    fun next()
}

/**
 * マスの状態
 */
enum class BoxState {
    /** 塗られていない状態 */
    BLANK,
    /** 塗られた状態 */
    COLORED;

    /**
     * 反対の状態を返す
     */
    fun inverted() : BoxState {
        return when (this) {
            BLANK -> COLORED
            COLORED -> BLANK
        }
    }
}

/**
 * 向き
 * @property vector 向きを表すベクトル
 */
enum class Orientation(val vector: Point) {
    /** 上向き */
    UP(Point(0, -1)),
    /** 右向き */
    RIGHT(Point(1, 0)),
    /** 下向き */
    DOWN(Point(0, 1)),
    /** 左向き */
    LEFT(Point(-1, 0));

    /**
     * 右に90°回転する
     */
    fun rotateRight() : Orientation {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }

    /**
     * 左に90°回転する
     */
    fun rotateLeft() : Orientation {
        return when (this) {
            UP -> LEFT
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
        }
    }
}

/**
 * 操作可能なラングトンのアリのシミュレータ
 */
private class LangtonsAnt(val size: Size) : LangtonsAntController {
    override val boxes: List<List<BoxState>>
        get() = _boxes
    private var _boxes: MutableList<MutableList<BoxState>> = MutableList(size.height.toInt()) { MutableList(size.width.toInt()) { BoxState.BLANK } }

    override val currentAntPosition: Point
        get() = _currentAntPosition
    private var _currentAntPosition: Point = Point(((size.width - 1) / 2).toInt(), ((size.height - 1) / 2).toInt())

    override val currentAntOrientation: Orientation
        get() = _currentAntOrientation
    private var _currentAntOrientation: Orientation = Orientation.RIGHT

    override fun next() {
        val currentBoxState = boxes[currentAntPosition.y.toInt()][currentAntPosition.x.toInt()]

        // 向きを変更
        _currentAntOrientation = when (currentBoxState) {
            BoxState.BLANK -> currentAntOrientation.rotateRight()
            BoxState.COLORED -> currentAntOrientation.rotateLeft()
        }
        // 現在のマスを反転
        _boxes[currentAntPosition.y.toInt()][currentAntPosition.x.toInt()] = currentBoxState.inverted()

        // 1マス進む
        val nextPosition = currentAntPosition + currentAntOrientation.vector
        // 位置が範囲外にならないように制限する
        _currentAntPosition = limitPosition(nextPosition)
    }

    /**
     * [boxes]の範囲を超えないように[position]を制限した座標を返す
     * 範囲からはみ出ていた場合は、反対側の端の座標に修正する
     */
    private fun limitPosition(position: Point) : Point {
        val minX = 0.0
        val maxX = size.width - 1.0
        val minY = 0.0
        val maxY = size.height - 1.0

        // 端からはみ出たら逆の端に移動させる
        val limitedX: Double = when {
            position.x < minX -> maxX
            position.x > maxX -> minX
            else -> position.x
        }

        val limitedY: Double = when {
            position.y < minY -> maxY
            position.y > maxY -> minY
            else -> position.y
        }

        return Point(limitedX, limitedY)
    }
}