package view

import geometry.Point
import geometry.Rect
import geometry.Size
import javafx.animation.AnimationTimer
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane

/**
 * メインループベース
 */
abstract class MainLoopBase : AnchorPane(), EventHandler<MouseEvent> {

    companion object {
        /** 現在のフレーム数 */
        var frameCount: Long = 0
            private set
        /** ウィンドウサイズ */
        val WINDOW_SIZE = Size(666, 532)
        /** ウィンドウレクト */
        val WINDOW_RECT = Rect(Point.ORIGIN, WINDOW_SIZE)
    }

    /** 次のフレームで現在の描画内容をクリアするかどうか */
    var isAutoClearingContext = true
    /**
     * 設定した属性を自動保存するかどうか
     * このフラグが`false`の場合は現在設定した属性を引き継がず、次フレームでは設定がリセットされる
     */
    var isAutoRestoringAttributes = true

    init {
        val canvas = Canvas(WINDOW_SIZE.width, WINDOW_SIZE.height)
        children.add(canvas)
        val context = canvas.graphicsContext2D

        val animationTimer = object : AnimationTimer() {

            override fun handle(now: Long) {
                if (isAutoClearingContext) {
                    context.clearRect(0.0, 0.0, WINDOW_SIZE.width, WINDOW_SIZE.height)
                }

                if (isAutoRestoringAttributes) {
                    context.save()
                }
                willDraw(context)
                if (isAutoRestoringAttributes) {
                    context.restore()
                }
                didDraw(context)

                frameCount++
            }
        }
        animationTimer.start()
    }

    final override fun handle(event: MouseEvent?) {
        event ?: return
        mouseDidClick(event)
    }

    /**
     * マウスクリックイベント[event]に対する処理を実装する
     */
    open fun mouseDidClick(event: MouseEvent) {}

    /**
     * キー押下イベント[event]に対する処理を実装する
     */
    open fun keyWasPressed(event: KeyEvent) {}

    /**
     * キー解放イベント[event]に対する処理を実装する
     */
    open fun keyWasReleased(event: KeyEvent) {}

    /**
     * [context]への描画を実装する
     */
    open fun willDraw(context: GraphicsContext) {}

    /**
     * [context]で描画した後の処理を実装する
     */
    open fun didDraw(context: GraphicsContext) {}
}