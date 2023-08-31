package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.canvas.utils.ColorInt
import com.zeropixel.pixelboard.views.CanvasViewModel

class FillTool : Tool {
    override val configuration: (@Composable () -> Unit)? = null

    private val undoableBuilder = UndoablePixelsBuilder()

    override fun CanvasViewModel.drawStart(x: Int, y: Int) {
        val currentColor = canvas.palette[currentColorId]
        val overriddenColor = canvas.getPixel(currentLayerId, x, y)
        if (overriddenColor == currentColor) return

        fill(x, y, overriddenColor)

        pushUndoable(undoableBuilder.build("Fill", currentLayerId))
    }

    @Composable
    override fun icon(): ImageVector = Icons.Default.Place

    private fun CanvasViewModel.fill(x: Int, y: Int, overriddenColor: ColorInt) {
        if (x < 0 || y < 0 || x >= canvas.width || y >= canvas.height) return

        val color = canvas.getPixel(currentLayerId, x, y)
        if (color != overriddenColor) return

        val currentColor = canvas.palette[currentColorId]

        canvas.setPixel(currentLayerId, x, y, currentColor)

        undoableBuilder.addPixel(x, y, color)

        fill(x + 1, y, overriddenColor)
        fill(x - 1, y, overriddenColor)
        fill(x, y + 1, overriddenColor)
        fill(x, y - 1, overriddenColor)
    }
}