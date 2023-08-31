package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.canvas.undo.UndoablePixelsBuilder
import com.zeropixel.pixelboard.canvas.utils.circlePixels
import com.zeropixel.pixelboard.canvas.utils.linePixels
import com.zeropixel.pixelboard.ui.components.toolbar.ToolConfigSlider
import com.zeropixel.pixelboard.views.CanvasViewModel

class PenTool : Tool {
    private val undoableBuilder = UndoablePixelsBuilder()

    private var size = 1

    private var oldX = 0
    private var oldY = 0

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

    override fun CanvasViewModel.drawStart(x: Int, y: Int) {
        oldX = x
        oldY = y
    }

    override fun CanvasViewModel.draw(x: Int, y: Int) {
        linePixels(oldX, oldY, x, y) { lineX, lineY ->
            circlePixels(lineX, lineY, size / 2f) { xPos, yPos ->
                val oldColor =
                    canvas.setPixel(currentLayerId, xPos, yPos, canvas.palette[currentColorId])

                undoableBuilder.addPixel(xPos, yPos, oldColor)
            }
        }

        oldX = x
        oldY = y
    }

    override fun CanvasViewModel.drawEnd() {
        pushUndoable(undoableBuilder.build("Pen stroke", currentLayerId))
    }
}