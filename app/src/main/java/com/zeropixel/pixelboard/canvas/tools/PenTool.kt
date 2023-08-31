package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.canvas.utils.DrawUtils
import com.zeropixel.pixelboard.ui.components.toolbar.ToolConfigSlider
import com.zeropixel.pixelboard.views.CanvasViewModel

class PenTool : Tool {
    private var size = 1

    private val undoableBuilder = UndoablePixelsBuilder()

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

    override fun CanvasViewModel.draw(x: Int, y: Int) {
        DrawUtils.circle(x, y, size / 2f) { xPos, yPos ->
            val oldColor = currentLayer.bitmap.setPixel(xPos, yPos, currentColor)

            undoableBuilder.addPixel(xPos, yPos, Color(oldColor))
        }
    }

    override fun CanvasViewModel.drawEnd() {
        pushUndoable(undoableBuilder.build("Pen stroke", currentLayerId))
    }
}