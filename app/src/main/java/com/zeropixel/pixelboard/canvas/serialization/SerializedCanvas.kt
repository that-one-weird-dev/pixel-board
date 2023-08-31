package com.zeropixel.pixelboard.canvas.serialization

import com.zeropixel.pixelboard.canvas.Canvas
import com.zeropixel.pixelboard.canvas.palette.ColorPalette
import com.zeropixel.pixelboard.canvas.utils.ColorInt
import kotlinx.serialization.Serializable

@Serializable
data class SerializedCanvas(
    val width: Int,
    val height: Int,
    val layers: List<SerializedLayer>,
    val palette: List<ColorInt>,
) {
    fun deserialize(): Canvas {
        return Canvas(
            width,
            height,
            ColorPalette(palette),
            layers.map { it.deserialize(width, height) },
        )
    }

    companion object {
        fun from(canvas: Canvas): SerializedCanvas {
            return SerializedCanvas(
                canvas.width,
                canvas.height,
                canvas.layers.map { SerializedLayer.from(it) },
                canvas.palette.colors,
            )
        }
    }
}