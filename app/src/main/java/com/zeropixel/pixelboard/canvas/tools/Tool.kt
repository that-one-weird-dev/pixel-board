package com.zeropixel.pixelboard.canvas.tools

import com.zeropixel.pixelboard.canvas.IconProvider
import com.zeropixel.pixelboard.views.CanvasViewModel

interface Tool : IconProvider {
    fun CanvasViewModel.use(x: Int, y: Int)
}