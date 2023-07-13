package com.zeropixel.pixelboard.canvas

class Layer(
    val bitmap: LayerBitmap,
    val visible: Boolean = true,
) {
    val width = bitmap.width
    val height = bitmap.height
}