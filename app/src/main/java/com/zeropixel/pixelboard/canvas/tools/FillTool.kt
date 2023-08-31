package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.views.CanvasViewModel

class FillTool : Tool {
    override val configuration: (@Composable () -> Unit)? = null

    private val undoableBuilder = UndoablePixelsBuilder()

    override fun CanvasViewModel.drawStart(x: Int, y: Int) {
        val overriddenColor = currentLayer.bitmap.getColor(x, y) ?: return
        if (overriddenColor == currentColor) return

        fill(x, y, overriddenColor)

        pushUndoable(undoableBuilder.build("Fill", currentLayerId))
    }

    @Composable
    override fun icon(): ImageVector = Icons.Default.Place

    private fun CanvasViewModel.fill(x: Int, y: Int, overriddenColor: Color) {
        val color = currentLayer.bitmap.getColor(x, y) ?: return
        if (color != overriddenColor) return

        currentLayer.bitmap.setPixel(x, y, currentColor)

        undoableBuilder.addPixel(x, y, color)

        fill(x + 1, y, overriddenColor)
        fill(x - 1, y, overriddenColor)
        fill(x, y + 1, overriddenColor)
        fill(x, y - 1, overriddenColor)
    }
}