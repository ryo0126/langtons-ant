package extension

import geometry.Point
import geometry.Rect
import javafx.scene.canvas.GraphicsContext

/**
 * レクト[rect]で塗りつぶす
 */
fun GraphicsContext.fillRect(rect: Rect) {
    fillRect(rect.minX, rect.minY, rect.size.width, rect.size.height)
}

/**
 * 座標[point]に文字列[text]を表示する
 */
fun GraphicsContext.fillText(text: String, point: Point) {
    fillText(text, point.x, point.y)
}

