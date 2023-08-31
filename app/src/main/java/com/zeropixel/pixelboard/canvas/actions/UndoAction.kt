package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.views.CanvasViewModel

class UndoAction : Action {

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.ArrowBack
    }

    override fun CanvasViewModel.execute() {
        popUndoableAndUndo()
    }
}