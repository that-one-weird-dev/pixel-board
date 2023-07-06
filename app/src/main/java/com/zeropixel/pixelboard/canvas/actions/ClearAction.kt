package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.views.CanvasViewModel

class ClearAction : Action {

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.Clear
    }

    override fun CanvasViewModel.execute() {
        repeat(canvasBitmap.width) { x ->
            repeat(canvasBitmap.height) { y ->
                canvasBitmap.drawPixel(x, y, canvasBitmap.backgroundColor)
            }
        }
    }
}