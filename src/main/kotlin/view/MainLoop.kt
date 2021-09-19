package view

import extension.fillRect
import geometry.Point
import geometry.Rect
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import langtonsant.BoxState
import langtonsant.LangtonsAntController

/**
 * メインループ
 */
object MainLoop : MainLoopBase() {

    /** 1フレームあたりに実行するラングトンのアリのステップ数 */
    private const val STEPS_PER_FRAME = 70
    /** キャンバス上のマスのサイズ(ピクセル) */
    private const val DOT_SIZE = 2
    /** 塗られているマスの色 */
    private val BOX_COLOR = Color.BLUE
    /** 塗られていないマスの色 */
    private val BLANK_COLOR = Color.BLACK
    /** アリがいるマスの色 */
    private val ANT_COLOR = Color.RED
    /** 現在のステップ数を表すラベルの文字列のテンプレート */
    private const val STEP_LABEL_TEMPLATE = "Steps: %d"

    /** 現在のラングトンのアリのステップ数を表示するラベル */
    private val stepLabel = Label()

    /** ラングトンのアリシミュレータ */
    private val langtonsAnt = LangtonsAntController.create(WINDOW_SIZE / DOT_SIZE)
    /** 現在のラングトンのアリのステップ数 */
    private var currentSteps = 0L
    /** ラングトンのアリのステップ実行を停止するかどうか */
    private var isStopping = true

    init {
        stepLabel.textFill = Color.WHITE
        stepLabel.font = Font.font("sans-serif", FontWeight.BOLD, 18.0)
        children.add(stepLabel)

        setLeftAnchor(stepLabel, 10.0)
        setBottomAnchor(stepLabel, 10.0)
    }

    override fun willDraw(context: GraphicsContext) {
        drawLangtonsAnt(context)
        updateStepLabel()

        if (!isStopping) {
            repeat(STEPS_PER_FRAME) {
                langtonsAnt.next()
                currentSteps += 1
            }
        }
    }

    override fun mouseDidClick(event: MouseEvent) {
        isStopping = !isStopping
    }

    /**
     * ラングトンのアリの現在の状態を[context]に描画する
     */
    private fun drawLangtonsAnt(context: GraphicsContext) {
        val width = langtonsAnt.boxes[0].size
        val height = langtonsAnt.boxes.size
        for (y in 0 until height) {
            for (x in 0 until width) {
                context.fill = if (langtonsAnt.boxes[y][x] == BoxState.COLORED) BOX_COLOR else BLANK_COLOR
                val positionInCanvas = Point(x, y) * DOT_SIZE
                context.fillRect(Rect(positionInCanvas, DOT_SIZE))
            }
        }
        // アリを描画
        context.fill = ANT_COLOR
        val antPositionInCanvas = langtonsAnt.currentAntPosition * DOT_SIZE
        context.fillRect(Rect(antPositionInCanvas, DOT_SIZE))
    }

    /**
     * 現在のラングトンのアリのステップ数を表示するラベルを更新する
     */
    private fun updateStepLabel() {
        stepLabel.text = STEP_LABEL_TEMPLATE.format(currentSteps)
    }
}