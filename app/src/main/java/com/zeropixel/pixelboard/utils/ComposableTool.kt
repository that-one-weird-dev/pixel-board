package com.zeropixel.pixelboard.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.engine.tools.Tool

class ComposableTool<T : Tool>(
    val tool: T,
    override val icon: @Composable () -> ImageVector,
    val configuration: (@Composable T.() -> Unit)? = null,
) : IconProvider {

    @Composable
    fun Configure() {
        configuration?.let { conf ->
            conf(tool)
        }
    }
}