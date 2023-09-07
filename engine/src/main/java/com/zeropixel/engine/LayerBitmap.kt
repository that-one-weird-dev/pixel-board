package com.zeropixel.engine

import com.zeropixel.engine.utils.ColorInt
import com.zeropixel.engine.utils.rectanglePixels

class LayerBitmap(
    val width: Int,
    val height: Int,
) {
    private val pixels = Array(width) { Array(height) { 0x00000000 } }

    operator fun get(x: Int, y: Int): ColorInt {
        return pixels[x][y]
    }

    operator fun set(x: Int, y: Int, value: ColorInt) {
        pixels[x][y] = value
    }

    companion object {
        fun backgroundGrid(width: Int, height: Int, cellSize: Int): LayerBitmap {
            val bitmap = LayerBitmap(width, height)

            rectanglePixels(0, 0, width, height) { x, y ->
                val isDarkCellHorizontally = x / cellSize == 0
                val isDarkCellVertically = y / cellSize == 0

                val color = if (isDarkCellHorizontally == isDarkCellVertically) {
                    0xffcacacc.toInt()
                } else {
                    0xfffdfdfd.toInt()
                }

                bitmap[x, y] = color
            }

            return bitmap
        }
    }
}