package com.zeropixel.engine.tools

import com.zeropixel.engine.Engine
import com.zeropixel.engine.undo.UndoablePixelsBuilder
import com.zeropixel.engine.utils.ColorInt

class FillTool(val engine: Engine) : Tool {
    private val undoableBuilder = UndoablePixelsBuilder(engine)

    override fun drawStart(x: Int, y: Int) {
        val currentColor = engine.currentColor
        val overriddenColor = engine.canvas.getPixel(engine.currentLayerId, x, y)
        if (overriddenColor == currentColor) return

        fill(x, y, overriddenColor)

        engine.pushUndoable(undoableBuilder.build("Fill", engine.currentLayerId))
    }

    private fun fill(x: Int, y: Int, overriddenColor: ColorInt) {
        if (x < 0 || y < 0 || x >= engine.canvas.width || y >= engine.canvas.height) return

        val color = engine.currentLayer.getPixel(x, y)
        if (color != overriddenColor) return

        val currentColor = engine.currentColor

        engine.currentLayer.setPixel(x, y, currentColor)

        undoableBuilder.addPixel(x, y, color)

        fill(x + 1, y, overriddenColor)
        fill(x - 1, y, overriddenColor)
        fill(x, y + 1, overriddenColor)
        fill(x, y - 1, overriddenColor)
    }
}