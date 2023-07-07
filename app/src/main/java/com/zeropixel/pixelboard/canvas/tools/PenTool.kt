package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.ui.components.toolconfig.ToolConfigSlider
import com.zeropixel.pixelboard.views.CanvasViewModel

class PenTool : Tool {
    private var size = 1

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.Edit
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
        canvasBitmap.drawCircle(x, y, size - 1, currentColor)
    }
}