package com.zeropixel.pixelboard.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap
import com.zeropixel.engine.palette.ColorPalette
import com.zeropixel.engine.utils.ColorInt

fun ColorPalette.Companion.fromBitmap(bitmap: ImageBitmap): ColorPalette {
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