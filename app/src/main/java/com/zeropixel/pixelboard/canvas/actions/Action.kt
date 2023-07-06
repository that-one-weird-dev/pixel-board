package com.zeropixel.pixelboard.canvas.actions

import com.zeropixel.pixelboard.canvas.IconProvider
import com.zeropixel.pixelboard.views.CanvasViewModel

interface Action : IconProvider {
    fun CanvasViewModel.execute()
}