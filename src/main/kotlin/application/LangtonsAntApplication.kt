package application

import extension.Scene
import javafx.application.Application
import javafx.stage.Stage
import view.MainLoop
import view.MainLoopBase
import java.lang.IllegalStateException

class LangtonsAntApplication : Application() {

    override fun start(primaryStage: Stage?) {
        primaryStage ?: throw IllegalStateException("Primary stage is null.")

        val root = MainLoop
        val scene = Scene(root, MainLoopBase.WINDOW_SIZE)
        scene.onMouseClicked = root
        primaryStage.scene = scene
        primaryStage.title = "Langton's ant"
        primaryStage.isResizable = false
        primaryStage.show()
    }
}