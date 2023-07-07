package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.pixelboard.ui.components.menus.ConfirmMenuOptions
import com.zeropixel.pixelboard.views.CanvasViewModel

class ClearAction : Action {

    @Composable
    override fun icon(): ImageVector {
        return Icons.Rounded.Clear
    }

    override fun CanvasViewModel.execute() {
        confirmMenu = ConfirmMenuOptions("Are you sure you want to clear the canvas?") {
            canvasBitmap.clearPixels()
        }
    }
}