package com.zeropixel.engine.serialization

import com.zeropixel.engine.Engine
import com.zeropixel.engine.palette.ColorPalette
import com.zeropixel.engine.utils.ColorInt
import kotlinx.serialization.Serializable

@Serializable
class SerializedEngine(
    val canvas: SerializedCanvas,
    val palette: List<ColorInt>,
) {

    fun deserialize(): Engine {
        return Engine(
            canvas.deserialize(),
            ColorPalette(palette),
        )
    }

    companion object {
        fun from(engine: Engine): SerializedEngine {
            return SerializedEngine(
                SerializedCanvas.from(engine.canvas),
                engine.palette.colors,
            )
        }
    }
}