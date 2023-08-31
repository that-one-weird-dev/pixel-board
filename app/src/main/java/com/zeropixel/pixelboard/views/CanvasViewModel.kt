package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.lifecycle.ViewModel
import com.darkrockstudios.libraries.mpfilepicker.FileSelected
import com.zeropixel.pixelboard.canvas.Layer
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.actions.ClearAction
import com.zeropixel.pixelboard.canvas.actions.UndoAction
import com.zeropixel.pixelboard.canvas.tools.EraserTool
import com.zeropixel.pixelboard.canvas.tools.FillTool
import com.zeropixel.pixelboard.canvas.tools.PenTool
import com.zeropixel.pixelboard.canvas.undo.Undoable
import com.zeropixel.pixelboard.utils.AlertDialogOptions

class CanvasViewModel(
    val width: Int = 32,
    val height: Int = 32,
) : ViewModel() {

    var showAlertDialog by mutableStateOf(false)
    var alertOptions by mutableStateOf(AlertDialogOptions("") {})
        private set

    var showFilePicker by mutableStateOf(false)
    var filePickerFileExtensions by mutableStateOf(listOf<String>())
    var filePickerCallback by mutableStateOf<FileSelected>({})

    var showDirectoryPicker by mutableStateOf(false)
    var directoryPickerCallback by mutableStateOf<(String?) -> Unit>({})

    val layers = mutableStateListOf(Layer(width, height, "Layer 0"), Layer(width, height, "Layer 1"))
    var currentLayerId by mutableStateOf(0)
        private set

    val currentLayer
        get() = layers[currentLayerId]

    var colorPalette = listOf(Color.Black, Color.Red, Color.Blue, Color.Green)
    var currentColor by mutableStateOf(colorPalette.firstOrNull() ?: Color.Black)

    val toolPalette = listOf(PenTool(), EraserTool(), FillTool())
    var currentTool by mutableStateOf(toolPalette.firstOrNull() ?: PenTool())

    val actionPalette = listOf(ClearAction(), UndoAction())

    private val undoStack = mutableListOf<Undoable>()

    var rerenderCanvasState by mutableStateOf(false)

    fun showAlertDialog(options: AlertDialogOptions) {
        alertOptions = options
        showAlertDialog = true
    }

    fun showFilePicker(selected: FileSelected) {
        filePickerCallback = selected
        showFilePicker = true
    }

    fun showDirectoryPicker(selected: (String?) -> Unit) {
        directoryPickerCallback = selected
        showDirectoryPicker = true
    }

    fun changeCurrentLayer(layerId: Int) {
        if (layerId < 0 || layerId >= layers.size) return

        currentLayerId = layerId
    }

    fun toggleLayerVisibility(layerId: Int) {
        val oldLayer = layers[layerId]

        layers[layerId] = Layer(oldLayer.bitmap, !oldLayer.visible, oldLayer.name)
    }

    fun executeAction(action: Action) {
        with(action) {
            execute()
        }
    }

    fun startToolDraw(x: Int, y: Int) {
        if (x < 0 || x >= width || y < 0 || y >= height) return

        with(currentTool) {
            drawStart(x, y)
        }

        rerenderCanvas()
    }

    fun toolDraw(x: Int, y: Int) {
        if (x < 0 || x >= width || y < 0 || y >= height) return

        with(currentTool) {
            draw(x, y)
        }

        rerenderCanvas()
    }

    fun endToolDraw() {
        with(currentTool) {
            drawEnd()
        }

        rerenderCanvas()
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

    fun pushUndoable(undoable: Undoable) {
        undoStack.add(undoable)
    }

    fun popUndoableAndUndo(): Boolean {
        val undoable = undoStack.lastOrNull() ?: return false
        undoStack.remove(undoable)

        with(undoable) {
            undo()
        }

        return true
    }

    fun expectedQuickBarColumns(): Int = (colorPalette.size - 1) / 8 + 1

    private fun rerenderCanvas() {
        rerenderCanvasState = !rerenderCanvasState
    }
}