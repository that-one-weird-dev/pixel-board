package com.zeropixel.engine.serialization

import com.zeropixel.engine.Layer
import com.zeropixel.engine.LayerBitmap
import kotlinx.serialization.Serializable

@Serializable
data class SerializedLayer(
    val name: String,
    val visible: Boolean,
    val bitmapString: String,
) {
    fun deserialize(width: Int, height: Int): Layer {
        val bitmap = LayerBitmap(width, height)

        bitmapString.split('-').forEachIndexed { i, colorString ->
            val color = colorString.toUInt(16).toInt()

            val x = i % width
            val y = i / width

            bitmap[x, y] = color
        }

        return Layer(bitmap, name, visible)
    }

    companion object {
        fun from(layer: Layer): SerializedLayer {
            val bitmapString = StringBuilder()

            for (y in 0 until layer.bitmap.height) {
                for (x in 0 until layer.bitmap.width) {
                    if (!(x == 0 && y == 0)) {
                        bitmapString.append('-')
                    }

                    val color = layer.bitmap[x, y]
                        .toUInt()
                        .toString(16)

                    bitmapString.append(color)
                }
            }

            return SerializedLayer(layer.name, layer.visible, bitmapString.toString())
        }
    }
}