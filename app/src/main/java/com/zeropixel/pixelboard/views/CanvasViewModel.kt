package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.zeropixel.pixelboard.canvas.CanvasBitmap
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.actions.ClearAction
import com.zeropixel.pixelboard.canvas.tools.EraserTool
import com.zeropixel.pixelboard.canvas.tools.PenTool
import com.zeropixel.pixelboard.ui.components.menus.ConfirmMenuOptions

class CanvasViewModel(
    width: Int = 32,
    height: Int = 32,
) : ViewModel() {

    var confirmMenu by mutableStateOf<ConfirmMenuOptions?>(null)

    val canvasBitmap = CanvasBitmap(width, height)
    var imageBitmap by mutableStateOf(canvasBitmap.asImageBitmap())
        private set

    val colorPalette = listOf(Color.Black, Color.Red, Color.Green, Color.Blue)
    var currentColor by mutableStateOf(colorPalette.firstOrNull() ?: Color.Black)

    val toolPalette = listOf(PenTool(), EraserTool())
    var currentTool by mutableStateOf(toolPalette.firstOrNull() ?: PenTool())

    val actionPalette = listOf<Action>(ClearAction())

    fun executeAction(action: Action) {
        with(action) {
            execute()
        }
    }

    fun useAt(x: Int, y: Int) {
        if (x < 0 || x >= canvasBitmap.width || y < 0 || y >= canvasBitmap.height) return

        with(currentTool) {
            use(x, y)
        }

        imageBitmap = canvasBitmap.asImageBitmap()
    }
}