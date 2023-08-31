package com.zeropixel.pixelboard.canvas.serialization

import com.zeropixel.pixelboard.canvas.Layer
import com.zeropixel.pixelboard.canvas.LayerBitmap
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

            bitmap.setPixel(x, y, color)
        }

        return Layer(bitmap, visible, name)
    }

    companion object {
        fun from(layer: Layer): SerializedLayer {
            val bitmapString = StringBuilder()

            for (y in 0 until layer.bitmap.height) {
                for (x in 0 until layer.bitmap.width) {
                    if (!(x == 0 && y == 0)) {
                        bitmapString.append('-')
                    }

                    val color = layer.bitmap.getPixel(x, y)
                        .toUInt()
                        .toString(16)

                    bitmapString.append(color)
                }
            }

            return SerializedLayer(layer.name, layer.visible, bitmapString.toString())
        }
    }
}