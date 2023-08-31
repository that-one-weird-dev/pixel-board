package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.canvas.utils.rectanglePixels
import com.zeropixel.pixelboard.views.CanvasViewModel

class ClearAction : Action {

    private val undoableBuilder = UndoablePixelsBuilder()

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.Clear
    }

    override fun CanvasViewModel.execute() {
        rectanglePixels(0, 0, canvas.width, canvas.height) { x, y ->
            val oldPixel = canvas.setPixel(currentLayerId, x, y, 0x00000000)

            undoableBuilder.addPixel(x, y, oldPixel)
        }

        pushUndoable(undoableBuilder.build("Clear screen", currentLayerId))
    }
}