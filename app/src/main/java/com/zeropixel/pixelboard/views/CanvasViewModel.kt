package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zeropixel.engine.Engine
import com.zeropixel.engine.actions.ClearAction
import com.zeropixel.engine.actions.UndoAction
import com.zeropixel.engine.serialization.saveEngine
import com.zeropixel.engine.tools.EraserTool
import com.zeropixel.engine.tools.FillTool
import com.zeropixel.engine.tools.PenTool
import com.zeropixel.engine.utils.ColorId
import com.zeropixel.engine.utils.LayerId
import com.zeropixel.pixelboard.components.actions.clearActionIcon
import com.zeropixel.pixelboard.components.actions.undoActionIcon
import com.zeropixel.pixelboard.components.tools.eraserToolConfiguration
import com.zeropixel.pixelboard.components.tools.eraserToolIcon
import com.zeropixel.pixelboard.components.tools.fillToolIcon
import com.zeropixel.pixelboard.components.tools.penToolConfiguration
import com.zeropixel.pixelboard.components.tools.penToolIcon
import com.zeropixel.pixelboard.utils.ComposableAction
import com.zeropixel.pixelboard.utils.ComposableTool

class CanvasViewModel(
    val engine: Engine,
    private val filesDir: String,
) : ViewModel() {
    var currentLayerId by mutableStateOf<LayerId>(0)
    var currentColorId by mutableStateOf<ColorId>(0)

    val toolPalette = listOf(
        ComposableTool(PenTool(engine), penToolIcon, penToolConfiguration),
        ComposableTool(EraserTool(engine), eraserToolIcon, eraserToolConfiguration),
        ComposableTool(FillTool(engine), fillToolIcon),
    )
    var currentTool by mutableStateOf(toolPalette.first())

    val actionPalette = listOf(
        ComposableAction(ClearAction(engine), clearActionIcon),
        ComposableAction(UndoAction(engine), undoActionIcon),
    )

    var rerenderCanvasState by mutableStateOf(false)
    var rerenderLayersState by mutableStateOf(false)

    fun executeAction(action: ComposableAction<*>) {
        action.action.execute()

        saveToFile()
    }

    fun startToolDraw(x: Int, y: Int) {
        if (x < 0 || x >= engine.canvas.width || y < 0 || y >= engine.canvas.height) return

        currentTool.tool.drawStart(x, y)

        rerenderCanvas()
    }

    fun toolDraw(x: Int, y: Int) {
        if (x < 0 || x >= engine.canvas.width || y < 0 || y >= engine.canvas.height) return

        currentTool.tool.draw(x, y)

        rerenderCanvas()
    }

    fun endToolDraw() {
        currentTool.tool.drawEnd()

        rerenderCanvas()

        saveToFile()
    }

    fun toggleLayerVisibility(layerId: LayerId) {
        engine.canvas[layerId].visible = !engine.canvas[layerId].visible

        rerenderCanvas()
        rerenderLayers()
    }

    fun expectedQuickBarColumns(): Int = (engine.palette.size - 1) / 8 + 1

    private fun saveToFile() {
        saveEngine(filesDir, "default", engine)
    }

    private fun rerenderCanvas() {
        rerenderCanvasState = !rerenderCanvasState
    }

    private fun rerenderLayers() {
        rerenderLayersState = !rerenderLayersState
    }
}