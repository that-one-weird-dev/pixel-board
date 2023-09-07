package com.zeropixel.pixelboard.components.tools

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zeropixel.engine.tools.EraserTool
import com.zeropixel.engine.tools.PenTool
import com.zeropixel.pixelboard.components.toolbar.ToolConfigSlider

typealias ConfFunc<T> = @Composable T.() -> Unit

val penToolConfiguration: ConfFunc<PenTool> = @Composable {
    ToolConfigSlider(
        minValue = 1,
        maxValue = 10,
        defaultValue = size,
        onValueChange = { size = it },
    ) {
        Text("Size")
    }
}

val eraserToolConfiguration: ConfFunc<EraserTool> = @Composable {
    ToolConfigSlider(
        minValue = 1,
        maxValue = 10,
        defaultValue = size,
        onValueChange = { size = it },
    ) {
        Text("Size")
    }
}

