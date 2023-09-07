package com.zeropixel.engine.tools

import com.zeropixel.engine.Engine
import com.zeropixel.engine.undo.UndoablePixelsBuilder
import com.zeropixel.engine.utils.circlePixels
import com.zeropixel.engine.utils.linePixels

class PenTool(
    val engine: Engine,
) : Tool {
    private val undoableBuilder = UndoablePixelsBuilder(engine)

    var size = 1

    private var oldX = 0
    private var oldY = 0

    override fun drawStart(x: Int, y: Int) {
        oldX = x
        oldY = y
    }

    override fun draw(x: Int, y: Int) {
        linePixels(oldX, oldY, x, y) { lineX, lineY ->
            circlePixels(lineX, lineY, size / 2f) { xPos, yPos ->
                val oldColor =
                    engine.currentLayer.setPixel(xPos, yPos, engine.currentColor)

                undoableBuilder.addPixel(xPos, yPos, oldColor)
            }
        }

        oldX = x
        oldY = y
    }

    override fun drawEnd() {
        engine.pushUndoable(undoableBuilder.build("Pen stroke", engine.currentLayerId))
    }
}