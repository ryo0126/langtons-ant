package extension

import geometry.Point
import javafx.scene.input.MouseEvent

/** マウスの現在位置 */
val MouseEvent.point: Point
    get() = Point(x, y)
/** マウスのシーン上のポイント */
val MouseEvent.scenePoint: Point
    get() = Point(sceneX, sceneY)
/** マウスのスクリーン上のポイント */
val MouseEvent.screenPoint: Point
    get() = Point(screenX, screenY)