package com.zeropixel.pixelboard.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zeropixel.pixelboard.canvas.Canvas
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.actions.ClearAction
import com.zeropixel.pixelboard.canvas.actions.UndoAction
import com.zeropixel.pixelboard.canvas.serialization.saveCanvas
import com.zeropixel.pixelboard.canvas.tools.EraserTool
import com.zeropixel.pixelboard.canvas.tools.FillTool
import com.zeropixel.pixelboard.canvas.tools.PenTool
import com.zeropixel.pixelboard.canvas.undo.Undoable
import com.zeropixel.pixelboard.canvas.utils.ColorId
import com.zeropixel.pixelboard.canvas.utils.LayerId

class CanvasViewModel(
    val canvas: Canvas,
    private val filesDir: String,
) : ViewModel() {
    var currentLayerId by mutableStateOf<LayerId>(0)
    var currentColorId by mutableStateOf<ColorId>(0)

    val toolPalette = listOf(PenTool(), EraserTool(), FillTool())
    var currentTool by mutableStateOf(toolPalette.firstOrNull() ?: PenTool())

    val actionPalette = listOf(ClearAction(), UndoAction())

    private val undoStack = mutableListOf<Undoable>()

    var rerenderCanvasState by mutableStateOf(false)

    fun executeAction(action: Action) {
        with(action) {
            execute()
        }

        saveToFile()
    }

    fun startToolDraw(x: Int, y: Int) {
        if (x < 0 || x >= canvas.width || y < 0 || y >= canvas.height) return

        with(currentTool) {
            drawStart(x, y)
        }

        rerenderCanvas()
    }

    fun toolDraw(x: Int, y: Int) {
        if (x < 0 || x >= canvas.width || y < 0 || y >= canvas.height) return

        with(currentTool) {
            draw(x, y)
        }

        rerenderCanvas()
    }

    fun endToolDraw() {
        with(currentTool) {
            drawEnd()
        }

        rerenderCanvas()

        saveToFile()
    }

    fun pushUndoable(undoable: Undoable) {
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

    fun expectedQuickBarColumns(): Int = (canvas.palette.size - 1) / 8 + 1

    private fun saveToFile() {
        saveCanvas(filesDir, "default", canvas)
    }

    private fun rerenderCanvas() {
        rerenderCanvasState = !rerenderCanvasState
    }
}