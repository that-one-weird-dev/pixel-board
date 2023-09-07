package com.zeropixel.engine.serialization

import com.zeropixel.engine.Canvas
import kotlinx.serialization.Serializable

@Serializable
data class SerializedCanvas(
    val width: Int,
    val height: Int,
    val layers: List<SerializedLayer>,
) {
    fun deserialize(): Canvas {
        return Canvas(
            width,
            height,
            layers.map { it.deserialize(width, height) },
        )
    }

    companion object {
        fun from(canvas: Canvas): SerializedCanvas {
            return SerializedCanvas(
                canvas.width,
                canvas.height,
                canvas.layers.map { SerializedLayer.from(it) },
            )
        }
    }
}