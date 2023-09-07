package com.zeropixel.engine

import com.zeropixel.engine.palette.ColorPalette
import com.zeropixel.engine.utils.ColorId
import com.zeropixel.engine.utils.ColorInt
import com.zeropixel.engine.utils.LayerId

class Engine(
    val canvas: Canvas,
    var palette: ColorPalette = ColorPalette(),
) {
    private val undoStack = mutableListOf<com.zeropixel.engine.undo.Undoable>()

    val currentLayerId: LayerId = 0
    val currentColorId: ColorId = 0

    val currentLayer: Layer
        get() = canvas[currentLayerId]

    val currentColor: ColorInt
        get() = palette[currentColorId]


    fun pushUndoable(undoable: com.zeropixel.engine.undo.Undoable) {
        undoStack.add(undoable)
    }

    fun popUndoableAndUndo(): Boolean {
        val undoable = undoStack.lastOrNull() ?: return false
        undoStack.remove(undoable)

        with(undoable) {
            undo()
        }

        return true
    }
}