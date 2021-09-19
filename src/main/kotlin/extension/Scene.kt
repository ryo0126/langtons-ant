package extension

import geometry.Size
import javafx.scene.Parent
import javafx.scene.Scene

/**
 * [root]をルートビューとしてサイズ[size]の[Scene]を作成する
 */
fun Scene(root: Parent, size: Size) : Scene {
    return Scene(root, size.width, size.height)
}