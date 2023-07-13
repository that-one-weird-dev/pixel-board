package com.zeropixel.pixelboard.canvas

class Layer(
    width: Int,
    height: Int,
) {
    val bitmap = LayerBitmap(width, height)
    val visible = true

    val width get() = bitmap.width
    val height get() = bitmap.height
}