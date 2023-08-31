package com.zeropixel.pixelboard.canvas.undo

import androidx.compose.ui.graphics.Color

class UndoablePixelsBuilder {
    private var pixels = mutableSetOf<UndoablePixels.UndoPixel>()

    fun addPixel(x: Int, y: Int, color: Color): UndoablePixelsBuilder {
        pixels.add(UndoablePixels.UndoPixel(x, y, color))

        return this
    }

    fun build(name: String, layerId: Int): UndoablePixels {
        val undoable = UndoablePixels(name, pixels, layerId)

        pixels = mutableSetOf()

        return undoable
    }
}