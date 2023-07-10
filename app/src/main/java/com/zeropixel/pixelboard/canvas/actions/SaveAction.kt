package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R
import com.zeropixel.pixelboard.views.CanvasViewModel

class SaveAction : Action {
    override fun CanvasViewModel.execute() {
        showDirectoryPicker { path ->
        }
    }

    @Composable
    override fun icon(): ImageVector {
        return ImageVector.vectorResource(id = R.drawable.save)
    }
}