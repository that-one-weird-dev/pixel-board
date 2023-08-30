package com.zeropixel.pixelboard.canvas.tools

import androidx.compose.runtime.Composable
import com.zeropixel.pixelboard.canvas.IconProvider
import com.zeropixel.pixelboard.views.CanvasViewModel

interface Tool : IconProvider {
    val configuration: (@Composable () -> Unit)?

    fun CanvasViewModel.drawStart(x: Int, y: Int) {}
    fun CanvasViewModel.draw(x: Int, y: Int) {}
    fun CanvasViewModel.drawEnd() {}
}