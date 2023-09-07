package com.zeropixel.engine.palette

import com.zeropixel.engine.utils.ColorInt

class ColorPalette(
    val colors: List<ColorInt> = listOf(),
) {
    val size
        get() = colors.size

    operator fun get(index: Int): ColorInt {
        return colors[index]
    }

    companion object
}