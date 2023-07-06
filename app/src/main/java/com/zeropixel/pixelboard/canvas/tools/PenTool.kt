package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.views.CanvasViewModel

class PenTool : Tool {

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.Edit
    }

    override fun CanvasViewModel.use(x: Int, y: Int) {
        canvasBitmap.drawPixel(x, y, currentColor)
    }
}