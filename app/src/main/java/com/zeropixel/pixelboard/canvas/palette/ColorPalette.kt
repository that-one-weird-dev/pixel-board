package com.zeropixel.pixelboard.canvas.palette

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap

class ColorPalette(
    val colors: List<Int> = listOf(0x00000000),
) {
    init {
        if (colors.isEmpty()) throw IllegalArgumentException("Colors must have at least one argument")
    }

    val size
        get() = colors.size

    operator fun get(index: Int): Int {
        return colors[index]
    }

    companion object {
        fun fromBitmap(bitmap: ImageBitmap): ColorPalette {
            val pixels = bitmap.toPixelMap()

            val colors = mutableSetOf(0x00000000)

            repeat(pixels.width) { x ->
                repeat(pixels.height) { y ->
                    val color = pixels[x, y]

                    colors.add(color.toArgb())
                }
            }

            return ColorPalette(colors.toList())
        }
    }
}