package com.zeropixel.engine.actions

import com.zeropixel.engine.Engine
import com.zeropixel.engine.undo.UndoablePixelsBuilder
import com.zeropixel.engine.utils.rectanglePixels

class ClearAction(
    val engine: Engine,
) : Action {

    private val undoableBuilder = UndoablePixelsBuilder(engine)

    override fun execute() {
        rectanglePixels(0, 0, engine.canvas.width, engine.canvas.height) { x, y ->
            val oldPixel = engine.currentLayer.setPixel(x, y, 0x00000000)

            undoableBuilder.addPixel(x, y, oldPixel)
        }

        engine.pushUndoable(undoableBuilder.build("Clear screen", engine.currentLayerId))
    }
}