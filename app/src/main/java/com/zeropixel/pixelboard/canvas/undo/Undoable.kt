package com.zeropixel.pixelboard.canvas.undo

import com.zeropixel.pixelboard.views.CanvasViewModel

interface Undoable {
    val name: String

    fun CanvasViewModel.undo()
}