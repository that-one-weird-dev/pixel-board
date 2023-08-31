package com.zeropixel.pixelboard.canvas.palette

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap
import com.zeropixel.pixelboard.canvas.utils.ColorInt

class ColorPalette(
    val colors: List<ColorInt> = listOf(),
) {
    val size
        get() = colors.size

    operator fun get(index: Int): ColorInt {
        return colors[index]
    }

    companion object {
        fun fromBitmap(bitmap: ImageBitmap): ColorPalette {
            val pixels = bitmap.toPixelMap()

            val colors = mutableSetOf<ColorInt>()

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