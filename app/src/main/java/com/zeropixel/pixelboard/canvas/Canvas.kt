package com.zeropixel.pixelboard.canvas

import com.zeropixel.pixelboard.canvas.palette.ColorPalette
import com.zeropixel.pixelboard.canvas.utils.ColorInt
import com.zeropixel.pixelboard.canvas.utils.LayerId

class Canvas(
    val width: Int,
    val height: Int,
    var palette: ColorPalette = ColorPalette(),
    val layers: List<Layer> = mutableListOf(Layer(width, height, "Default")),
) {

    fun setPixel(layerId: LayerId, x: Int, y: Int, color: ColorInt): ColorInt {
        return layers[layerId].bitmap.setPixel(x, y, color)
    }

    fun getPixel(layerId: LayerId, x: Int, y: Int): ColorInt {
        return layers[layerId].bitmap.getPixel(x, y)
    }
}