package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.canvas.utils.DrawUtils
import com.zeropixel.pixelboard.ui.components.toolbar.ToolConfigSlider
import com.zeropixel.pixelboard.views.CanvasViewModel

class EraserTool : Tool {
    private var size = 3

    private val undoableBuilder = UndoablePixelsBuilder()

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

    override fun CanvasViewModel.draw(x: Int, y: Int) {
        DrawUtils.circle(x, y, size / 2f) { xPos, yPos ->
            val oldColor = canvas.setPixel(currentLayerId, xPos, yPos, 0x00000000)

            undoableBuilder.addPixel(xPos, yPos, oldColor)
        }
    }

    override fun CanvasViewModel.drawEnd() {
        pushUndoable(undoableBuilder.build("Eraser stroke", currentLayerId))
    }
}