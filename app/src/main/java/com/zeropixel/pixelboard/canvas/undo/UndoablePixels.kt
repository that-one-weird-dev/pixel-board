package com.zeropixel.pixelboard.canvas.undo

import com.zeropixel.pixelboard.canvas.utils.ColorInt
import com.zeropixel.pixelboard.views.CanvasViewModel

class UndoablePixels(
    override val name: String,
    private val pixels: Set<UndoPixel>,
    private val layerId: Int,
) : Undoable {
    data class UndoPixel(
        val x: Int,
        val y: Int,
        val color: ColorInt,
    ) {
        override fun hashCode(): Int {
            return x and y
        }

        override fun equals(other: Any?): Boolean {
            if (javaClass != other?.javaClass) return false

            other as UndoPixel

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }
    }

    override fun CanvasViewModel.undo() {
        pixels.forEach { pixel ->
            canvas.setPixel(layerId, pixel.x, pixel.y, pixel.color)
        }
    }
}