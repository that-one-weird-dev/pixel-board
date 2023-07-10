package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.lifecycle.ViewModel
import com.zeropixel.pixelboard.canvas.CanvasBitmap
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.actions.ClearAction
import com.zeropixel.pixelboard.canvas.tools.EraserTool
import com.zeropixel.pixelboard.canvas.tools.PenTool
import com.zeropixel.pixelboard.utils.AlertDialogOptions

class CanvasViewModel(
    width: Int = 32,
    height: Int = 32,
) : ViewModel() {

    var showAlertDialog by mutableStateOf(false)
    var alertOptions by mutableStateOf(AlertDialogOptions("") {})
        private set

    val canvasBitmap = CanvasBitmap(width, height)
    var imageBitmap by mutableStateOf(canvasBitmap.asImageBitmap())
        private set

    var colorPalette = listOf(Color.Black, Color.Red, Color.Blue, Color.Green)
    var currentColor by mutableStateOf(colorPalette.firstOrNull() ?: Color.Black)

    val toolPalette = listOf(PenTool(), EraserTool())
    var currentTool by mutableStateOf(toolPalette.firstOrNull() ?: PenTool())

    val actionPalette = listOf<Action>(ClearAction())

    fun showAlertDialog(options: AlertDialogOptions) {
        alertOptions = options
        showAlertDialog = true
    }

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

    fun loadPalette(bitmap: ImageBitmap) {
        val pixels = bitmap.toPixelMap()

        val colors = mutableListOf<Color>()

        repeat(pixels.width) { x ->
            repeat(pixels.height) { y ->
                val color = pixels[x, y]

                if (!colors.contains(color)) {
                    colors.add(color)
                }
            }
        }

        colorPalette = colors
        currentColor = colorPalette.firstOrNull() ?: Color.Black
    }

    fun expectedQuickBarColumns(): Int = (colorPalette.size - 1) / 8 + 1
}