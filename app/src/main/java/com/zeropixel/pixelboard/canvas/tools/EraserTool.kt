package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R
import com.zeropixel.pixelboard.ui.components.toolconfig.ToolConfigSlider
import com.zeropixel.pixelboard.views.CanvasViewModel

class EraserTool : Tool {
    private var size = 3

    @Composable
    override fun icon(): ImageVector {
        return ImageVector.vectorResource(id = R.drawable.ic_eraser)
    }

    override val configuration = @Composable {
        ToolConfigSlider(
            minValue = 1,
            maxValue = 10,
            defaultValue = size,
            onValueChange = { size = it },
        ) {
            Text("Size")
        }
    }

    override fun CanvasViewModel.use(x: Int, y: Int) {
        canvasBitmap.drawCircle(x, y, size / 2f, Color.Transparent)
    }
}