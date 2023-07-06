package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R
import com.zeropixel.pixelboard.views.CanvasViewModel

class EraserTool : Tool {

    @Composable
    override fun icon(): ImageVector {
        return ImageVector.vectorResource(id = R.drawable.ic_eraser)
    }

    override fun CanvasViewModel.use(x: Int, y: Int) {
        canvasBitmap.drawPixel(x, y, Color.Transparent)
    }
}