package com.zeropixel.engine.undo

import com.zeropixel.engine.Engine
import com.zeropixel.engine.utils.ColorInt

class UndoablePixelsBuilder(
    val engine: Engine,
) {
    private var pixels = mutableSetOf<UndoablePixels.UndoPixel>()

    fun addPixel(x: Int, y: Int, color: ColorInt): UndoablePixelsBuilder {
        pixels.add(UndoablePixels.UndoPixel(x, y, color))

        return this
    }

    fun build(name: String, layerId: Int): UndoablePixels {
        val undoable = UndoablePixels(engine, name, pixels, layerId)

        pixels = mutableSetOf()

        return undoable
    }
}