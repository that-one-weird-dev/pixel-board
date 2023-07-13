package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.lifecycle.ViewModel
import com.darkrockstudios.libraries.mpfilepicker.FileSelected
import com.zeropixel.pixelboard.canvas.Layer
import com.zeropixel.pixelboard.canvas.LayerBitmap
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.actions.ClearAction
import com.zeropixel.pixelboard.canvas.actions.SaveAction
import com.zeropixel.pixelboard.canvas.tools.EraserTool
import com.zeropixel.pixelboard.canvas.tools.FillTool
import com.zeropixel.pixelboard.canvas.tools.PenTool
import com.zeropixel.pixelboard.utils.AlertDialogOptions

class CanvasViewModel(
    width: Int = 32,
    height: Int = 32,
) : ViewModel() {

    var showAlertDialog by mutableStateOf(false)
    var alertOptions by mutableStateOf(AlertDialogOptions("") {})
        private set

    var showFilePicker by mutableStateOf(false)
    var filePickerFileExtensions by mutableStateOf(listOf<String>())
    var filePickerCallback by mutableStateOf<FileSelected>({})

    var showDirectoryPicker by mutableStateOf(false)
    var directoryPickerCallback by mutableStateOf<(String?) -> Unit>({})

    var layer by mutableStateOf(Layer(LayerBitmap(width, height)))

    var colorPalette = listOf(Color.Black, Color.Red, Color.Blue, Color.Green)
    var currentColor by mutableStateOf(colorPalette.firstOrNull() ?: Color.Black)

    val toolPalette = listOf(PenTool(), EraserTool(), FillTool())
    var currentTool by mutableStateOf(toolPalette.firstOrNull() ?: PenTool())

    val actionPalette = listOf(SaveAction(), ClearAction())

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

    fun executeAction(action: Action) {
        with(action) {
            execute()
        }
    }

    fun useAt(x: Int, y: Int) {
        if (x < 0 || x >= layer.width || y < 0 || y >= layer.height) return

        with(currentTool) {
            use(x, y)
        }

        layer = Layer(layer.bitmap, layer.visible)
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