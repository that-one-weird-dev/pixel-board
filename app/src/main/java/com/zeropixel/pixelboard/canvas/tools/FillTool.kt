package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.views.CanvasViewModel

class FillTool : Tool {
    override val configuration: (@Composable () -> Unit)? = null

    override fun CanvasViewModel.use(x: Int, y: Int) {
        val overriddenColor = currentLayer.bitmap.getColor(x, y) ?: return

        fill(x, y, overriddenColor)
    }

    @Composable
    override fun icon(): ImageVector = Icons.Default.Place

    private fun CanvasViewModel.fill(x: Int, y: Int, overriddenColor: Color) {
        val color = currentLayer.bitmap.getColor(x, y) ?: return
        if (color != overriddenColor) return

        currentLayer.bitmap.drawPixel(x, y, currentColor)

        fill(x + 1, y, overriddenColor)
        fill(x - 1, y, overriddenColor)
        fill(x, y + 1, overriddenColor)
        fill(x, y - 1, overriddenColor)
    }
}