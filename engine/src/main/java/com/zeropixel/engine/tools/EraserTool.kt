package com.zeropixel.engine.tools

import com.zeropixel.engine.Engine
import com.zeropixel.engine.undo.UndoablePixelsBuilder
import com.zeropixel.engine.utils.circlePixels

class EraserTool(
    val engine: Engine,
) : Tool {
    var size = 3

    private val undoableBuilder = UndoablePixelsBuilder(engine)

    override fun draw(x: Int, y: Int) {
        circlePixels(x, y, size / 2f) { xPos, yPos ->
            val oldColor = engine.currentLayer.setPixel(xPos, yPos, 0x00000000)

            undoableBuilder.addPixel(xPos, yPos, oldColor)
        }
    }

    override fun drawEnd() {
        engine.pushUndoable(undoableBuilder.build("Eraser stroke", engine.currentLayerId))
    }
}