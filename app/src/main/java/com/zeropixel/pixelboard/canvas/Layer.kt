package com.zeropixel.pixelboard.canvas

class Layer(
    val bitmap: LayerBitmap,
    val visible: Boolean = true,
    val name: String = "Default",
) {
    constructor(
        width: Int,
        height: Int,
        name: String = "Default",
        visible: Boolean = true,
    ) : this(LayerBitmap(width, height), visible, name)
}