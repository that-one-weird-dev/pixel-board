package com.zeropixel.engine

import com.zeropixel.engine.utils.ColorInt

class Layer(
    val bitmap: LayerBitmap,
    val name: String = "Default",
    var visible: Boolean = true,
) {
    val width = bitmap.width
    val height = bitmap.height

    constructor(
        width: Int,
        height: Int,
        name: String = "Default",
        visible: Boolean = true,
        fillColor: ColorInt = 0x00000000,
    ) : this(LayerBitmap(width, height), name, visible) {
        fillPixels(fillColor)
    }

    fun setPixel(x: Int, y: Int, color: ColorInt): ColorInt {
        if (x < 0 || x >= width || y < 0 || y >= height) return color

        val oldColor = bitmap[x, y]

        bitmap[x, y] = color

        return oldColor
    }

    fun getPixel(x: Int, y: Int): ColorInt {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return 0x00000000

        return bitmap[x, y]
    }

    private fun fillPixels(color: ColorInt) {
        repeat(width) { x ->
            repeat(height) { y ->
                setPixel(x, y, color)
            }
        }
    }
}