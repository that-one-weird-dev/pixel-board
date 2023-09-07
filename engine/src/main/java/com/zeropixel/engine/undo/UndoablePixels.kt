package com.zeropixel.engine.undo

import com.zeropixel.engine.Engine
import com.zeropixel.engine.utils.ColorInt

class UndoablePixels(
    val engine: Engine,
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

    override fun undo() {
        pixels.forEach { pixel ->
            engine.canvas.setPixel(layerId, pixel.x, pixel.y, pixel.color)
        }
    }
}