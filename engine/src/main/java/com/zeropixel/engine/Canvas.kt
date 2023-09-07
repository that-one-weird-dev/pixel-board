package com.zeropixel.engine

import com.zeropixel.engine.utils.ColorInt
import com.zeropixel.engine.utils.LayerId

class Canvas(
    val width: Int,
    val height: Int,
    val layers: List<Layer> = mutableListOf(Layer(width, height, "Default")),
) {
    operator fun get(index: LayerId): Layer {
        return layers[index]
    }

    fun setPixel(layerId: LayerId, x: Int, y: Int, color: ColorInt): ColorInt {
        return get(layerId).setPixel(x, y, color)
    }

    fun getPixel(layerId: LayerId, x: Int, y: Int): ColorInt {
        return get(layerId).getPixel(x, y)
    }
}